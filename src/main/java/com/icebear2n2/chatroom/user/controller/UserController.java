package com.icebear2n2.chatroom.user.controller;

import com.icebear2n2.chatroom.user.domain.entity.User;
import com.icebear2n2.chatroom.user.domain.request.LoginRequest;
import com.icebear2n2.chatroom.user.domain.request.SignupRequest;
import com.icebear2n2.chatroom.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    @GetMapping("/signup")
    public String showsignupPage() { return "/user/signup";}

    @PostMapping("/signup")
    public String signup(@ModelAttribute("user") @Validated SignupRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return "/user/signup";
        }

        try {
            service.signup(request);
            return "redirect:/user/login";
        } catch (RuntimeException e) {
            result.rejectValue("email", "EXIST EMAIL", "FAILED TO SIGNUP USER!!" + e.getMessage());
            return "/user/signup";
        }
    }

    @GetMapping("/login")
    public String showLoginPage() { return "/user/login"; }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") @Validated LoginRequest request, HttpSession session, BindingResult result) {
        if (result.hasErrors()) {
            return "/user/login";
        }

        User login = service.login(request);
        if (login != null) {
            session.setAttribute("userId", login.getId());
            session.setAttribute("username", login.getUsername());
        }
        return "redirect:/main";
    }
}
