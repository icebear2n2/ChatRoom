package com.icebear2n2.chatroom.chat.controller;

import com.icebear2n2.chatroom.chat.domain.entity.Message;
import com.icebear2n2.chatroom.chat.domain.request.MessageRequest;
import com.icebear2n2.chatroom.chat.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;
    @PostMapping
    public String createMessage(@RequestBody MessageRequest request) {
        Message message = messageService.saveMessage(request);
        Long id = message.getRoom().getId();
       return  "redirect:/room/" + id;
    }
}
