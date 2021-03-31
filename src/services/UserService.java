package services;

import domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {
    private List<User> userList = new ArrayList<>();

    public UserService() {
        userList.add(new User("user1@binarycod.com", "user1123"));
        userList.add(new User("user2@binarycod.com", "user2123"));
    }

    public Optional<User> getUserByEmail(String email) {
        return userList
                .stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }

    public Optional<User> getUserByEmailAndPassword(String email, String password){
        return userList
                .stream()
                .filter(user ->
                        (user.getEmail().equals(email)) && (user.getPassword().equals(password
                ))).findFirst();
    }

}
