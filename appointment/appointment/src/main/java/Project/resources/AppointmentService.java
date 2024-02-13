package Project.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.sql.Time;
import java.util.Map;
import java.util.Optional;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private SlotRepository slotRepository;

    // Book an appointment
    public String bookAppointment(Integer doctorID, Integer patientID, Date date, Time time, String reason) {
        Optional<Doctor> doctorOpt = doctorRepository.findById(doctorID);
        if (!doctorOpt.isPresent()) {
            return "Error: Doctor not found";
        }

        List<Slot> slots = slotRepository.findByDoctorAndDate(doctorOpt.get(), date);
        for (Slot slot : slots) {
            if (slot.getTime().equals(time) && slot.getPatientID() == null) {
                slot.setPatientID(patientID);
                slot.setReason(reason);
                slotRepository.save(slot);
                return "Booked";
            }
        }
        return "Error: Slot not available";
    }

    public String createTimeSlot(Integer doctorID, Date date, Time time) {
        Optional<Doctor> doctorOpt = doctorRepository.findById(doctorID);
        if (!doctorOpt.isPresent()) {
            return "Error: Doctor not found";
        }

        // Check if a slot already exists for the specified date and time
        List<Slot> existingSlots = slotRepository.findByDoctorAndDateAndTime(doctorOpt.get(), date, time);
        if (!existingSlots.isEmpty()) {
            return "Error: Time slot already exists";
        }

        // Create a new time slot
        Slot newSlot = new Slot();
        newSlot.setDoctor(doctorOpt.get());
        newSlot.setDate(date);
        newSlot.setTime(time);
        newSlot.setReason("Available"); // Set the reason as available initially
        newSlot.setPatientID(null); // Set the patient ID as null to indicate availability
        slotRepository.save(newSlot);

        return "Time slot created";
    }

    // Reschedule an appointment
    public String rescheduleAppointment(Integer slotID, Time newTimeSlot) {
        Optional<Slot> slotOpt = slotRepository.findById(slotID);
        if (!slotOpt.isPresent()) {
            return "Error: Appointment not found";
        }

        Slot slot = slotOpt.get();
        slot.setTime(newTimeSlot);
        slotRepository.save(slot);
        return "Rescheduled";
    }

    // Cancel an appointment
    public String cancelAppointment(Integer slotID) {
        Optional<Slot> slotOpt = slotRepository.findById(slotID);
        if (!slotOpt.isPresent()) {
            return "Error: Appointment not found";
        }

        slotRepository.deleteById(slotID);
        return "Cancelled";
    }
}
