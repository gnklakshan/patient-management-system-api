package com.patientmanagement.patientservice.controller;

import com.patientmanagement.patientservice.dto.PatientResponseDTO;
import com.patientmanagement.patientservice.dto.patientRequestDTO;
import com.patientmanagement.patientservice.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class patientController {
    private final PatientService patientService;

    //constructor
    public patientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients(){
    List<PatientResponseDTO> patients = patientService.getPatients();
    return ResponseEntity.ok().body(patients);
    }

    @PostMapping("/new-patient")
    public ResponseEntity<PatientResponseDTO> createNewPatient(@Valid @RequestBody patientRequestDTO patientRequestDTO){
    PatientResponseDTO response = patientService.createPatient(patientRequestDTO);
    return ResponseEntity.ok(response);
    }
}
