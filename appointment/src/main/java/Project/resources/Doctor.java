package Project.resources;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer doctorID;
	private Integer hospitalID;
	private String dName;
	private String specialty;

	public void setDoctorID(Integer doctorID) {
		this.doctorID = doctorID;
	}

	public void setHospitalID(Integer hospitalID) {
		this.hospitalID = hospitalID;
	}

	public void setdName(String dName) {
		this.dName = dName;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}


	public Integer getDoctorID() {
		return doctorID;
	}

	public Integer getHospitalID() {
		return hospitalID;
	}

	public String getdName() {
		return dName;
	}

	public String getSpecialty() {
		return specialty;
	}


}

