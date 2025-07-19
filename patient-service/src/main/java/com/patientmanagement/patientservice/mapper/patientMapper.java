package com.patientmanagement.patientservice.mapper;

import com.patientmanagement.patientservice.dto.PatientResponseDTO;
import com.patientmanagement.patientservice.dto.patientRequestDTO;
import com.patientmanagement.patientservice.model.Patient;

public class patientMapper {
    public static PatientResponseDTO toPatientResponseDTO(Patient patient){
       // create new object of patientResponseDTO
        PatientResponseDTO patientResponseDTO = new PatientResponseDTO();

        //set data
        patientResponseDTO.setId(patient.getId().toString());
        patientResponseDTO.setName(patient.getName());
        patientResponseDTO.setAddress(patient.getAddress());
        patientResponseDTO.setDateOfBirth(patient.getBirthDate().toString());
        return patientResponseDTO;
    }

    public static Patient toPatientModal(patientRequestDTO patientRequestDTO){
        Patient patient = new Patient();
        patient.setName(patientRequestDTO.getName());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setRegistrationDate(java.time.LocalDate.parse(patientRequestDTO.getRegisterDate()));
        patient.setBirthDate(java.time.LocalDate.parse(patientRequestDTO.getDateOfBirth()));
        return patient;

    }
}
