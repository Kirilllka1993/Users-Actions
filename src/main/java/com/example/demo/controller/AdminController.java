package com.example.demo.controller;

import com.example.demo.dto.UserAccountDto;
import com.example.demo.exception.NoSuchElementException;
import com.example.demo.exception.RepeatitionException;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller()
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/user/admin/redirectToCreate")
    public String redirectToCreating(Model model) {
        model.addAttribute("userAccountDto", new UserAccountDto());
        return "registrationByAdmin.html";
    }

    @PostMapping("/user/admin/userCreate")
    public String createUser(@Valid UserAccountDto userAccountDto,
                             BindingResult bindingResult1,
                             Model model) throws RepeatitionException {
        if (bindingResult1.hasErrors()) {
            return "registration.html";
        } else {
            adminService.createNewUser(userAccountDto);
            model.addAttribute("userAccountDto", userAccountDto);
            return "userInfoPage.html";
        }
    }

    @GetMapping("/user/admin/edit")
    public String changeUserPage(Model model) {
        model.addAttribute("newUserAccountDto", new UserAccountDto());
        return "changeUserByAdmin.html";
    }

    @PostMapping("/user/admin/editUser")
    public String changeUser(@RequestParam("userId") long userId,
                             @Valid UserAccountDto newUserAccountDto,
                             BindingResult bindingResult,
                             Model model
    ) throws NoSuchElementException, RepeatitionException {
        model.addAttribute("userId", userId);
        model.addAttribute("newUserAccountDto", newUserAccountDto);
        if (bindingResult.hasErrors()) {
            return "registration.html";
        } else {
            adminService.changeUser(userId, newUserAccountDto);
            return "userInfoPage.html";
        }
    }

    //    Вариант редактирования через RequestBody используя аннотацию pathVariable, если выполнить без использования thymeleaf, а используя
//    библиотеку jackson и используя аннотацию RestController (то, есть принять json), то сработает, можно проверитьт через Postman с соответсвующими корректировками
    @PutMapping("/user/admin/{userId}/editUser")
    public String changeUserWithPath(@PathVariable("userId") long userId, Model model,
                                     @RequestBody UserAccountDto newUserAccountDto) throws NoSuchElementException, RepeatitionException {
        model.addAttribute("userId", userId);
        model.addAttribute("newUserAccountDto", new UserAccountDto());
        adminService.changeUser(userId, newUserAccountDto);
        return "changeUserByAdmin.html";
    }

    @GetMapping("/user/admin/editStatusPage")
    public String changeStatusPage(Model model) {
        model.addAttribute("status", new String());
        return "updateStatus.html";
    }

    @PutMapping("/user/admin/editStatus")
    public String changeStatus(@RequestParam("userId") long userId,
                               @RequestParam String status,
                               Model model) throws NoSuchElementException {
        model.addAttribute("status", status);
        adminService.changeStatusOfUser(userId, status);
        return "updateStatus.html";
    }


}
