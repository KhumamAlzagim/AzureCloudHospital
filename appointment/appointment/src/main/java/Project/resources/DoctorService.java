package Project.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

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
