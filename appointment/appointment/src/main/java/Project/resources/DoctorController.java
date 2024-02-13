package Project.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PutMapping("/{doctorID}")
    public ResponseEntity<String> updateDoctorDetails(@PathVariable Integer doctorID, @RequestBody Map<String, Object> newDetails) {
        String status = doctorService.updateDoctorDetails(doctorID, newDetails);
        return ResponseEntity.ok("{\"status\": \"" + status + "\"}");
    }

    @DeleteMapping("/{doctorID}")
    public ResponseEntity<String> deleteDoctor(@PathVariable Integer doctorID) {
        String status = doctorService.deleteDoctor(doctorID);
        return ResponseEntity.ok("{\"status\": \"" + status + "\"}");
    }
}
