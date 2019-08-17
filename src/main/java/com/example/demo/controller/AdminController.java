package com.example.demo.controller;

import com.example.demo.dto.UserAccountDto;
import com.example.demo.exception.NoSuchElementException;
import com.example.demo.exception.RepeatitionException;
import com.example.demo.service.AdminService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller()
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;

    @GetMapping("/admin/redirectToCreate")
    public String redirectToCreating(Model model) {
        model.addAttribute("userAccountDto", new UserAccountDto());
        return "registrationByAdmin.html";
    }

    @PostMapping("/admin/userCreate")
    public String createUser(UserAccountDto userAccountDto, Model model) throws RepeatitionException {
        adminService.createNewUser(userAccountDto);
        model.addAttribute("userAccountDto", userAccountDto);
        return "userInfoPage.html";
    }

    @PostMapping("/admin/{userId}/edit")
    public String viewUserById(@PathVariable("userId") long userId, Model model) throws NoSuchElementException {
        return "userInfoPage.html";
    }

    @GetMapping("/admin/changeUser/{userId}")
    public String changeUser(@PathVariable("userId") long userId, Model model) throws NoSuchElementException {
        UserAccountDto useraAccountDto=userService.viewUserById(userId);
        model.addAttribute("useraAccountDto", useraAccountDto);
        model.addAttribute("newUserAccountDto",new UserAccountDto());
        model.addAttribute("userId", userId);
        return "changeUserByAdmin.html";
    }


}
