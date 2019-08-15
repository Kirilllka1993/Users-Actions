package com.example.demo.service.impl;

import com.example.demo.converter.NewUserDtoToUserAccount;
import com.example.demo.converter.UserAccountDtoToUserAccount;
import com.example.demo.converter.UserAccountToUserAccountDto;
import com.example.demo.dao.AdminRepository;
import com.example.demo.dto.UserAccountDto;
import com.example.demo.exception.NoSuchElementException;
import com.example.demo.exception.RepeatitionException;
import com.example.demo.model.UserAccount;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private UserAccountDtoToUserAccount userAccountDtoToUserAccount;
    @Autowired
    private UserAccountToUserAccountDto userAccountToUserAccountDto;
    @Autowired
    private NewUserDtoToUserAccount newUserDtoToUserAccount;

    @Override
    public void createNewUser(UserAccountDto userAccountDto) throws RepeatitionException {
        Optional<UserAccount> checkUser = Optional.ofNullable(adminRepository.findUserAccountByUsername(userAccountDto.getUsername()));
        UserAccount userAccount = null;
        if (checkUser.isPresent() == true) {
            throw new RepeatitionException();
        } else {
            userAccount = newUserDtoToUserAccount.convert(userAccountDto);
            adminRepository.save(userAccount);
        }
    }

    @Override
    public UserAccountDto changeUser(long userId, UserAccountDto newUserAccountDto) throws NoSuchElementException {
        UserAccount userAccount = adminRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
        UserAccount checkUser = Optional.ofNullable(adminRepository.findUserAccountByUsername(newUserAccountDto.getUsername())).orElseThrow(() -> new NoSuchElementException());


        return null;
    }

    @Override
    public UserAccountDto changeStatusOfUser(long userId, UserAccountDto userAccountDto) {
        return null;
    }

    @Override
    public UserAccountDto findUserByUsername(String username) {
        UserAccount userAccount = adminRepository.findUserAccountByUsername(username);
        UserAccountDto userAccountDto = userAccountToUserAccountDto.convert(userAccount);
        return userAccountDto;
    }


}
