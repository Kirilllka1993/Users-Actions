package com.example.demo.controller;

import com.example.demo.dto.UserAccountDto;
import com.example.demo.exception.NoSuchElementException;
import com.example.demo.exception.RepeatitionException;
import com.example.demo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/registration")
    public String addUser(Model model) {
        model.addAttribute("userAccountDto", new UserAccountDto());
        return "registration.html";
    }

    @PostMapping("/signIn")
    public String checkPersonInfo(@Valid UserAccountDto userAccountDto,
                                  BindingResult bindingResult,
                                  Model model) throws RepeatitionException {
        model.addAttribute("userAccountDto", userAccountDto);
        if (bindingResult.hasErrors()) {
            return "registration.html";
        } else {
            String userName = userService.signIn(userAccountDto);
            model.addAttribute("userName", userName);
            return "userInfoPage.html";
        }
    }

    @GetMapping("/user")
    public String goToUserPage(Model model) {
        List<UserAccountDto> userAccountDtos = new ArrayList<>();
        UserAccountDto userAccountDto = new UserAccountDto();
        model.addAttribute("userAccountDtos", userAccountDtos);
        model.addAttribute("userAccountDto", new UserAccountDto());
        return "userInfoPage.html";
    }

    @GetMapping("/user/showUsers")
    public String showUsers(Model model) {
        List<UserAccountDto> userAccountDtos = userService.getAllUsers();
        model.addAttribute("userAccountDtos", userAccountDtos);
        return "users.html";
    }

    @GetMapping("/user/{userId}")
    public String viewUserById(@PathVariable("userId") long userId, Model model) throws NoSuchElementException {
        UserAccountDto useraAccountDto = userService.viewUserById(userId);
        model.addAttribute("useraAccountDto", useraAccountDto);
        model.addAttribute("userId", userId);
        return "userInformation.html ";
    }


}
