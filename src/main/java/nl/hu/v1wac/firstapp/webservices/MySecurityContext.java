package nl.hu.v1wac.firstapp.webservices;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

public class MySecurityContext implements SecurityContext {
    private String name;
    private String role;
    private Boolean isSecure;

    public MySecurityContext(String name, String role, Boolean isSecure) {
        this.name = name;
        this.role = role;
        this.isSecure = isSecure;
    }

    public Principal getUserPrincipal() {
        return new Principal() {
            @Override
            public String getName() {
                return name;
            }
        };
    }

    public boolean isUserInRole(String role) { return role.equals(this.role); }
    public boolean isSecure() { return isSecure; }
    public String getAuthenticationScheme() { return "Bearer"; }

}
