package com.example.demo.service;

import com.example.demo.dto.UserAccountDto;
import com.example.demo.exception.NoSuchElementException;
import com.example.demo.exception.RepeatitionException;

import java.util.List;

public interface AdminService {

    void createNewUser(UserAccountDto userAccountDto) throws RepeatitionException;

    void changeUser(long userId, UserAccountDto newUserAccountDto) throws NoSuchElementException, RepeatitionException;

    UserAccountDto changeStatusOfUser(long userId, String status);

    UserAccountDto findUserByUsername(String username);


}
