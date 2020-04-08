package resources;

import res.SecurityFactory;
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

//    public void initializeTestScenario() {
//        // create roles
//        Role role1 = new Role();
//        role1.setRoleName("ADMIN");
//        role1.setDescription("Administrative user role");
//        roleDao.persist(role1);
//
//        Role role2 = new Role();
//        role2.setRoleName("REGULAR");
//        role2.setDescription("Regular user role");
//        roleDao.persist(role2);
//
//        // create administrative user
//        User adminUser = new User();
//        adminUser.setEmail("admin@example.com");
//        // clear password
//        final char[] pwd1 = { '1', '2', '3' };
//        // hash password
//        final String adminUserPassword = passwordService.encryptPassword(pwd1);
//        adminUser.setPassword(adminUserPassword.toCharArray());
//        userDao.persist(adminUser);
//
//        // create regular user
//        User regularUser = new User();
//        regularUser.setEmail("regular@example.com");
//        // clear password
//        final char[] pwd2 = { '4', '5', '6' };
//        // hash password
//        final String regularUserPassword = passwordService.encryptPassword(pwd2);
//        regularUser.setPassword(regularUserPassword.toCharArray());
//        userDao.persist(regularUser);
//    }

//    public RoleDao getRoleDao() {
//        return roleDao;
//    }

//    public void setRoleDao(RoleDao roleDao) {
//        this.roleDao = roleDao;
//    }

//    public UserDao getUserDao() {
//        return userDao;
//    }

//    public void setUserDao(UserDao userDao) {
//        this.userDao = userDao;
//    }

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
