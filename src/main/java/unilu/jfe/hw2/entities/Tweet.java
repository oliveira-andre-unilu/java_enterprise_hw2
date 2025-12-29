package unilu.jfe.hw2.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.redis.core.RedisHash;

import lombok.Getter;
import lombok.Setter;

@RedisHash("Tweet")
@Getter
@Setter
public class Tweet implements Serializable{
    
    //Data related attributes
    private String id;
    private String title, message;
    private String authorName, authorId; //Author related data allowing for quick lookup and id relationship
    private String postDate;
    private List<Feedback> allFeedbacks;


    

    //Constructors
    public Tweet() {
        this.id = "0";
        this.title = "UNKNOWN";
        this.message = "UNKNOWN";
        this.authorName = "UNKNOWN";
        this.authorId = "0";
        this.postDate = "2000-00-00";
        this.allFeedbacks = new ArrayList<Feedback>();
    }

    
    public Tweet(String id, String title, String message, String authorName, String authorId, String postDate,
            List<Feedback> allFeedbacks) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.authorName = authorName;
        this.authorId = authorId;
        this.postDate = postDate;
        this.allFeedbacks = allFeedbacks;
    }

}
