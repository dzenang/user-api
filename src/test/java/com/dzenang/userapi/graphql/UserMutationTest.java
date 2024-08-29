package com.dzenang.userapi.graphql;

import com.dzenang.userapi.fixtures.UserFixtures;
import com.dzenang.userapi.model.entity.UserEntity;
import com.dzenang.userapi.repository.UserEntityRepository;
import com.netflix.graphql.dgs.DgsQueryExecutor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class UserMutationTest {

    @Autowired
    private UserEntityRepository userEntityRepository;
    @Autowired
    private DgsQueryExecutor dgsQueryExecutor;
    private UserEntity userEntity;

    @BeforeEach
    void setUp() {
        userEntity = userEntityRepository.save(UserFixtures.createUserEntity1(false));
    }

    @Test
    void createUser() {
        Map<String, Object> variables = new HashMap<>();
        variables.put("input", Map.of("name",UserFixtures.createUserInput1.getName(),
                "email",UserFixtures.createUserInput1.getEmail()));
        String createdUserName = dgsQueryExecutor.executeAndExtractJsonPath(
                """
                        mutation CreateUser($input: CreateUserInput!) {
                            createUser(input: $input) {
                                name
                            }
                        }
                        """,
                "data.createUser.name",
                variables);

        Assertions.assertEquals(UserFixtures.userName1, createdUserName);
    }

    @Test
    void updateUser() {
        Map<String, Object> variables = new HashMap<>();
        variables.put("input", Map.of("name",UserFixtures.updateUserInput2.getName(),
                "email",UserFixtures.updateUserInput2.getEmail()));
        variables.put("id", userEntity.getId());
        String updatedUserName = dgsQueryExecutor.executeAndExtractJsonPath(
                """
                        mutation UpdateUser($id: ID!, $input: UpdateUserInput!) {
                                updateUser(id: $id, input: $input) {
                                    name
                                }
                        }
                        """,
                "data.updateUser.name",
                variables);

        Assertions.assertEquals(UserFixtures.userName2, updatedUserName);
    }

    @Test
    void deleteUser() {
        Map<String, Object> variables = new HashMap<>();
        variables.put("id", userEntity.getId());
        String deletedUserName = dgsQueryExecutor.executeAndExtractJsonPath(
                """
                        mutation DeleteUser($id: ID!) {
                            deleteUser(id: $id) {
                                name
                            }
                        }
                        """,
                "data.deleteUser.name",
                variables);
        Assertions.assertEquals(UserFixtures.userName1, deletedUserName);
    }

    @AfterEach
    void tearDown() {
        userEntityRepository.deleteAll();
    }
}