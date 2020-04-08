package resources;

import sec.SecurityFactory;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashingPasswordService;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

public class AuthenticationService {

    SecurityManager securityManager = SecurityFactory.getSecurityManager();
    private HashingPasswordService passwordService;

    public String login(final UsernamePasswordToken credentials)
            throws UnauthenticatedException {
        Subject newSubject = new Subject.Builder(securityManager).buildSubject();
        newSubject.login(credentials);
        return newSubject.getSession().getId().toString();
    }

    public boolean isAuthenticated(final String token) {
        Subject requestSubject = new Subject.Builder(securityManager).sessionId(
                token).buildSubject();
        return requestSubject.isAuthenticated();
    }

    public String getUsername(final String token) {
        Subject requestSubject = new Subject.Builder(securityManager).sessionId(
                token).buildSubject();
        return requestSubject.getPrincipal().toString();
    }

    public SecurityManager getSecurityManager() {
        return securityManager;
    }

    public void setSecurityManager(SecurityManager securityManager) {
        this.securityManager = securityManager;
    }

    public HashingPasswordService getPasswordService() {
        return passwordService;
    }

    public void setPasswordService(HashingPasswordService passwordService) {
        this.passwordService = passwordService;
    }

}
