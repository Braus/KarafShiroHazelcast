package sec;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.env.BasicIniEnvironment;
import org.apache.shiro.env.Environment;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.hazelcast.cache.HazelcastCacheManager;

public class SecurityFactory {

    private static DefaultSecurityManager securityManager;

    public static SecurityManager getSecurityManager(){
        if(securityManager == null) {
            Environment env = new BasicIniEnvironment("etc/shiro.ini");
            //2.
            SimpleSession simple = new SimpleSession();
            securityManager = (DefaultSecurityManager) env.getSecurityManager();
            DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
            EnterpriseCacheSessionDAO sessionDAO = new EnterpriseCacheSessionDAO();
            sessionManager.setSessionDAO(sessionDAO);
            sessionManager.setSessionValidationSchedulerEnabled(false);
            securityManager.setSessionManager(sessionManager);
            HazelcastCacheManager cacheManager = new HazelcastCacheManager();
            securityManager.setCacheManager(cacheManager);
            //3.
            SecurityUtils.setSecurityManager(securityManager);
            return securityManager;
        } else {
            return securityManager;
        }
    }

}
