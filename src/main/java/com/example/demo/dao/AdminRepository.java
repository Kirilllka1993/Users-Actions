package com.example.demo.dao;

import com.example.demo.dto.UserAccountDto;
import com.example.demo.model.UserAccount;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AdminRepository extends CrudRepository<UserAccount,Long> {

    UserAccount findUserAccountByUsername(String username);

//    @Modifying
//    @Query("update UserAccount user set userAccount.lastName = ?1, u.lastname = ?2 where u.id = ?1")
//    void setUserInfoById(long userAccountId, UserAccount userAccount);

//    @Modifying
//    @Query("UPDATE FuneralLead flead SET flead.funeralLeadStatus = :status, flead.isActive = :isActive WHERE flead.id = :userId")
//    void updateFuneralLeadStatus(@Param("userId") Long userId, @Param("status") FuneralLeadStatus funeralLeadStatus,
//                                 @Param("isActive") Boolean isActive);
}
