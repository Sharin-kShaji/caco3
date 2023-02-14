package com.example.demoservice.service;


import com.example.demoservice.config.MapperConfig;
import com.example.demoservice.dto.CreateDesignationRequest;
import com.example.demoservice.exception.DesignationServiceException;
import com.example.demoservice.model.Designation;
import com.example.demoservice.repository.DesignationRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Slf4j
public class DesignationService {

    private DesignationRepository designationRepository;

    private MapperConfig mapperConfig;

    @Autowired
    public DesignationService(DesignationRepository designationRepository, MapperConfig mapperConfig) {
        this.designationRepository = designationRepository;
        this.mapperConfig = mapperConfig;
    }


    @SneakyThrows
    @Transactional
    public void createDesignation(CreateDesignationRequest request) {
     //   Optional<Designation> designation = designationRepository.findByTitleIgnoreCase(request.getTitle());
//        if(designation.isPresent() && request.getTitle().toLowerCase().equals(designation.get().getTitle())) {
//            throw new DesignationServiceException("Title already exist");
//        }
//        if(designationRepository.countByTitleIgnoreCase(request.getTitle()) > 1) {
//            throw new DesignationServiceException("Title already exist");
//        }
        if(designationRepository.countByTitleIgnoreCase(request.getTitle()) >= 1) {
            log.error("Title {} already exist", request.getTitle());
            throw new DesignationServiceException("Title already exist");
        }
        log.info("creating designation");
        Designation entity  = mapperConfig.getMapper().map(request, Designation.class);
        designationRepository.save(entity);
        log.info("Designation with id {} created", entity.getId());
    }


}
