package com.example.demo.service.impl;

import com.example.demo.converter.UserAccountDtoToUserAccount;
import com.example.demo.converter.UserAccountToUserAccountDto;
import com.example.demo.dao.AdminRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.dto.UserAccountDto;
import com.example.demo.exception.NoSuchElementException;
import com.example.demo.exception.RepeatitionException;
import com.example.demo.model.UserAccount;
import com.example.demo.service.AdminService;
import com.example.demo.service.UserService;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserAccountDtoToUserAccount userAccountDtoToUserAccount;
    @Autowired
    private UserAccountToUserAccountDto userAccountToUserAccountDto;
    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminRepository adminRepository;


    @Override
    public String signIn(UserAccountDto userAccountDto) throws RepeatitionException {
        Optional<UserAccount> checkUser = Optional.ofNullable(adminRepository.findUserAccountByUsername(userAccountDto.getUsername()));
        UserAccount userAccount = null;
        if (checkUser.isPresent() == true) {
            throw new RepeatitionException();
        } else {
            userAccount = userAccountDtoToUserAccount.convert(userAccountDto);
            userRepository.save(userAccount);
        }
        return userAccount.getUsername();
    }

    @Override
    public List<UserAccountDto> getAllUsers() {
        Iterable<UserAccount> userAccounts = userRepository.findAll();
        List<UserAccount> userAcoounts2 = IteratorUtils.toList(userAccounts.iterator());
        List<UserAccountDto> userAccountDtos = userAcoounts2.stream().map(UserAccountDto::new).collect(Collectors.toList());
        return userAccountDtos;
    }

    @Override
    public UserAccountDto viewUserById(long userId) throws NoSuchElementException {
        UserAccount userAccount = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
        UserAccountDto userAccountDto = userAccountToUserAccountDto.convert(userAccount);
        return userAccountDto;
    }

    @Override
    public UserAccountDto viewUserByUserName(String userName) throws NoSuchElementException {
        UserAccount checkUser = Optional.ofNullable(adminRepository.findUserAccountByUsername(userName)).orElseThrow(() -> new NoSuchElementException());
        UserAccountDto userAccountDto = userAccountToUserAccountDto.convert(checkUser);
        return userAccountDto;
    }
}
