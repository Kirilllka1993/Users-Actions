package com.example.demo.controller;

import com.example.demo.dto.UserAccountDto;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller()
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/admin/createUser")
    public String createUser (@RequestParam("firstName") String firstName,
                          @RequestParam ("lastName") String lastName,
                          @RequestParam ("password") String password,
                          @RequestParam ("username") String username,
                          @RequestParam ("role") String role,
                          @RequestParam ("status") String status,
                          Model model){
        UserAccountDto userAccountDto=new UserAccountDto();
        userAccountDto.setFirstName(firstName);
        userAccountDto.setLastName(lastName);
        userAccountDto.setPassword(password);
        userAccountDto.setUsername(username);
        userAccountDto.setRole(role);
        userAccountDto.setStatus(status);
        adminService.createNewUser(userAccountDto);
        return "index.html";
    }

    @GetMapping("/admin/userinfo")
    public String productInfo(Model model, String username) {
//        model.addAttribute("username",username);
//        UserAccountDto userAccountDto = adminService.findUserByUsername("kirillka");
//        model.addAttribute("userAccountDto", userAccountDto);
        model.addAttribute("userAccountDto", new UserAccountDto());

        return "registration.html";
    }



}
