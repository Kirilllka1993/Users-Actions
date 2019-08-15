package com.example.demo.dao;

import com.example.demo.model.UserAccount;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<UserAccount,Long> {

    UserAccount findUserAccountByUsername(String username);
}
