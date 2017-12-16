package org.abc.controllers;

import com.google.common.collect.ImmutableMap;
import org.abc.data.entity.User;
import org.abc.exceptions.NotFoundException;
import org.abc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sign-up")
public class SignUpController {

    @Autowired
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/new-user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        try {
            userService.createUser(user);
            return new ResponseEntity<>(ImmutableMap.of("message", "Created"), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(ImmutableMap.of("message", "Internal Server Error"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "{user_id}/view-user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getUser(@PathVariable("user_id") int userId) {
        try {
            return new ResponseEntity<>(userService.getUser(userId), HttpStatus.OK);
        } catch (NotFoundException e) {
            System.out.println(e);
            return new ResponseEntity<>(ImmutableMap.of("message", e.getMessage()), null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(ImmutableMap.of("message", "Internal Server Error"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
