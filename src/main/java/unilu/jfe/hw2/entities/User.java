package unilu.jfe.hw2.entities;

import java.io.Serializable;

import org.springframework.data.redis.core.RedisHash;

import lombok.Data;

@RedisHash("User")
@Data
public class User implements Serializable{
    
    //General data attributes
    private String id;
    private String userName, email;

    //Constructors
    public User(){
        this.id = "0";
        this.userName = "UNKNOWN";
        this.email = "not@known.com";
    }

    public User(String id, String userName, String email) {
        this.id = id;
        this.userName = userName;
        this.email = email;
    }

    //Override methods
    @Override
    public String toString(){
        return "User =>{id: " + this.getId() + "; username: " + this.userName + "; email: " + this.getEmail() + "}";
    }
}
