package es.jdamiancabello.inventory.data.repository;

import java.util.ArrayList;
import java.util.List;

import es.jdamiancabello.inventory.data.model.User;

public class UserRepository {
    private static UserRepository userRepository;
    private List<User> userList;

    public static UserRepository getInstance(){
        if(userRepository==null)
            userRepository = new UserRepository();
        return userRepository;
    }

    private UserRepository(){
        userList = new ArrayList<>();
        initialice();
    }

    private void initialice() {
        userList.add(new User("damian","damian@damian.es","damian"));
        userList.add(new User("","",""));
    }

    public List<User> getDependencyList(){
        return userList;
    }
}
