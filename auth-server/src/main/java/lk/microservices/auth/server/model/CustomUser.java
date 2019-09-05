package lk.microservices.auth.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lk.microservices.auth.server.domain.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;


public class CustomUser extends User {

    private String firstName;

    private String lastName;

    private String createDate;

    private String lastUpdate;

    private String email;

    private String status;

    private String oldPasswords;

    private String numberOfLoginAttempts;

    private String context;

    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public CustomUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOldPasswords() {
        return oldPasswords;
    }

    public void setOldPasswords(String oldPasswords) {
        this.oldPasswords = oldPasswords;
    }

    public String getNumberOfLoginAttempts() {
        return numberOfLoginAttempts;
    }

    public void setNumberOfLoginAttempts(String numberOfLoginAttempts) {
        this.numberOfLoginAttempts = numberOfLoginAttempts;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}

