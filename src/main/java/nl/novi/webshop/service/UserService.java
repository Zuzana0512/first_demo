package nl.novi.webshop.service;

import nl.novi.webshop.exeption.RecordNotFoundException;
import nl.novi.webshop.model.Authority;
import nl.novi.webshop.model.User;

import java.util.Optional;

public interface UserService {
    public abstract Iterable<User> getUsers();

    public abstract User getUser(String userName);

    public abstract String addUser (User user);

    public abstract void deleteUser (String userName);

    public abstract void updateUser (String userName, User user);

    public abstract void addAuthority(String userName, String authorityName);

    public abstract void setPassword(String userName, String newPassword);

}
