package com.dzenang.userapi.graphql;

import com.dzenang.userapi.model.User;
import com.dzenang.userapi.repository.UserRepository;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DgsComponent
public class UsersDataFetcher {

    private final UserRepository userRepository;

    public UsersDataFetcher(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @DgsQuery
    public List<User> users() {
        return (List<User>) userRepository.findAll();
    }

    @DgsQuery
    public User user(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
