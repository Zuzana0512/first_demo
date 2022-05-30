package nl.novi.webshop.service;

import nl.novi.webshop.dto.CustomerRequestDto;
import nl.novi.webshop.dto.UserDto;
import nl.novi.webshop.exeption.RecordNotFoundException;
import nl.novi.webshop.model.Authority;
import nl.novi.webshop.model.Customer;
import nl.novi.webshop.model.User;
import nl.novi.webshop.repository.AuthorityRepository;
import nl.novi.webshop.repository.CustomerRepository;
import nl.novi.webshop.repository.UserRepository;
import nl.novi.webshop.util.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerServiceImpl customerService;

//    @Autowired
//    private AuthorityRepository authorityRepository;

    public List<UserDto> getUsers() {
        List<UserDto> collection = new ArrayList<>();
        List<User> list = userRepository.findAll();
        for (User user : list) {
            collection.add(fromUser(user));
        }
        return collection;
    }

    public UserDto getUser(String username) {
        UserDto dto = new UserDto();
        Optional<User> user = userRepository.findById(username);
        if (user.isPresent()){
            dto = fromUser(user.get());
        }else {
            throw new UsernameNotFoundException(username);
        }
        return dto;
    }

    public boolean userExists(String username) {
        return userRepository.existsById(username);
    }

    public String createCustomer(CustomerRequestDto customerRequestDto, String username) {
        Long customerId = customerService.addCustomer(customerRequestDto);
        User newUser = userRepository.findById(username).get();
        assignUserDataToUser(customerId, newUser.getUsername());
        return newUser.getUsername();
    }


    public String createUser(UserDto userDto) {
        String randomString = RandomStringGenerator.generateAlphaNumeric(20);
        userDto.setApikey(randomString);
        User user = new User();
        user = toUser(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.addAuthority(new Authority(user.getUsername(), "ROLE_CUSTOMER"));
        userRepository.save(user);
        return user.getUsername();
    }

    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }

    public void updateUser(String username, UserDto newUser) {
        if (!userRepository.existsById(username)) throw new RecordNotFoundException();
        User user = userRepository.findById(username).get();
        user.setPassword(newUser.getPassword());
        userRepository.save(user);
    }

    public Set<Authority> getAuthorities(String username) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        UserDto userDto = fromUser(user);
        return userDto.getAuthorities();
    }
    public void addAuthority(String username, String authority) {

        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        user.addAuthority(new Authority(username, authority));
        userRepository.save(user);
    }

    public void removeAuthority(String username, String authority) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        Authority authorityToRemove = user.getAuthorities().stream().filter((a) -> a.getAuthority().equalsIgnoreCase(authority)).findAny().get();
        user.removeAuthority(authorityToRemove);
        userRepository.save(user);
    }

    public void assignUserDataToUser(Long customerId, String username) {

        var optionalUser = userRepository.findById(username);

        var optionalCustomer = customerRepository.findById(customerId);

        if (optionalCustomer.isPresent() && optionalUser.isPresent()) {

            User user = optionalUser.get();

            Customer customer = optionalCustomer.get();

            user.setCustomer(customer);

            userRepository.save(user);

        }

    }


    public static UserDto fromUser(User user){

        var dto = new UserDto();

        dto.username = user.getUsername();
        dto.password = user.getPassword();
        dto.enabled = user.isEnabled();
        dto.apikey = user.getApikey();
        dto.email = user.getEmail();
        dto.authorities = user.getAuthorities();
        dto.customer = user.getCustomer();

        return dto;
    }

    public User toUser(UserDto userDto) {

        var user = new User();

        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEnabled(userDto.getEnabled());
        user.setApikey(userDto.getApikey());
        user.setEmail(userDto.getEmail());
        user.setCustomer(userDto.getCustomer());

        return user;
    }

}