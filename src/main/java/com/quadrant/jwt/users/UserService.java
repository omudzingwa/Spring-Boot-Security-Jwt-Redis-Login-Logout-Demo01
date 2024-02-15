package com.quadrant.jwt.users;

import com.quadrant.jwt.userroles.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    public User saveUser(User user){
        return userRepository.save(user);
    }
    public List<User> listAllUsers(){
        return userRepository.findAll();
    }
    public void deleteUser(long id){
        userRepository.deleteById(id);
    }

    public User findById(long id){
        return userRepository.findById(id);
    }

    public Role getUserRole(long id){
        return userRepository.getUserRole(id);
    }

}
