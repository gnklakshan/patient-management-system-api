package com.patientmanagement.patientservice.service;

import com.patientmanagement.patientservice.dto.PatientResponseDTO;
import com.patientmanagement.patientservice.dto.patientRequestDTO;
import com.patientmanagement.patientservice.mapper.patientMapper;
import com.patientmanagement.patientservice.model.Patient;
import com.patientmanagement.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {
    private PatientRepository patientRepository;

    //dependency injection
    //constructor for the patient service class
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getPatients(){
        List<Patient> patients = patientRepository.findAll();
        List<PatientResponseDTO> patientResponseDTOs = patients.stream().map(
                patientMapper::toPatientResponseDTO
        ).toList();
        return patientResponseDTOs;

    }

    public PatientResponseDTO createPatient(patientRequestDTO patientRequestDTO){
        Patient newpatient = patientRepository.save(patientMapper.toPatientModal(patientRequestDTO));
        return patientMapper.toPatientResponseDTO(newpatient);
    }
}
