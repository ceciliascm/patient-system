package com.pm.patientservice.service;

import com.pm.patientservice.dto.CreatePatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.exception.EmailAlreadyExistsException;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();

        return patients.stream().map(PatientMapper::toDTO).toList();
    }

    public PatientResponseDTO createPatient(CreatePatientRequestDTO createPatientRequestDTO) {
        if(patientRepository.existsByEmail((createPatientRequestDTO.getEmail()))) {
            throw new EmailAlreadyExistsException("Patient with email " + createPatientRequestDTO.getEmail() + " already exists");
        }

        Patient newPatient = patientRepository.save(PatientMapper.toModel(createPatientRequestDTO));
        return PatientMapper.toDTO(newPatient);
    }

    public PatientResponseDTO updatePatient(UUID id, CreatePatientRequestDTO createPatientRequestDTO) {
        if(patientRepository.existsByEmail((createPatientRequestDTO.getEmail()))) {
            throw new EmailAlreadyExistsException("Patient with email " + createPatientRequestDTO.getEmail() + " already exists");
        }

        Patient patient = patientRepository.findById(id).orElseThrow(() -> new RuntimeException("Patient not found"));

        patient.setName(createPatientRequestDTO.getName());
        patient.setEmail(createPatientRequestDTO.getEmail());
        patient.setAddress(createPatientRequestDTO.getAddress());
        patient.setDateOfBirth(LocalDate.parse(createPatientRequestDTO.getDateOfBirth()));

        return PatientMapper.toDTO(patientRepository.save(patient));

    }

}
