package com.Hadniva.hadnivaBackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private LocalDateTime timestamp;
    private Long userId;
    
    
  
	@ManyToOne
    private User sender;
    @ManyToOne
    private User receiver;
    
    
	public Message() {
		super();
	}


	public Message(Long id, String content, LocalDateTime timestamp, User sender, User receiver) {
		super();
		this.id = id;
		this.content = content;
		this.timestamp = timestamp;
		this.sender = sender;
		this.receiver = receiver;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	 public Long getUserId() {
			return userId;
	}
	public void setUserId(Long userId) {
			this.userId = userId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public User getSender() {
		return sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	public User getReceiver() {
		return receiver;
	}
	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}
    
}


