package Project.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Time;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<String> bookAppointment(@RequestBody AppointmentRequest request) {
        String status = appointmentService.bookAppointment(
                request.getDoctorID(),
                request.getPatientID(),
                request.getDate(),
                request.getTime(),
                request.getReason()
        );
        return ResponseEntity.ok("{\"status\": \"" + status + "\"}");
    }
    @PostMapping("/create")
    public ResponseEntity<String> createTimeSlot(@RequestBody AppointmentRequest request) {
        String status = appointmentService.createTimeSlot(
                request.getDoctorID(),
                request.getDate(),
                request.getTime()
        );
        return ResponseEntity.ok("{\"status\": \"" + status + "\"}");
    }
    @PutMapping("/{slotID}")
    public ResponseEntity<String> rescheduleAppointment(@PathVariable Integer slotID, @RequestParam Time newTimeSlot) {
        String status = appointmentService.rescheduleAppointment(slotID, newTimeSlot);
        return ResponseEntity.ok("{\"status\": \"" + status + "\"}");
    }

    @DeleteMapping("/{slotID}")
    public ResponseEntity<String> cancelAppointment(@PathVariable Integer slotID) {
        String status = appointmentService.cancelAppointment(slotID);
        return ResponseEntity.ok("{\"status\": \"" + status + "\"}");
    }
}
