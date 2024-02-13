package Project.resources;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.sql.Date;
import java.sql.Time;
public interface SlotRepository extends JpaRepository<Slot, Integer> {
    List<Slot> findByDoctorAndDate(Doctor doctor, java.sql.Date date);
    List<Slot> findByDoctorAndDateAndTime(Doctor doctor, Date date, Time time);

}
