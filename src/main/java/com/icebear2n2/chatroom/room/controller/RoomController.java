package com.icebear2n2.chatroom.room.controller;

import com.icebear2n2.chatroom.room.domain.entity.Room;
import com.icebear2n2.chatroom.room.domain.request.RoomRequest;
import com.icebear2n2.chatroom.room.domain.response.RoomResponse;
import com.icebear2n2.chatroom.room.service.RoomService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class RoomController {

    private final RoomService service;
    @GetMapping("/main")
    public String showMainPage(Model model) {
        List<RoomResponse> all = service.findAll();
        System.out.println("all = " + all);
        model.addAttribute("list", all);
        return "/main";
    }


//    @GetMapping("/room/create")
//    public String showCreateRoomPage(Model model) {
//
//    }
    @PostMapping("/main")
    public String createRoom(@ModelAttribute RoomRequest request) {
      service.createRoom(request);

        return "redirect:/main";
    }
}
