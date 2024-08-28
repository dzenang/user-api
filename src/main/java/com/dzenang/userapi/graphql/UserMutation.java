package com.dzenang.userapi.graphql;

import com.dzenang.userapi.model.CreateUserInput;
import com.dzenang.userapi.model.User;
import com.dzenang.userapi.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@DgsComponent
public class UserMutation {

    private final UserRepository userRepository;

    public UserMutation(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //createUser(input: CreateUserInput!): User!
    @DgsMutation
    public User createUser(DataFetchingEnvironment env) {
        Map<String, Object> input = env.getArgument("input");
        CreateUserInput createUserInput = new ObjectMapper().convertValue(input, CreateUserInput.class);

        if(input != null) {
            User user = new User();
            user.setName(createUserInput.getName());
            user.setEmail(createUserInput.getEmail());
            return userRepository.save(user);
        }
        return null;
    }
}
