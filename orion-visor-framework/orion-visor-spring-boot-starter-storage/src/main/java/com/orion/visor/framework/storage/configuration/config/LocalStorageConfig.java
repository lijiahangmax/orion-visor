package com.orion.visor.framework.storage.configuration.config;

import com.orion.visor.framework.storage.core.client.local.LocalFileClientConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 本地存储配置
 *
 * @author Jiahang Li
 * @version 1.0.0
 * @since 2023/6/30 18:40
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ConfigurationProperties(prefix = "orion.storage.local")
public class LocalStorageConfig extends LocalFileClientConfig {

}
