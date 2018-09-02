package com.practice.messages.controller;

import com.practice.messages.model.Message;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping("messages")
public class MessageRestController {

    private final CopyOnWriteArrayList<Message> messages = new CopyOnWriteArrayList<>();

    @GetMapping
    public List<Message> getMessages() {
        return messages;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<Message> postMessages(@RequestBody Message message) {
        messages.add(message);
        return messages;
    }
}
