package com.patientmanagement.patientservice.controller;

import com.patientmanagement.patientservice.dto.PatientResponseDTO;
import com.patientmanagement.patientservice.dto.patientRequestDTO;
import com.patientmanagement.patientservice.dto.validators.createPatientValidationGroup;
import com.patientmanagement.patientservice.service.PatientService;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public ResponseEntity<PatientResponseDTO> createNewPatient(@Validated({Default.class, createPatientValidationGroup.class}) @RequestBody patientRequestDTO patientRequestDTO){
    PatientResponseDTO response = patientService.createPatient(patientRequestDTO);
    return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> updatePatient(@Validated(Default.class) @RequestBody patientRequestDTO patientRequestDTO, @PathVariable UUID id){
        PatientResponseDTO response = patientService.updatePatient(id,patientRequestDTO);
        return ResponseEntity.ok(response);
    }
}
