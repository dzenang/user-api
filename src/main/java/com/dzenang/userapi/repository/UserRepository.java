package com.dzenang.userapi.repository;

import com.dzenang.userapi.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
