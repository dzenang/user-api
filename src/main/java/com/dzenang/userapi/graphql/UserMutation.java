package com.dzenang.userapi.graphql;

import com.dzenang.userapi.model.CreateUserInput;
import com.dzenang.userapi.model.UpdateUserInput;
import com.dzenang.userapi.model.User;
import com.dzenang.userapi.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.Objects;

@DgsComponent
public class UserMutation {

    private final UserRepository userRepository;

    public UserMutation(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @DgsMutation
    public User createUser(DataFetchingEnvironment env) {
        Map<String, Object> input = env.getArgument("input");
        CreateUserInput createUserInput = new ObjectMapper().convertValue(input, CreateUserInput.class);

        User user = new User();
        user.setName(createUserInput.getName());
        user.setEmail(createUserInput.getEmail());
        return userRepository.save(user);
    }

    @DgsMutation
    public User updateUser(DataFetchingEnvironment env) {
        Map<String, Object> input = env.getArgument("input");
        UpdateUserInput updateUserInput = new ObjectMapper().convertValue(input, UpdateUserInput.class);
        Long id = Long.valueOf(Objects.requireNonNull(env.getArgument("id")));

        User user = userRepository.findById(id).orElseThrow();
        user.setName(updateUserInput.getName());
        user.setEmail(updateUserInput.getEmail());
        return userRepository.save(user);
    }

    //	deleteUser(id: ID!): User!
//    @DgsMutation
//    public User deleteUser(DataFetchingEnvironment env) {
//        Long id = Long.valueOf(Objects.requireNonNull(env.getArgument("id")));
//
//        User user = userRepository.findById(id).orElseThrow();
//        userRepository.delete(user);
//        return new User();
//    }
}
