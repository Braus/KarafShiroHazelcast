package res;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.util.Factory;

public class SecurityFactory {

    private static SecurityManager securityManager;

    public static SecurityManager getSecurityManager(){
        if(securityManager == null) {
            Factory<SecurityManager> factory = new IniSecurityManagerFactory("etc/shiro.ini");
            //2.
            securityManager = factory.getInstance();
            SimpleSession simple = new SimpleSession();
//            simple.se
            //3.
            SecurityUtils.setSecurityManager(securityManager);
            return securityManager;
        } else {
            return securityManager;
        }
    }

}
