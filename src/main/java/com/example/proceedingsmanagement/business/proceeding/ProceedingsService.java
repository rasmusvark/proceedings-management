package com.example.proceedingsmanagement.business.proceeding;

import com.example.proceedingsmanagement.business.rabbit.MessageSender;
import com.example.proceedingsmanagement.domain.proceeding.Proceeding;
import com.example.proceedingsmanagement.domain.proceeding.ProceedingRepository;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProceedingsService {

    @Resource
    private ProceedingRepository proceedingRepository;
    
    @Resource 
    private MessageSender messageSender;

    public Proceeding createProceeding(Proceeding proceeding) throws Exception {
        try {
            boolean messageSent = messageSender.sendMessage("New proceeding initiated: " + proceeding.getName());
            proceeding.setEmailSent(messageSent); 
            return proceedingRepository.save(proceeding);
        } catch (Exception e) {
            throw new Exception("Error creating proceeding", e);
        }
    }

    public List<Proceeding> getAllProceedings() throws Exception {
        try {
            return proceedingRepository.findAll();
        } catch (Exception e) {
            throw new Exception("Error retrieving proceedings", e);
        }
    }

    public void deleteProceeding(Long id) throws Exception {
        try {
            proceedingRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception("Error deleting proceeding", e);
        }
    }
}