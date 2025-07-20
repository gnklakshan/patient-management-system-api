package com.patientmanagement.patientservice.service;

import com.patientmanagement.patientservice.dto.PatientResponseDTO;
import com.patientmanagement.patientservice.dto.patientRequestDTO;
import com.patientmanagement.patientservice.exception.EmailAlreadyExistsException;
import com.patientmanagement.patientservice.mapper.patientMapper;
import com.patientmanagement.patientservice.model.Patient;
import com.patientmanagement.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    final PatientRepository patientRepository;

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
        if (patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("A patient with this email : " +patientRequestDTO.getEmail()+" is already exists");
        }

        Patient newpatient = patientRepository.save(patientMapper.toPatientModal(patientRequestDTO));
        return patientMapper.toPatientResponseDTO(newpatient);
    }
}
