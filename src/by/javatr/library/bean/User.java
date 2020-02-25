package by.javatr.library.bean;

import by.javatr.library.dao.util.Role;
import by.javatr.library.dao.util.Status;

import java.io.Serializable;

public class User implements Serializable {


    private static final long serialVersionUID = -6672256886751595811L;

    private int id;
    private String name;
    private int year;
    private String login;
    private String password;
    private Role role;
    private Status status;

    public User() {
    }

    public final int getId() {
        return id;
    }

    public final void setId(int id) {
        this.id = id;
    }


    public final String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public final int getYear() {
        return year;
    }

    public final void setYear(int year) {
        this.year = year;
    }

    public final String getLogin() {
        return login;
    }

    public final void setLogin(String login) {
        this.login = login;
    }

    public final String getPassword() {
        return password;
    }

    public final void setPassword(String password) {
        this.password = password;
    }

    public final Role getRole() {
        return role;
    }

    public final void setRole(Role role) {
        this.role = role;
    }

    public final Status getStatus() {
        return status;
    }

    public final void setStatus(Status status) {
        this.status = status;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        User other = (User) obj;
        if(name==null){
            if(other.name!=null) return false;
        } else{
            if(!name.equals(other.name)) return false;
        }
        if(year!=other.year) return false;
        if(login==null){
            if(other.login!=null) return false;
        } else{
            if(!login.equals(other.login)) return false;
        }
        if(password==null){
            if(other.password!=null) return false;
        } else{
            if(!password.equals(other.password)) return false;
        }
        if(role==null){
            if(other.role!=null) return false;
        } else{
            if(role!=other.role) return false;
        }
        if(status==null){
            if(other.status!=null) return false;
        } else{
            if(status!=other.status) return false;
        }

      return true;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + year;
        result = prime * result + ((login == null) ? 0 : login.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() + '@' +
                "id=" + id +
                ", name='" + name +
                ", year=" + year +
                ", login='" + login +
                ", password='" + password +
                ", role=" + role +
                ", status=" + status;
    }
}
