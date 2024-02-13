package Project.management;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients/records")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping
    public ResponseEntity<?> addPatientRecord(@RequestBody Patient patient) {
        Patient savedPatient = patientService.addPatientRecord(patient);
        if (savedPatient != null) {
            return ResponseEntity.ok(savedPatient);
        } else {
            return ResponseEntity.status(400).body("Error: Unable to save patient record");
        }
    }

    @GetMapping("/{patientID}")
    public ResponseEntity<?> getPatientRecord(@PathVariable Integer patientID) {
        Optional<Patient> patient = patientService.getPatientRecord(patientID);
        if (patient.isPresent()) {
            return ResponseEntity.ok(patient.get());
        } else {
            return ResponseEntity.status(404).body("{\"status\": \"Error\", \"message\": \"Patient record not found\"}");
        }
    }

    @PutMapping("/{patientID}")
    public ResponseEntity<?> updatePatientRecord(@PathVariable Integer patientID, @RequestBody Patient newData) {
        Patient updatedPatient = patientService.updatePatientRecord(patientID, newData);
        if (updatedPatient != null) {
            return ResponseEntity.ok(updatedPatient);
        } else {
            return ResponseEntity.status(404).body("Error: Patient record not found");
        }
    }

}

