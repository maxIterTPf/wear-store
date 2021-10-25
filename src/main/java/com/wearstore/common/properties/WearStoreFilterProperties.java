package com.wearstore.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * Filter 配置属性
 *
 * @author PF.Tian
 * @since 2021/08/18
 */
@Data
@ConfigurationProperties(prefix = "wear-store.filter")
public class WearStoreFilterProperties {

    /**
     * 请求路径Filter配置
     */
    @NestedConfigurationProperty
    private FilterConfig requestPath = new FilterConfig();

    @Data
    public static class FilterConfig {

        /**
         * 是否启用
         */
        private boolean enabled;

        /**
         * 包含的路径
         */
        private String[] includePaths;

        /**
         * 排除路径
         */
        private String[] excludePaths;

    }
}
