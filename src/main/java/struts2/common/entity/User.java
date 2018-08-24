package struts2.common.entity;

import struts2.common.users.UserRole;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class User {

    @Id
    private String username;
    private String password;
    private int role;

    public User(String username, String password, int role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        switch (role) {
            case 0:
                return UserRole.ADMIN;
            case 1:
                return UserRole.STAFF;
            default:
                return UserRole.UNKNOWN;
        }
    }

    public void setRole(int role) {
        this.role = role;
    }
}


