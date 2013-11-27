package com.smartapp.openid;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.util.Collection;

/**
 * Customized {@code UserDetails} implementation.
 *
 * @author Luke Taylor
 * @since 3.1
 */
public class CustomUserDetails extends User implements Serializable {

    private String email;
    private String name;
    private boolean newUser;

    public CustomUserDetails(String username, Collection<GrantedAuthority> authorities) {
        super(username, "unused", authorities);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isNewUser() {
        return newUser;
    }

    public void setNewUser(boolean newUser) {
        this.newUser = newUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return "Email: " + email + ", username: " + super.getUsername() + ", name: " + name;
    }
}

