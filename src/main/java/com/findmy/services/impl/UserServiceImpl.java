package com.findmy.services.impl;

import com.findmy.models.User;
import com.findmy.models.UserRole;
import com.findmy.repository.RoleRepository;
import com.findmy.repository.UserRepository;
import com.findmy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {

        User register = this.userRepository.findByUsername(user.getUsername());
        User register2= this.userRepository.findByPhone(user.getPhone());
        if(register !=null ){
            if(register2!=null){
                System.out.println("Phone number already present");
//                throw new Exception("Phone number already present");
            }
            System.out.println("User is already there");
            throw new Exception("User is already there");
        }
        else {
//            user create
            for(UserRole role:userRoles){
                roleRepository.save(role.getRole());
            }
            user.getUserRoles().addAll(userRoles);
            register = this.userRepository.save(user);

        }

        return register;

    }

    @Override
    public User getUserInfo(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(Long userid) {
        this.userRepository.deleteById(userid);
    }

    @Override
    public User updateDetails(User updatedUser) throws Exception {
        User existingUser  = this.userRepository.findByUsername(updatedUser.getUsername());
        System.out.println(updatedUser.getUsername());
        System.out.println(existingUser.getUsername());
        System.out.println(existingUser.getUsername().equals(updatedUser.getUsername()));
        if(existingUser.getUsername().equals(updatedUser.getUsername())){
            existingUser.setUserFullName(updatedUser.getUserFullName());
            existingUser.setPassword(updatedUser.getPassword());
//            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPhone(updatedUser.getPhone());
            existingUser.setProfile(updatedUser.getProfile());
            return userRepository.save(existingUser);
        }
        else {
            System.out.println("User not exist");
            throw new Exception();
        }

    }
}
