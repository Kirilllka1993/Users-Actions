package com.example.demo.converter;

import com.example.demo.dto.UserAccountDto;
import com.example.demo.model.UserAccount;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserAccountToUserAccountDto implements Converter<UserAccount, UserAccountDto> {

    @Override
    public UserAccountDto convert(UserAccount userAccount) {
        UserAccountDto userAccountDto = new UserAccountDto();
        userAccountDto.setId(userAccount.getId());
        userAccountDto.setFirstName(userAccount.getFirstName());
        userAccountDto.setLastName(userAccount.getLastName());
        userAccountDto.setPassword(userAccount.getPassword());
        userAccountDto.setUsername(userAccount.getUsername());
        userAccountDto.setRole(String.valueOf(userAccount.getRole()));
        userAccountDto.setStatus(String.valueOf(userAccount.getStatus()));
        userAccountDto.setCreatedAt(userAccount.getCreatedAt());
        return userAccountDto;
    }
}
