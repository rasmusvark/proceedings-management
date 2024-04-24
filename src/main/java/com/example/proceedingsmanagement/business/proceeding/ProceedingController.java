package com.example.proceedingsmanagement.business.proceeding;

import com.example.proceedingsmanagement.business.rabbit.MessageSender;
import com.example.proceedingsmanagement.domain.proceeding.Proceeding;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/proceedings")
public class ProceedingController {

    @Resource
    private ProceedingsService proceedingsService;
    @Resource
    private MessageSender messageSender;

    @PostMapping
    public ResponseEntity<Proceeding> createProceeding(@RequestBody Proceeding proceeding) {
        Proceeding savedProceeding = proceedingsService.createProceeding(proceeding);
        messageSender.sendMessage("New proceeding initiated: " + proceeding.getName());
        return new ResponseEntity<>(savedProceeding, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Proceeding>> getAllProceedings() {
        List<Proceeding> proceedings = proceedingsService.getAllProceedings();
        return ResponseEntity.ok(proceedings);
    }
}