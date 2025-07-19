package com.patientmanagement.patientservice.mapper;

import com.patientmanagement.patientservice.dto.PatientResponseDTO;
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
}
