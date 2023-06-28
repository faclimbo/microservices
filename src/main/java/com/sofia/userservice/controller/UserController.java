package com.sofia.userservice.controller;

import com.sofia.userservice.model.Notifications;
import com.sofia.userservice.model.Posts;
import com.sofia.userservice.model.User;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    //this is for resilience4j circuit breaker
    public static final String USER_SERVICE = "user-service";


    //@CircuitBreaker, if one of the service is not responding, it will call userServiceFallBack method
    @GetMapping(value="/{userId}")
    @CircuitBreaker(name= USER_SERVICE, fallbackMethod = "userServiceFallBack")
    public User getUser(@PathVariable("userId") String userId) {
        //Adding hard coded value to model for testing
        User userOne = new User(userId, "User Name " + userId,
                "XXXXX"+ userId);

        //request data from other services or application\
        //getForObject (<param1>, <param2>)
        //param1 = URL
        //param2 = Model Class
        //http://post-service/post/1 -> registered in eureka server
        Posts posts = restTemplate.getForObject(
                "http://post-service/post/1",
                Posts.class
        );

        Notifications notifications = restTemplate.getForObject(
                "http://notification-service/notifications/1",
                Notifications.class
        );

        userOne.setPosts(posts);
        userOne.setNotifications(notifications);

        return userOne;
    }

    public User userServiceFallBack(Exception userException){
        return new User("1",
                        "User One",
                        "XYX");
    }
}
