package com.example.demo.service;

import com.example.demo.dto.UserAccountDto;
import com.example.demo.exception.NoSuchElementException;
import com.example.demo.exception.RepeatitionException;


public interface AdminService {

    void createNewUser(UserAccountDto userAccountDto) throws RepeatitionException;

    void changeUser(long userId, UserAccountDto newUserAccountDto) throws NoSuchElementException, RepeatitionException;

    void changeStatusOfUser(long userId, String status) throws NoSuchElementException;

    UserAccountDto findUserByUsername(String username);


}
