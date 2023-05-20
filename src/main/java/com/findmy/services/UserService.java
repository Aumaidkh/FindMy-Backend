package com.findmy.services;

import com.findmy.models.User;
import com.findmy.models.UserRole;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface UserService {
//    Creating/Registering User
    public User createUser(User user, Set<UserRole> userRoles) throws Exception;

//    Getting userDetail using username
    public User getUserInfo(String username);

//    Deleting user
    public void deleteUser(Long username);

//    Updating user Details

    public User updateDetails(User user) throws Exception;


}
