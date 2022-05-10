package com.example.websocket_demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SocketUser {
    private String userName;
    private String content;
}
