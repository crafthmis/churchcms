package com.techbridge.apis.controller;

import com.techbridge.apis.model.Message;
import com.techbridge.apis.model.dto.MessageDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class MessageController extends BaseController {

    @GetMapping("/messages")
    public ResponseEntity<Object> getCounties() {
        return messageService.getMessages();
    }


    @GetMapping("/message/{id}")
    public ResponseEntity<Object> getMessage(@PathVariable Long id) {
        return messageService.getMessage(id);
    }

    @PostMapping("/message/add")
    public ResponseEntity<Object> addNewMessage(@RequestBody Message message) {
        return messageService.createNewMessage(message);
    }

    @DeleteMapping("/message/{id}")
    public ResponseEntity<Object> deleteMessage(@PathVariable Long id) {
        return messageService.deleteMessage(id);
    }

    @PutMapping("/message/{id}")
    public ResponseEntity<Object> updateMessage(@PathVariable("id") Long id, @RequestParam(required = false) MessageDto dto) {
        return messageService.updateMessage(id, dto);
    }
}
