package com.dzenang.userapi.graphql;

import com.dzenang.userapi.model.CreateUserInput;
import com.dzenang.userapi.model.UpdateUserInput;
import com.dzenang.userapi.model.User;
import com.dzenang.userapi.model.entity.UserEntity;
import com.dzenang.userapi.repository.UserEntityRepository;
import com.dzenang.userapi.util.UserMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.Objects;

@DgsComponent
public class UserMutation {

    private final UserEntityRepository userEntityRepository;

    public UserMutation(@Autowired UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    @DgsMutation
    public User createUser(DataFetchingEnvironment env) {
        Map<String, Object> input = env.getArgument("input");
        CreateUserInput createUserInput = new ObjectMapper().convertValue(input, CreateUserInput.class);

        UserEntity userEntity = new UserEntity();
        userEntity.setName(createUserInput.getName());
        userEntity.setEmail(createUserInput.getEmail());
        return UserMapper.toUser(userEntityRepository.save(userEntity));
    }

    @DgsMutation
    public User updateUser(DataFetchingEnvironment env) {
        Map<String, Object> input = env.getArgument("input");
        UpdateUserInput updateUserInput = new ObjectMapper().convertValue(input, UpdateUserInput.class);
        Long id = Long.valueOf(Objects.requireNonNull(env.getArgument("id")));

        UserEntity userEntity = userEntityRepository.findById(id).orElseThrow();
        userEntity.setName(updateUserInput.getName());
        userEntity.setEmail(updateUserInput.getEmail());
        return UserMapper.toUser(userEntityRepository.save(userEntity));
    }

    @DgsMutation
    public User deleteUser(DataFetchingEnvironment env) {
        Long id = Long.valueOf(Objects.requireNonNull(env.getArgument("id")));

        UserEntity userEntity = userEntityRepository.findById(id).orElseThrow();
        User deletedUser = UserMapper.toUser(userEntity);
        userEntityRepository.delete(userEntity);
        return deletedUser;
    }
}
