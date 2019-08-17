package com.example.demo.controller;

import com.example.demo.dto.UserAccountDto;
import com.example.demo.exception.NoSuchElementException;
import com.example.demo.exception.RepeatitionException;
import com.example.demo.service.AdminService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/user/admin/edit")
    public String changeUserPage(Model model) {
        model.addAttribute("newUserAccountDto", new UserAccountDto());
        return "changeUserByAdmin.html";
    }

    @PutMapping("/user/admin/editUser")
    public String changeUser(@RequestParam("userId") long userId, Model model, UserAccountDto newUserAccountDto) throws NoSuchElementException, RepeatitionException {
        model.addAttribute("userId", userId);
        model.addAttribute("newUserAccountDto", new UserAccountDto());
        adminService.changeUser(userId, newUserAccountDto);
        return "changeUserByAdmin.html";
    }

    //    Вариант редактирования через RequestBody используя аннотацию pathVariable, если выполнить без использования thymeleaf, а используя
//    библиотеку jackson и используя аннотацию RestController (то, есть принять json), то сработает, можно проверитьт через Postman с соответсвующими корректировками
    @PutMapping("/user/admin/{userId}/editUser")
    public String changeUserWithPath(@PathVariable("userId") long userId, Model model, @RequestBody UserAccountDto newUserAccountDto) throws NoSuchElementException, RepeatitionException {
        model.addAttribute("userId", userId);
        model.addAttribute("newUserAccountDto", new UserAccountDto());
        adminService.changeUser(userId, newUserAccountDto);
        return "changeUserByAdmin.html";
    }



}
