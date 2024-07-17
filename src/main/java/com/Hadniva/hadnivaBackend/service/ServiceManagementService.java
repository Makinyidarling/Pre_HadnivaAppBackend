package com.Hadniva.hadnivaBackend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Hadniva.hadnivaBackend.entity.HadnivaServices;
import com.Hadniva.hadnivaBackend.exception.ResourceNotFoundException;
import com.Hadniva.hadnivaBackend.repository.ServiceRepository;

import java.io.IOException;
import java.util.List;

@Service
public class ServiceManagementService {

    private final ServiceRepository serviceRepository;

    public ServiceManagementService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<HadnivaServices> getAllServices() {
        return serviceRepository.findAll();
    }

    public HadnivaServices getServiceById(Long id) {
        return serviceRepository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("Service not found with id " + id));
    }

    public HadnivaServices createService(HadnivaServices service, MultipartFile image) throws IOException {
        if (image != null && !image.isEmpty()) {
            service.setImage(image.getBytes());
        }
        return serviceRepository.save(service);
    }

    public HadnivaServices updateService(Long id, HadnivaServices updatedService, MultipartFile image) throws IOException {
        HadnivaServices existingService = serviceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Service not found with id " + id));
        existingService.setName(updatedService.getName());
        existingService.setDescription(updatedService.getDescription());
        if (image != null && !image.isEmpty()) {
            existingService.setImage(image.getBytes());
        }
        return serviceRepository.save(existingService);
    }

    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }
}
