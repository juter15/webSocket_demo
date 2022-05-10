package com.example.websocket_demo.controller;

import com.example.websocket_demo.model.SocketUser;
import com.example.websocket_demo.service.WebSocketChatt;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@Slf4j
public class WebSocketController {
    @MessageMapping("/receive")
    @SendTo("/send")
    public SocketUser SocketHandler(SocketUser socketUser){
        log.info("user: {}", socketUser);
        String userName = socketUser.getUserName();
        String content = socketUser.getContent();
        SocketUser socketUser1 = new SocketUser(userName, content);
        log.info("socket: {}", socketUser1);
        return socketUser1;
    }
}
