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
        Room room = service.createRoom(request);
        Long id = room.getId();
        return "redirect:/main";
    }

    @GetMapping("/room/{id}")
    public String chattingRoom(@PathVariable Long id, Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        String username = (String) session.getAttribute("username");
        if (userId == null) {
            // 로그인되지 않은 사용자가 채팅방에 접속하려고 할 때 처리할 로직 작성
            // 예를 들어 로그인 페이지로 리다이렉트하거나 에러 페이지를 표시할 수 있습니다.
            return "redirect:/user/login";
        }

        // id에 따른 채팅방 접근 권한 체크 로직 작성
        // 예를 들어, 특정 조건에 따라 허용된 채팅방만 접근하도록 할 수 있습니다.

        model.addAttribute("name", username);
        model.addAttribute("userId", userId);
        model.addAttribute("id", id);
        return "/room/chattingRoom";
    }

    @GetMapping("/delete/{id}")
    public String deleteRoom(@PathVariable("id") Long id) {

        service.deleteRoom(id);
        return "redirect:/main";
    }


    @PostMapping("/confirmPwd/{id}")
    @ResponseBody
    public boolean confirmPwd(@PathVariable("id") Long roomId, @RequestParam String password) {

        // 넘어온 roomId 와 roomPwd 를 이용해서 비밀번호 찾기
        // 찾아서 입력받은 roomPwd 와 room pwd 와 비교해서 맞으면 true, 아니면  false
        return service.confirmPwd(roomId, password);
    }

    @GetMapping("/chat/chkUserCnt/{roomId}")
    @ResponseBody
    public boolean chUserCnt(@PathVariable("roomId") Long roomId) {

        return service.chkRoomUserCnt(roomId);
    }

    @GetMapping("/chat/userList")
    @ResponseBody
    public List<String> userList(Long roomId) {

        return service.getUserList(roomId);
    }

}
