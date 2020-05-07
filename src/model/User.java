package model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    private long id;
    private String name;
    private String password;
    private String gender;

    public User(long id, String name, String password, String gender){
        this.id = id;
        this.name = name;
        this.password = password;
        this.gender = gender;
    }

    public User( String name, String password, String gender) {
        this.name = name;
        this.password = password;
        this.gender = gender;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }
}
