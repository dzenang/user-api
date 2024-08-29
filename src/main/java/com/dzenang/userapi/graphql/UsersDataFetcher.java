package com.dzenang.userapi.graphql;

import com.dzenang.userapi.model.User;
import com.dzenang.userapi.repository.UserEntityRepository;
import com.dzenang.userapi.util.UserMapper;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DgsComponent
public class UsersDataFetcher {

    private final UserEntityRepository userEntityRepository;

    public UsersDataFetcher(@Autowired UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    @DgsQuery
    public List<User> users() {
        return userEntityRepository.findAll()
                .stream()
                .map(UserMapper::toUser)
                .toList();
    }

    @DgsQuery
    public User user(Long id) {
        return UserMapper.toUser(userEntityRepository.findById(id).orElseThrow());
    }
}
