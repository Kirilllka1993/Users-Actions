package com.example.demo.service;

import com.example.demo.dto.UserAccountDto;
import com.example.demo.exception.NoSuchElementException;
import com.example.demo.exception.RepeatitionException;

import java.util.List;

public interface UserService {

    String signIn(UserAccountDto userAccountDto) throws RepeatitionException;

    List<UserAccountDto> getAllUsers();

    UserAccountDto viewUserByUserName(String userName) throws NoSuchElementException;

    UserAccountDto viewUserById(long userId) throws NoSuchElementException;

}
