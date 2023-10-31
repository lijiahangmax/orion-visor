package com.orion.ops.module.infra.entity.request.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 用户下线请求
 *
 * @author Jiahang Li
 * @version 1.0.0
 * @since 2023/7/17 12:19
 */
@Data
@Schema(name = "OfflineUserSessionRequest", description = "用户下线请求")
public class OfflineUserSessionRequest {

    @Schema(description = "userId")
    private Long userId;

    @NotNull
    @Schema(description = "时间戳")
    private Long timestamp;

}
