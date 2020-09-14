package com.xixiwan.platform.module.common.util;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

/**
 * @ClassName: EmailUtil
 * @Description: 邮件工具类
 * @Author: Sente
 * @Date: 2018年10月2日 下午1:51:40
 * @Copyright: 2018 www.sto.cn Inc. All rights reserved.
 */
public class EmailUtils {

    private EmailUtils() {
        throw new IllegalStateException("EmailUtil class");
    }

    public static void simpleEmail(String subject, String msg, String... emails) {
        try {
            Email email = new SimpleEmail();
            email.setHostName("smtp.163.com");
            email.setAuthenticator(new DefaultAuthenticator("liaoxiting2011@163.com", "wy13875470948"));
            email.setSSLOnConnect(true);
            email.setSSLCheckServerIdentity(true);
            email.setFrom("liaoxiting2011@163.com");
            email.setSubject(subject);
            email.setMsg(msg);
            email.addTo(emails);
            email.send();
        } catch (Exception e) {
            ExceptionUtils.rethrow(e);
        }
    }

}
