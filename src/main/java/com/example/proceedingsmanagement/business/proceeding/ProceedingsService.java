package com.example.proceedingsmanagement.business.proceeding;

import org.springframework.stereotype.Service;
import com.example.proceedingsmanagement.domain.proceeding.Proceeding;
import com.example.proceedingsmanagement.domain.proceeding.ProceedingRepository;

import jakarta.annotation.Resource;
import java.util.List;

@Service
public class ProceedingsService {

    @Resource
    private ProceedingRepository proceedingRepository;

    public Proceeding createProceeding(Proceeding proceeding) {
        // Save the new proceeding to the database
        return proceedingRepository.save(proceeding);
    }

    public List<Proceeding> getAllProceedings() {
        // Retrieve all proceedings from the database
        return proceedingRepository.findAll();
    }
}