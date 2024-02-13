package Project.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;
    public Doctor addDoctor(Map<String, Object> doctorDetails) {
        Doctor newDoctor = new Doctor();

        // Set fields from doctorDetails map if they exist
        if (doctorDetails.containsKey("hospitalID")) {
            newDoctor.setHospitalID((Integer) doctorDetails.get("hospitalID"));
        }
        if (doctorDetails.containsKey("dName")) {
            newDoctor.setdName((String) doctorDetails.get("dName"));
        }
        if (doctorDetails.containsKey("specialty")) {
            newDoctor.setSpecialty((String) doctorDetails.get("specialty"));
        }

        // Save the new doctor object

        Doctor x = doctorRepository.save(newDoctor);
        return x;

    }

    public String updateDoctorDetails(Integer doctorID, Map<String, Object> newDetails) {
        Optional<Doctor> doctorOpt = doctorRepository.findById(doctorID);
        if (!doctorOpt.isPresent()) {
            return "Error: Doctor not found";
        }

        Doctor doctor = doctorOpt.get();


        doctorRepository.save(doctor);
        return "Updated";
    }

    public String deleteDoctor(Integer doctorID) {
        if (doctorRepository.existsById(doctorID)) {
            doctorRepository.deleteById(doctorID);
            return "Deleted";
        }
        return "Error: Doctor not found";
    }
}
