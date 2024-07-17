package com.Hadniva.hadnivaBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Hadniva.hadnivaBackend.entity.Message;
import com.Hadniva.hadnivaBackend.service.MessageService;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

   
    private final MessageService messageService;
    @Autowired
    public MessageController(MessageService messageService) {
		super();
		this.messageService = messageService;
	}

	@GetMapping("/user/{userId}")
    public ResponseEntity<List<Message>> getMessagesByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(messageService.getMessagesByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        return ResponseEntity.ok(messageService.createMessage(message));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Message> updateMessage(@PathVariable Long id, @RequestBody Message message) {
        message.setId(id);
        return ResponseEntity.ok(messageService.updateMessage(message));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
        messageService.deleteMessage(id);
        return ResponseEntity.ok().build();
    }
}
