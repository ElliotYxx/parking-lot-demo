package com.sheva.parkinglotdemo.shiro.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @Author Sheva
 * @Date 2020/12/6
 */
@Slf4j
public class LogoutFilter extends org.apache.shiro.web.filter.authc.LogoutFilter {

    private String loginUrl;

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        String redirectUrl = getRedirectUrl(request, response, subject);
        try {
            // 退出登录
            subject.logout();
        } catch (SessionException e) {
            log.error("账号登出失败...", e.getMessage());
        }
        issueRedirect(request, response, redirectUrl);
        return false;
    }

    @Override
    protected String getRedirectUrl(ServletRequest request, ServletResponse response, Subject subject) {
        String url = getLoginUrl();
        if (!url.isEmpty()){
            return url;
        }

        return super.getRedirectUrl(request, response, subject);
    }
}
