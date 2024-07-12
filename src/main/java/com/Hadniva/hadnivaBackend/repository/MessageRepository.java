package com.Hadniva.hadnivaBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Hadniva.hadnivaBackend.entity.Message;
import java.util.List;


public interface MessageRepository extends JpaRepository<Message, Long> {
	 List<Message> findByUserId(Long userId);
}

