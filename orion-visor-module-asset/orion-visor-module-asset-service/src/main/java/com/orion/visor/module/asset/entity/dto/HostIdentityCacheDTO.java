package com.orion.visor.module.asset.entity.dto;

import com.orion.lang.define.cache.key.model.LongCacheIdModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 主机身份缓存
 *
 * @author Jiahang Li
 * @version 1.0.0
 * @since 2023/9/21 12:00
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "HostIdentityCacheDTO", description = "主机身份缓存")
public class HostIdentityCacheDTO implements LongCacheIdModel, Serializable {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "类型")
    private String type;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "密钥id")
    private Long keyId;

    /**
     * 资产页面展示
     */
    @Schema(description = "创建时间")
    private Date createTime;

    /**
     * 资产页面展示
     */
    @Schema(description = "修改时间")
    private Date updateTime;

}
