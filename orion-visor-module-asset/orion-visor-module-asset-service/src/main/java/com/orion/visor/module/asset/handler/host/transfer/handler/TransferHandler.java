package com.orion.visor.module.asset.handler.host.transfer.handler;

import com.orion.lang.id.UUIds;
import com.orion.lang.utils.Exceptions;
import com.orion.lang.utils.io.Streams;
import com.orion.net.host.SessionStore;
import com.orion.spring.SpringHolder;
import com.orion.visor.framework.common.constant.ErrorMessage;
import com.orion.visor.framework.common.constant.ExtraFieldConst;
import com.orion.visor.framework.websocket.core.utils.WebSockets;
import com.orion.visor.module.asset.entity.dto.HostTerminalConnectDTO;
import com.orion.visor.module.asset.handler.host.transfer.enums.TransferOperatorType;
import com.orion.visor.module.asset.handler.host.transfer.enums.TransferReceiverType;
import com.orion.visor.module.asset.handler.host.transfer.model.TransferOperatorRequest;
import com.orion.visor.module.asset.handler.host.transfer.session.*;
import com.orion.visor.module.asset.handler.host.transfer.utils.TransferUtils;
import com.orion.visor.module.asset.service.HostTerminalService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.WebSocketSession;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 传输处理器
 *
 * @author Jiahang Li
 * @version 1.0.0
 * @since 2024/2/21 20:57
 */
@Slf4j
public class TransferHandler implements ITransferHandler {

    private static final HostTerminalService hostTerminalService = SpringHolder.getBean(HostTerminalService.class);

    private final Long userId;

    private final WebSocketSession channel;

    /**
     * 当前会话
     */
    private ITransferHostSession currentSession;

    /**
     * 会话列表
     */
    private final ConcurrentHashMap<String, ITransferHostSession> sessions;

    @Getter
    private final ConcurrentHashMap<String, IDownloadSession> tokenSessions;

    public TransferHandler(WebSocketSession channel) {
        this.channel = channel;
        this.userId = WebSockets.getAttr(channel, ExtraFieldConst.USER_ID);
        this.sessions = new ConcurrentHashMap<>();
        this.tokenSessions = new ConcurrentHashMap<>();
    }

    @Override
    public void handleMessage(TransferOperatorRequest payload) {
        // 解析消息类型
        TransferOperatorType type = TransferOperatorType.of(payload.getType());
        // 获取会话
        if (!this.getAndInitSession(payload, type)) {
            return;
        }
        // 处理消息
        switch (type) {
            case UPLOAD_START:
                // 开始上传
                ((IUploadSession) currentSession).startUpload(payload.getPath());
                break;
            case UPLOAD_FINISH:
                // 上传完成
                ((IUploadSession) currentSession).uploadFinish();
                break;
            case UPLOAD_ERROR:
                // 上传失败
                ((IUploadSession) currentSession).uploadError();
                break;
            case DOWNLOAD_INIT:
                // 开始下载
                String token = UUIds.random32();
                tokenSessions.put(token, (IDownloadSession) currentSession);
                ((IDownloadSession) currentSession).downloadInit(payload.getPath(), token);
                break;
            case DOWNLOAD_ABORT:
                // 中断下载
                ((IDownloadSession) currentSession).abortDownload();
                break;
            default:
                break;
        }
    }

    @Override
    public void putContent(byte[] content) {
        ((IUploadSession) currentSession).putContent(content);
    }

    /**
     * 获取并且初始化会话
     *
     * @param payload payload
     * @param type    type
     * @return success
     */
    private boolean getAndInitSession(TransferOperatorRequest payload, TransferOperatorType type) {
        Long hostId = payload.getHostId();
        String sessionKey = hostId + "_" + type.getKind();
        try {
            // 获取会话
            ITransferHostSession session = sessions.get(sessionKey);
            if (session == null) {
                // 获取主机信息
                HostTerminalConnectDTO connectInfo = hostTerminalService.getTerminalConnectInfo(this.userId, hostId);
                SessionStore sessionStore = hostTerminalService.openSessionStore(connectInfo);
                // 打开会话并初始化
                if (TransferOperatorType.UPLOAD.equals(type.getKind())) {
                    // 上传操作
                    session = new UploadSession(connectInfo, sessionStore, this.channel);
                } else if (TransferOperatorType.DOWNLOAD.equals(type.getKind())) {
                    // 下载操作
                    session = new DownloadSession(connectInfo, sessionStore, this.channel);
                } else {
                    throw Exceptions.invalidArgument(ErrorMessage.UNKNOWN_TYPE);
                }
                session.init();
                sessions.put(sessionKey, session);
                log.info("TransferHandler.getAndInitSession success channelId: {}, hostId: {}", channel.getId(), hostId);
            }
            this.currentSession = session;
            return true;
        } catch (Exception e) {
            log.error("TransferHandler.getAndInitSession error channelId: {}", channel.getId(), e);
            // 响应结果
            TransferUtils.sendMessage(this.channel, TransferReceiverType.NEXT_TRANSFER, e);
            return false;
        }
    }

    @Override
    public void close() {
        log.info("TransferHandler.close channelId: {}", channel.getId());
        sessions.values().forEach(Streams::close);
        tokenSessions.clear();
    }

}
