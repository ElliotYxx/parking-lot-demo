package com.sheva.parkinglotdemo.shiro.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.sheva.parkinglotdemo.constant.Constants;
import com.sheva.parkinglotdemo.shiro.filter.KickoutSessionControllerFilter;
import com.sheva.parkinglotdemo.shiro.filter.LogoutFilter;
import com.sheva.parkinglotdemo.shiro.listener.Listener;
import com.sheva.parkinglotdemo.shiro.realm.UserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author Sheva
 * @Date 2020/12/3
 */
@Configuration
public class ShiroConfig {
    /**
     * 登录地址
     */
    @Value("${shiro.user.loginUrl}")
    private String loginUrl;

    /**
     * 权限认证失败地址
     */
    @Value("${shiro.user.unauthorizedUrl")
    private String unauthorizedUrl;

    /**
     * 踢出之前登录的/之后登录的用户，默认踢出之前登录的用户
     */
    @Value("${shiro.session.kickoutAfter}")
    private boolean kickoutAfter;

    /**
     * Session超时时间，单位为毫秒（默认30分钟）
     */
    @Value("${shiro.session.expireTime}")
    private int expireTime;

    /**
     * 相隔多久检查一次session的有效性，单位毫秒，默认就是10分钟
     */
    @Value("${shiro.session.validationInterval}")
    private int validationInterval;

    /**
     * 允许同时的最大session数
     */
    @Value("${shiro.session.maxSession}")
    private int maxSession;

    /**
     * 设置cookie的有效访问路径
     */
    @Value("${shiro.cookie.path}")
    private String path;

    /**
     * 设置HttpOnly属性
     */
    @Value("${shiro.cookie.httpOnly}")
    private boolean httpOnly;

    /**
     * 设置Cookie的过期时间，秒为单位
     */
    @Value("${shiro.cookie.maxAge}")
    private int maxAge;


    /**
     * 退出过滤器
     */
    public LogoutFilter logoutFilter()
    {
        LogoutFilter logoutFilter = new LogoutFilter();
        logoutFilter.setLoginUrl("/login");
        return logoutFilter;
    }

    /**
     * Shiro过滤器配置
     */

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") SecurityManager securityManager)
    {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //Shiro核心安全接口
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String,String> filterMap = new LinkedHashMap<>();
        Map<String, Filter> filter = new LinkedHashMap<>();

        //身份验证失败，跳转到登录页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        //权限认证失败 登录到登录页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauth");
        filterMap.put("/css/**", "anon");
        filterMap.put("/js/**", "anon");
        filterMap.put("/fonts/**", "anon");
        filterMap.put("/img/**", "anon");
        filterMap.put("/ajax/**", "anon");;
        filterMap.put("/login","anon");
        filterMap.put("/register","anon");
        filterMap.put("/index", "user");
        filterMap.put("/logout","logout");
        filter.put("kickout", kickoutSessionControllerFilter());
        filter.put("logout", logoutFilter());
        shiroFilterFactoryBean.setFilters(filter);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public KickoutSessionControllerFilter kickoutSessionControllerFilter()
    {
        KickoutSessionControllerFilter kickoutSessionControllerFilter=new KickoutSessionControllerFilter();
        kickoutSessionControllerFilter.setSessionManager(sessionManager());
        kickoutSessionControllerFilter.setCache(ehCacheManager());
//        kickoutSessionControllerFilter.setKickoutAfter(kickoutAfter);
//        kickoutSessionControllerFilter.setMaxSession(maxSession);
//        kickoutSessionControllerFilter.setKickoutUrl(loginUrl);
        kickoutSessionControllerFilter.setKickoutAfter(false);
        kickoutSessionControllerFilter.setMaxSession(-1);
        kickoutSessionControllerFilter.setKickoutUrl("/login");
        return kickoutSessionControllerFilter;
    }


    @Bean
    public SessionListener sessionListener()
    {
        Listener listener = new Listener();
        return listener;
    }

    @Bean
    public MethodInvokingFactoryBean getMethodInvokingFactoryBean()
    {
        MethodInvokingFactoryBean factoryBean = new MethodInvokingFactoryBean();
        factoryBean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        factoryBean.setArguments(new Object[]{securityManager()});
        return factoryBean;
    }
    @Bean
    public SimpleCookie sessionIdCookie()
    {
        SimpleCookie simpleCookie=new SimpleCookie("sid");
//        simpleCookie.setHttpOnly(httpOnly);
//        simpleCookie.setPath(path);
//        simpleCookie.setMaxAge(maxAge * 24 * 60 * 60);
        simpleCookie.setHttpOnly(true);
        simpleCookie.setPath("/");
        simpleCookie.setMaxAge(2592000);
        return simpleCookie;
    }
    @Bean
    public SessionManager sessionManager()
    {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        Collection<SessionListener> listeners=new ArrayList<SessionListener>();
        ((ArrayList<SessionListener>) listeners).add(sessionListener());
        sessionManager.setSessionListeners(listeners);
        sessionManager.setSessionIdCookie(sessionIdCookie());
        sessionManager.setSessionDAO(sessionDAO());
        sessionManager.setCacheManager(ehCacheManager());
//        sessionManager.setGlobalSessionTimeout(expireTime * 60 * 1000);
        sessionManager.setGlobalSessionTimeout(1800000);
        sessionManager.setDeleteInvalidSessions(true);
//        sessionManager.setSessionValidationInterval(validationInterval * 60 * 1000);
        sessionManager.setSessionValidationInterval(3600000);

        sessionManager.setSessionIdUrlRewritingEnabled(false);
        //避免与SERVLET容器默认的Cookie名冲突
        Cookie cookie = new SimpleCookie("wms.session.id");
        sessionManager.setSessionIdCookie(cookie);
        return sessionManager;
    }


    @Bean
    public SessionDAO sessionDAO()
    {
        EnterpriseCacheSessionDAO enterpriseCacheSessionDAO = new EnterpriseCacheSessionDAO();
        enterpriseCacheSessionDAO.setCacheManager(ehCacheManager());
        enterpriseCacheSessionDAO.setActiveSessionsCacheName("shiro-activeSessionCache");
        enterpriseCacheSessionDAO.setSessionIdGenerator(sessionIdGenerator());
        return enterpriseCacheSessionDAO;
    }
    @Bean
    public SessionIdGenerator sessionIdGenerator()
    {
        return new JavaUuidSessionIdGenerator();
    }
    @Bean
    public SecurityManager securityManager()
    {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm(ehCacheManager()));
        securityManager.setCacheManager(ehCacheManager());
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }
    @Bean
    public UserRealm myShiroRealm(EhCacheManager ehCacheManager)
    {
        UserRealm userRealm = new UserRealm();
        userRealm.setCachingEnabled(true);
        userRealm.setCacheManager(ehCacheManager);

        userRealm.setAuthorizationCacheName("authorizationCache");
        userRealm.setAuthorizationCachingEnabled(true);

        userRealm.setAuthenticationCacheName("authenticationCache");
        userRealm.setAuthenticationCachingEnabled(true);

        userRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return userRealm;
    }
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher()
    {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(Constants.ALGORITHM_NAME);
        hashedCredentialsMatcher.setHashIterations(Constants.HASH_ITERATIONS);
        return hashedCredentialsMatcher;
    }
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor()
    {
        return new LifecycleBeanPostProcessor();
    }
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor()
    {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager());
        return advisor;
    }
    @Bean
    public EhCacheManager ehCacheManager()
    {
        EhCacheManager em = new EhCacheManager();
        em.setCacheManagerConfigFile("classpath:ehcache/ehcache-shiro.xml");
        return em;
    }
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator()
    {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }
    @Bean
    public ShiroDialect shiroDialect()
    {
        return new ShiroDialect();
    }

}
