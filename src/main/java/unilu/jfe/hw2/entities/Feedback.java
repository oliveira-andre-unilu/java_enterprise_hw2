package unilu.jfe.hw2.entities;

import java.io.Serializable;

import org.springframework.data.redis.core.RedisHash;

import lombok.Data;

@RedisHash("FeedBack")
@Data
public class Feedback implements Serializable{

    //Data related attributes
    private String id;
    private String userName, userId;
    private String content;

    //Constructors
    public Feedback(){
        this.id = "0";
        this.userName = "UNKNOWN";
        this.userId = "0";
        this.content = "NOTHING";
    }

    public Feedback(String id, String userName, String userId, String content){
        this.id = id;
        this.userName = userName;
        this.userId = userId;
        this.content = content;
    }

    //Override methods

    @Override
    public String toString() {
        return "Feedback [id=" + id + ", userName=" + userName + ", userId=" + userId + ", content=" + content + "]";
    }
}
