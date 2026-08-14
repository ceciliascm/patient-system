package com.pm.patientservice.mapper;

import com.pm.patientservice.dto.CreatePatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.model.Patient;

import java.time.LocalDate;

public class PatientMapper {
    public static PatientResponseDTO toDTO(Patient patient) {
        PatientResponseDTO patientDTO = new PatientResponseDTO();
        patientDTO.setId(patient.getId().toString());
        patientDTO.setName(patient.getName());
        patientDTO.setEmail(patient.getEmail());
        patientDTO.setAddress(patient.getAddress());
        patientDTO.setDateOfBirth(patient.getDateOfBirth().toString());


        return patientDTO;
    }

    public static Patient toModel(CreatePatientRequestDTO createPatientRequestDTO) {
        Patient patient = new Patient();
        patient.setName(createPatientRequestDTO.getName());
        patient.setEmail(createPatientRequestDTO.getEmail());
        patient.setAddress(createPatientRequestDTO.getAddress());
        patient.setDateOfBirth(LocalDate.parse(createPatientRequestDTO.getDateOfBirth()));
        patient.setRegisteredDate(LocalDate.parse(createPatientRequestDTO.getRegisteredDate()));
        return patient;
    }
}
