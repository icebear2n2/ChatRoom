package com.icebear2n2.chatroom.user.controller;

import com.icebear2n2.chatroom.user.domain.entity.User;
import com.icebear2n2.chatroom.user.domain.request.ConnectRequest;
import com.icebear2n2.chatroom.user.domain.request.LoginRequest;
import com.icebear2n2.chatroom.user.domain.request.SignupRequest;
import com.icebear2n2.chatroom.user.domain.response.UserResponse;
import com.icebear2n2.chatroom.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    @GetMapping("/signup")
    public String showSignupPage() {
        return "/user/signup";
    }

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
    public String showLoginPage() {
        return "/user/login";
    }

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

    @GetMapping("/join/{id}")
    public String joinRoom(@PathVariable("id") Long id, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/user/login";
        }

        ConnectRequest connectRequest = new ConnectRequest(userId, id);
        try {
            service.joinRoom(connectRequest);
            return "redirect:/room/{id}";
        } catch (RuntimeException e) {

            return "redirect:/error";
        }
    }

    @PostMapping("/leave/{id}")
    public String leaveRoom(@PathVariable("id") Long id, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/user/login";
        }

        ConnectRequest connectRequest = new ConnectRequest(userId, id);
        try {
            service.leaveRoom(connectRequest);

            return "redirect:/main";
        } catch (RuntimeException e) {

            return "redirect:/error";
        }
    }

    @GetMapping("{id}")
    public UserResponse getById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {

        // 세션에서 사용자 ID를 가져와서 uid에 저장
        Long uid = (Long) session.getAttribute("userId");

        // uid가 존재하면, 세션 무효화
        if (uid != null) {
            session.invalidate();
        }

        return "redirect:/main";
    }

}
