package com.example.demo.converter;

//import com.example.demo.dto.UserAccountDto;
import com.example.demo.dto.UserAccountDto;
import com.example.demo.model.Role;
import com.example.demo.model.Status;
import com.example.demo.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component
public class UserAccountDtoToUserAccount implements Converter<UserAccountDto, UserAccount> {

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserAccount convert(UserAccountDto userAccountDto) {
        UserAccount userAccount=new UserAccount();
        userAccount.setFirstName(userAccountDto.getFirstName());
        userAccount.setLastName(userAccountDto.getLastName());
        userAccount.setPassword(encoder.encode(userAccountDto.getPassword()));
        userAccount.setUsername(userAccountDto.getUsername());
        userAccount.setRole(Role.USER);
        userAccount.setStatus(Status.ACTIVE);
        userAccount.setCreatedAt(LocalDate.now());
        return userAccount;
    }
}
