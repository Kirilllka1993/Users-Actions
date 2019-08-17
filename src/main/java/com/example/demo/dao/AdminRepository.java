package com.example.demo.dao;

import com.example.demo.dto.UserAccountDto;
import com.example.demo.model.Status;
import com.example.demo.model.UserAccount;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AdminRepository extends CrudRepository<UserAccount, Long> {

    UserAccount findUserAccountByUsername(String username);

    @Modifying
    @Query("update UserAccount user set user.status=?2 where user.id = ?1")
    void updateUserStatus(long userId, Status status);
}
