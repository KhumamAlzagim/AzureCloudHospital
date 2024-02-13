package Project.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Patient addPatientRecord(Patient patient) {
        return patientRepository.save(patient);
    }

    public Optional<Patient> getPatientRecord(Integer patientID) {
        return patientRepository.findById(patientID);
    }

    public Patient updatePatientRecord(Integer patientID, Patient newData) {
        return patientRepository.findById(patientID)
                .map(existingPatient -> {
                    // Update existing patient details here
                    existingPatient.setFirstName(newData.getFirstName());
                    existingPatient.setLastName(newData.getLastName());
                    existingPatient.setDob(newData.getDob());
                    existingPatient.setGender(newData.getGender());
                    existingPatient.setMedicalHistory(newData.getMedicalHistory());
                    existingPatient.setAllergies(newData.getAllergies());
                    existingPatient.setRadiologyImages(newData.getRadiologyImages());
                    existingPatient.setLabResults(newData.getLabResults());
                    return patientRepository.save(existingPatient);
                })
                .orElseGet(() -> {
                    // Handle the case where the patient ID is not found
                    return null;
                });
    }
}
