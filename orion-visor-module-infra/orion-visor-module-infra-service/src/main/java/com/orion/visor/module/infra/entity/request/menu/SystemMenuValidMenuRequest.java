package com.orion.visor.module.infra.entity.request.menu;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 菜单 创建菜单请求对象
 *
 * @author Jiahang Li
 * @version 1.0.0
 * @since 2023-7-18 10:18
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "SystemMenuValidMenuRequest", description = "菜单 创建菜单请求对象")
public class SystemMenuValidMenuRequest implements Serializable {

    @NotNull
    @Schema(description = "父id")
    private Long parentId;

    @NotBlank
    @Size(max = 32)
    @Schema(description = "菜单名称")
    private String name;

    @Size(max = 64)
    @Schema(description = "菜单权限")
    private String permission;

    @NotNull
    @Schema(description = "排序")
    private Integer sort;

    @NotNull
    @Schema(description = "是否可见 0不可见 1可见")
    private Integer visible;

    @Schema(description = "菜单缓存 0不缓存 1缓存")
    private Integer cache;

    @Size(max = 64)
    @Schema(description = "菜单图标")
    private String icon;

    @Size(max = 256)
    @Schema(description = "链接地址")
    private String path;

    @Size(max = 128)
    @Schema(description = "组件名称")
    private String component;

}
