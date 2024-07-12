package com.Hadniva.hadnivaBackend.service;

import org.springframework.stereotype.Service;

import com.Hadniva.hadnivaBackend.entity.Message;
import com.Hadniva.hadnivaBackend.repository.MessageRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {
	private MessageRepository messageRepository;
	
	
    public MessageService(MessageRepository messageRepository) {
		super();
		this.messageRepository = messageRepository;
	}

	

    public List<Message> getMessagesByUserId(Long userId) {
        return messageRepository.findByUserId(userId);
    }

    public Message createMessage(Message message) {
        return messageRepository.save(message);
    }

    public Message updateMessage(Message message) {
        return messageRepository.save(message);
    }
    // Method to get messages for chart
    public List<Object[]> getMessagesForChart() {
        return messageRepository.findAll().stream()
                .map(message -> new Object[]{
                        message.getSender().getName(),
                        message.getReceiver().getName(),
                        message.getTimestamp(),
                        message.getContent()
                })
                .collect(Collectors.toList());
    }

    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }
}
