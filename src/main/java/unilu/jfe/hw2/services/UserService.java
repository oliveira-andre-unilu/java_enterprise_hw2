package unilu.jfe.hw2.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.redis.core.RedisTemplate;

import unilu.jfe.hw2.entities.User;

public class UserService {
    
    //Constants
    private static final String USER_ID_SEQUENCE = "seq:user:id";
    //Dependencies
    private final RedisTemplate<String, User> rt;


    //Constructors
    public UserService(RedisTemplate<String, User> rt){
        this.rt = rt;
    }


    //Main functions
    public boolean createUser(String userName, String email){
        
        //Verify if userName or email already exists
        for(String id: rt.keys("[0-9]*")){
            User existingUser = rt.opsForValue().get(id);
            if(existingUser.getEmail().equals(email) || existingUser.getUserName().equals(userName)){
                return false;
            }
        }

        //Initializing teacher
        String newId = generateId();
        User newUser = new User(newId, userName, email);
        rt.opsForValue().set(newId, newUser);
        System.out.println("Adding User " + newUser + "...");
        return true;
    }

    public User getUser(String id){
        User existingUser = rt.opsForValue().get(id);
        return existingUser;
    }

    public List<User> getAllUsers(){
        List<User> allUsers = new ArrayList<>();
        for(String id: rt.keys("[0-9]*")){
            User existingUser = rt.opsForValue().get(id);
            allUsers.add(existingUser);
        }
        return allUsers;
    }

    //Helper functions
    private String generateId(){
        return String.valueOf(rt.opsForValue().increment(USER_ID_SEQUENCE));
    }

}
