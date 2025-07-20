package com.patientmanagement.patientservice.service;

import com.patientmanagement.patientservice.dto.PatientResponseDTO;
import com.patientmanagement.patientservice.dto.patientRequestDTO;
import com.patientmanagement.patientservice.exception.EmailAlreadyExistsException;
import com.patientmanagement.patientservice.exception.PatientNotFoundException;
import com.patientmanagement.patientservice.mapper.patientMapper;
import com.patientmanagement.patientservice.model.Patient;
import com.patientmanagement.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {
    final PatientRepository patientRepository;

    //dependency injection
    //constructor for the patient service class
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    //get all patients
    public List<PatientResponseDTO> getPatients(){
        List<Patient> patients = patientRepository.findAll();
        List<PatientResponseDTO> patientResponseDTOs = patients.stream().map(
                patientMapper::toPatientResponseDTO
        ).toList();
        return patientResponseDTOs;

    }

    //create patient
    public PatientResponseDTO createPatient(patientRequestDTO patientRequestDTO){
        if (patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("A patient with this email : " +patientRequestDTO.getEmail()+" is already exists");
        }

        Patient newpatient = patientRepository.save(patientMapper.toPatientModal(patientRequestDTO));
        return patientMapper.toPatientResponseDTO(newpatient);
    }

    //update patient
    public PatientResponseDTO updatePatient(UUID id, patientRequestDTO patientRequestDTO){

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with ID: " + id));

        if (patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(),id)) {
            throw new EmailAlreadyExistsException("A patient with this email : " +patientRequestDTO.getEmail()+" is already exists");
        }

        patient.setName(patientRequestDTO.getName());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setBirthDate(LocalDate.parse(patientRequestDTO.getDateOfBirth()));

        Patient updatedPatient = patientRepository.save(patient);
        return patientMapper.toPatientResponseDTO(updatedPatient);
    }
}
