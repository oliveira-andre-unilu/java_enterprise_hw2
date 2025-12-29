package unilu.jfe.hw2.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.redis.core.RedisTemplate;

import unilu.jfe.hw2.entities.Feedback;
import unilu.jfe.hw2.entities.Tweet;
import unilu.jfe.hw2.entities.User;

public class TweetService {

    //Constants
    private static final String TWEET_ID_SEQUENCE = "seq:tweet:id";
    private static final String FEEDBACK_ID_SEQUENCE = "seq:feedback:id";

    //Dependencies
    private final RedisTemplate<String, Tweet> rt;
    private final UserService us;

    //Constructors
    public TweetService(RedisTemplate<String, Tweet> rt, UserService us) {
        this.rt = rt;
        this.us = us;
    }

    public Tweet postTweet(String userId, String title, String message){
        //Verifying user existence and getting name
        User user = us.getUser(userId);
        if(user==null){
            return null;
        }

        // Creating the new user
        String userName = user.getUserName();
        String tweetId = generateTweetId();
        Tweet newTweet = new Tweet(tweetId, title, message, userName, userId, LocalDate.now().toString(), new ArrayList<>());

        // Adding the user to the db
        rt.opsForValue().set(tweetId, newTweet);
        return newTweet;
    }

    public Feedback postFeedback(String postId, String userId, String content){
        //Verifying user existence and getting name
        User user = us.getUser(userId);
        if ( user == null){
            return null;
        }

        //Verifying if post exists
        Tweet tweet = this.getPost(postId);
        if(tweet == null){
            return null;
        }

        //Create feedback object
        String feedbackId = generateFeedbackId();
        Feedback newFeedback = new Feedback(feedbackId, user.getUserName(), userId, content);

        // Updating tweet
        List<Feedback> allPosts = tweet.getAllFeedbacks();
        allPosts.add(newFeedback);
        tweet.setAllFeedbacks(allPosts);
        
        //Saving updated feedback to db
        rt.opsForValue().set(postId, tweet);
        return newFeedback;
    }

    public List<Tweet> searchFor(String word){
        List<Tweet> result = new ArrayList<>();
        for(String key : rt.keys("TWEET[0-9]*")){
            Tweet tempTweet = rt.opsForValue().get(key);
            
            //General search
            if(tempTweet.getId().contains(word) || tempTweet.getAuthorName().contains(word) || tempTweet.getId().contains(word) ||
            tempTweet.getMessage().contains(word) || tempTweet.getPostDate().contains(word) || tempTweet.getTitle().contains(word)){
                result.add(tempTweet);
            }
        }
        return result;
    }


    public Tweet getPost(String postId){
        Tweet searchedTweet = rt.opsForValue().get(postId);
        return searchedTweet;
    }

    //Helper functions
    public String generateTweetId(){
        return "TWEET" + String.valueOf(rt.opsForValue().increment(TWEET_ID_SEQUENCE));
    }

    public String generateFeedbackId(){
        return "FEEDBACK" + String.valueOf(rt.opsForValue().increment(FEEDBACK_ID_SEQUENCE));
    }
    
}
