package com.Hadniva.hadnivaBackend.controller;
import com.Hadniva.hadnivaBackend.entity.HadnivaServices;
import com.Hadniva.hadnivaBackend.service.ServiceManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/services")
public class ServiceController {
    private final ServiceManagementService serviceManagementService;

    @Autowired
    public ServiceController(ServiceManagementService serviceManagementService) {
        this.serviceManagementService = serviceManagementService;
    }

    @GetMapping
    public ResponseEntity<List<HadnivaServices>> getAllServices() {
        return ResponseEntity.ok(serviceManagementService.getAllServices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HadnivaServices> getServiceById(@PathVariable Long id) {
        return ResponseEntity.ok(serviceManagementService.getServiceById(id));
    }

    @PostMapping
    public ResponseEntity<HadnivaServices> createService(@RequestParam("name") String name,
                                                         @RequestParam("description") String description,
                                                         @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {
        HadnivaServices service = new HadnivaServices();
        service.setName(name);
        service.setDescription(description);
        return ResponseEntity.ok(serviceManagementService.createService(service, image));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HadnivaServices> updateService(@PathVariable Long id,
                                                         @RequestParam("name") String name,
                                                         @RequestParam("description") String description,
                                                         @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {
        HadnivaServices updatedService = new HadnivaServices();
        updatedService.setName(name);
        updatedService.setDescription(description);
        return ResponseEntity.ok(serviceManagementService.updateService(id, updatedService, image));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        serviceManagementService.deleteService(id);
        return ResponseEntity.ok().build();
    }
}
