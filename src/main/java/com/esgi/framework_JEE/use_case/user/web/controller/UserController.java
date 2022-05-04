package com.esgi.framework_JEE.use_case.user.web.controller;

import com.esgi.framework_JEE.use_case.user.entities.User;
import com.esgi.framework_JEE.use_case.user.command.UserCommand;
import com.esgi.framework_JEE.use_case.user.query.UserQuery;
import com.esgi.framework_JEE.use_case.user.web.request.UserRequest;
import com.esgi.framework_JEE.use_case.user.web.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private final UserCommand userCommand;
    @Autowired
    private final UserQuery userQuery;

    public UserController(UserCommand userCommand, UserQuery userQuery){
        this.userCommand = userCommand;
        this.userQuery = userQuery;
    }

    @PostMapping("/create")
    public  ResponseEntity<UserResponse> addUser(@RequestBody UserRequest userRequest) {
        if(userQuery.userEmailExist(userRequest.email))
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        var createdUser = userCommand.create(userRequest);
        if(createdUser == null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(userToUserResponse(createdUser), HttpStatus.CREATED);
    }



    @GetMapping(value = "/", produces = { MimeTypeUtils.APPLICATION_JSON_VALUE }, headers = "Accept=application/json")
    public ResponseEntity<List<UserResponse>> getUserAll(){
        return new ResponseEntity<>(
                listUserToListUserResponse(userQuery.getAll()),
                HttpStatus.OK);
    }

    @GetMapping(value = "/{userId}", produces = { MimeTypeUtils.APPLICATION_JSON_VALUE }, headers = "Accept=application/json")
    public ResponseEntity<UserResponse> getUserById(@PathVariable int userId) {
        var user = userQuery.getById(userId);
        if(user == null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(userToUserResponse(
                user),
                HttpStatus.OK
        );
    }

    @GetMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody UserRequest userRequest){
        var user = userQuery.getByEmailAndPassword(userRequest.email, userRequest.password);
        if(user == null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(userToUserResponse(user), HttpStatus.OK);
    }

    @PatchMapping("/password/{userId}")
    public ResponseEntity<UserResponse> changePassword(@PathVariable int userId, @RequestBody UserRequest userRequest){
        var userNewPassword = userCommand.changePassword(userId, userRequest);
        if(userNewPassword == null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(userToUserResponse(userNewPassword), HttpStatus.OK);
    }

    @PatchMapping("/email/{userId}")
    public ResponseEntity<UserResponse> changeEmail(@PathVariable int userId, @RequestBody UserRequest userRequest){
        var user = userCommand.changeEmail(userId, userRequest);
        if(user == null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(userToUserResponse(user), HttpStatus.OK);

    }

    @PatchMapping("/lastname/{userId}")
    public ResponseEntity<UserResponse> changeLastname(@PathVariable int userId, @RequestBody UserRequest userRequest){
        var user = userCommand.changeLastname(userId, userRequest);
        if(user == null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(userToUserResponse(user), HttpStatus.OK);
    }

    @PatchMapping("/firstname/{userId}")
    public ResponseEntity<UserResponse> changeFirstname(@PathVariable int userId, @RequestBody UserRequest userRequest){
        var user = userCommand.changeFirstname(userId, userRequest);
        if(user == null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(userToUserResponse(user), HttpStatus.OK);
    }


    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable int userId) {
        var user = userQuery.getById(userId);
        if(user == null)
            return new ResponseEntity<>(
                    "User " + userId + " not exist",
                    HttpStatus.BAD_REQUEST
            );
        userCommand.delete(userId);
        return new ResponseEntity<>(
                "User " + userId + " deleted",
                HttpStatus.ACCEPTED
        );

    }




    private UserResponse userToUserResponse(User user){
        return new UserResponse()
                .setId(user.getId())
                .setFirstname(user.getFirstname())
                .setEmail(user.getEmail())
                .setLastname(user.getLastname());
    }

    private List<UserResponse> listUserToListUserResponse(List<User> users){
        List<UserResponse> userResponses = new ArrayList<>();
        users.forEach(user -> userResponses.add(userToUserResponse(user)));
        return userResponses;
    }
}
