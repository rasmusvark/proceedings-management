package com.example.proceedingsmanagement.business.proceeding;

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

    @PostMapping
    public ResponseEntity<Proceeding> createProceeding(@RequestBody Proceeding proceeding) {
        try {
            Proceeding savedProceeding = proceedingsService.createProceeding(proceeding);
            return new ResponseEntity<>(savedProceeding, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Proceeding>> getAllProceedings() {
        try {
            List<Proceeding> proceedings = proceedingsService.getAllProceedings();
            return new ResponseEntity<>(proceedings, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProceeding(@PathVariable Long id) {
        try {
            proceedingsService.deleteProceeding(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}