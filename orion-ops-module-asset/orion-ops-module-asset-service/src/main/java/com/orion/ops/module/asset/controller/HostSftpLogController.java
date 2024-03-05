package com.orion.ops.module.asset.controller;

import com.orion.lang.define.wrapper.DataGrid;
import com.orion.ops.framework.biz.operator.log.core.annotation.OperatorLog;
import com.orion.ops.framework.common.validator.group.Page;
import com.orion.ops.framework.log.core.annotation.IgnoreLog;
import com.orion.ops.framework.log.core.enums.IgnoreLogMode;
import com.orion.ops.framework.web.core.annotation.RestWrapper;
import com.orion.ops.module.asset.define.operator.HostTerminalOperatorType;
import com.orion.ops.module.asset.entity.request.host.HostSftpLogQueryRequest;
import com.orion.ops.module.asset.entity.vo.HostSftpLogVO;
import com.orion.ops.module.asset.service.HostSftpLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * SFTP 操作日志服务 api
 *
 * @author Jiahang Li
 * @version 1.0.0
 * @since 2023-12-26 22:09
 */
@Tag(name = "asset - SFTP 操作日志服务")
@Slf4j
@Validated
@RestWrapper
@RestController
@RequestMapping("/asset/host-sftp-log")
@SuppressWarnings({"ELValidationInJSP", "SpringElInspection"})
public class HostSftpLogController {

    @Resource
    private HostSftpLogService hostSftpLogService;

    @IgnoreLog(IgnoreLogMode.RET)
    @PostMapping("/query")
    @Operation(summary = "分页查询 SFTP 操作日志")
    @PreAuthorize("@ss.hasAnyPermission('infra:operator-log:query', 'asset:host-sftp-log:management:query')")
    public DataGrid<HostSftpLogVO> getHostSftpLogPage(@Validated(Page.class) @RequestBody HostSftpLogQueryRequest request) {
        return hostSftpLogService.getHostSftpLogPage(request);
    }

    @OperatorLog(HostTerminalOperatorType.DELETE_SFTP_LOG)
    @DeleteMapping("/delete")
    @Operation(summary = "删除 SFTP 操作日志")
    @Parameter(name = "idList", description = "idList", required = true)
    @PreAuthorize("@ss.hasAnyPermission('infra:operator-log:delete', 'asset:host-sftp-log:management:delete')")
    public Integer deleteHostSftpLog(@RequestParam("idList") List<Long> idList) {
        return hostSftpLogService.deleteHostSftpLog(idList);
    }

}

