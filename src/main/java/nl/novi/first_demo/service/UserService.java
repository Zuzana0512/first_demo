package nl.novi.first_demo.service;

import nl.novi.first_demo.exeption.RecordNotFoundException;
import nl.novi.first_demo.model.Authority;
import nl.novi.first_demo.model.User;
import nl.novi.first_demo.repository.AuthorityRepository;
import nl.novi.first_demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityRepository authorityRepository;

    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(String userName){
        Optional<User> user = userRepository.findById(userName);
        if(user.isPresent()){
            return user.get();
        }
        else {
            throw new RecordNotFoundException("User with userName " + userName + " is not found.");
        }
    }

    public String addUser (User user){
        String password = user.getPassword();
        String encryptedPassword = passwordEncoder.encode(password);
        user.setPassword(encryptedPassword);
        User newUser = userRepository.save(user);
        return newUser.getUserName();

    }

    public void deleteUser (String userName){
        if (userRepository.existsById(userName)) {
            userRepository.deleteById(userName);
        }
        else {
            throw new RecordNotFoundException("User with userName " + userName + " is not found.");
        }
    }

    public void updateUser (String userName, User user){
        Optional<User> optionalUser = userRepository.findById(userName);
        if (optionalUser.isPresent()) {
            User userInDb = optionalUser.get();
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
        }
        else {
            throw new RecordNotFoundException("User with userName " + " is not found.");
        }

    }

    public void addAuthority(String userName, String authorityName) {
        Optional<User> optionalUser = userRepository.findById(userName);
        if (optionalUser.isPresent()) {
            User userInDb = optionalUser.get();
            Authority newAuthority = new Authority();
            newAuthority.setUsername(userInDb.getUserName());
            newAuthority.setAuthority(authorityName);
            Authority savedAuthority = authorityRepository.save(newAuthority);
            userInDb.addAuthority(savedAuthority);
            userRepository.save(userInDb);
        }
        else {
            throw new RecordNotFoundException("User with userName " + " is not found.");
        }

    }

    public void setPassword(String userName, String newPassword) {
        Optional<User> optionalUser = userRepository.findById(userName);
        if (optionalUser.isPresent()) {
            User userInDb = optionalUser.get();
            String encryptedPassword = passwordEncoder.encode(newPassword);
            userInDb.setPassword(encryptedPassword);
            userRepository.save(userInDb);
        }
        else {
            throw new RecordNotFoundException("User with userName " + " is not found.");
        }
    }

}
