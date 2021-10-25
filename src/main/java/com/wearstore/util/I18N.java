package com.wearstore.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author PF.Tian
 * @since 2021/08/17
 */
@Component
@Slf4j
public class I18N {

    private static MessageSource messageSource;

    @Autowired
    public I18N(MessageSource messageSource) {
        I18N.messageSource = messageSource;
    }

    /**
     * 获取不带参数的国际化值
     *
     * @param msgKey 消息key
     * @return 消息字符串
     */
    public static String get(String msgKey) {
        try {
            return messageSource.getMessage(msgKey, null, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            log.error("error key获取无参数error msg出现异常：", e);
            return msgKey;
        }
    }

    /**
     * 获取带参数的国际化值
     *
     * @param msgKey 消息key
     * @param args   参数
     * @return 消息字符串
     */
    public static String get(String msgKey, Object[] args) {
        try {
            return messageSource.getMessage(msgKey, args, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            log.error("error key获取多参数error msg出现异常：", e);
            return msgKey;
        }
    }
}
