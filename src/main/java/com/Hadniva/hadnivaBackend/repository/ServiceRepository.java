package com.Hadniva.hadnivaBackend.repository;

import com.Hadniva.hadnivaBackend.entity.HadnivaServices;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<HadnivaServices, Long> {
}
