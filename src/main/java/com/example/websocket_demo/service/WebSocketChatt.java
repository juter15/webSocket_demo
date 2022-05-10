package com.example.websocket_demo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Collections;

@Service
@RequiredArgsConstructor
@ServerEndpoint(value = "/chat")
@Slf4j
public class WebSocketChatt {
    private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(Session s) {
        log.info("open session: {}", s.toString());
        if (!clients.contains(s)) {
            clients.add(s);
            log.info("session open : {}", s);
        } else {
            log.info("이미 연결된  session");
        }

    }

    @OnMessage
    public void onMessage(String msg, Session session) throws IOException {
        log.info("receive message : ", msg);
        for (Session s : clients) {
            log.info("send data : {}", msg);
            s.getBasicRemote().sendText(msg);
        }
    }

    @OnClose
    public void onClose(Session s) {
        log.info("session close: {}", s);
        clients.remove(s);

    }
}
