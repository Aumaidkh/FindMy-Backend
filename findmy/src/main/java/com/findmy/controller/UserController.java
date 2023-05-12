package com.findmy.controller;

import com.findmy.models.Role;
import com.findmy.models.User;
import com.findmy.models.UserRole;
import com.findmy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

//    creating user
    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody User user) throws Exception {
        try {
            Set<UserRole> setRoles = new HashSet<>();
            Role role = new Role();
            role.setRole_id(13L);
            role.setRole_name("NORMAL");

            UserRole userRole = new UserRole();
            userRole.setUser(user);
            userRole.setRole(role);

            setRoles.add(userRole);
           return new ResponseEntity<>(this.userService.createUser(user,setRoles), HttpStatus.CREATED);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(user,HttpStatus.BAD_REQUEST);
        }
    }

//    Getting User
    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(@PathVariable("username") String username){
        try{
            return new ResponseEntity<>(this.userService.getUserInfo(username), HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

//    Deleting User Details

    @DeleteMapping("/{userid}")
    public ResponseEntity<?> deleteUser(@PathVariable("userid") Long userid){
        try {
            this.userService.deleteUser(userid);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

//    Updating userDetails
    @PutMapping("/")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        try{
            return new ResponseEntity<>(this.userService.updateDetails(user), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
