package com.patientmanagement.patientservice.controller;

import com.patientmanagement.patientservice.dto.PatientResponseDTO;
import com.patientmanagement.patientservice.dto.patientRequestDTO;
import com.patientmanagement.patientservice.dto.validators.createPatientValidationGroup;
import com.patientmanagement.patientservice.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.groups.Default;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/patients")
@Tag(name = "patient",description = "API for manage patients")
public class patientController {
    private final PatientService patientService;

    //constructor
    public patientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    @Operation(summary = "Get Patients")
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients(){
    List<PatientResponseDTO> patients = patientService.getPatients();
    return ResponseEntity.ok().body(patients);
    }

    @PostMapping("/new-patient")
    @Operation(summary = "Create New Patient")
    public ResponseEntity<PatientResponseDTO> createNewPatient(@Validated({Default.class, createPatientValidationGroup.class}) @RequestBody patientRequestDTO patientRequestDTO){
    PatientResponseDTO response = patientService.createPatient(patientRequestDTO);
    return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Patient")
    public ResponseEntity<PatientResponseDTO> updatePatient(@Validated(Default.class) @RequestBody patientRequestDTO patientRequestDTO, @PathVariable UUID id){
        PatientResponseDTO response = patientService.updatePatient(id,patientRequestDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Patient")
    public ResponseEntity<Void> deletePatient(@PathVariable UUID id){
        log.info("delete patient with id:{}",id);
        patientService.deletePatient(id);
        return ResponseEntity.ok().build();
    }
}
