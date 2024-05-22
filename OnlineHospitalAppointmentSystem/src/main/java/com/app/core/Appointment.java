package com.app.core;

import java.sql.Date;
import java.sql.Time;

public class Appointment {
	private int id;
	private int patientId;
	private int doctorId;
	private Date appointmentDate;
	private Time appointmentTime;
	private int durationMinutes;
	private AppointmentStatus status;

	public Appointment(int patientId, int doctorId, Date appointmentDate, Time appointmentTime, int durationMinutes,
			AppointmentStatus status) {
		super();
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.appointmentDate = appointmentDate;
		this.appointmentTime = appointmentTime;
		this.durationMinutes = durationMinutes;
		this.status = status;
	}
	

	public Appointment(int id, int patientId, int doctorId, Date appointmentDate, Time appointmentTime,
			int durationMinutes, AppointmentStatus status) {
		super();
		this.id = id;
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.appointmentDate = appointmentDate;
		this.appointmentTime = appointmentTime;
		this.durationMinutes = durationMinutes;
		this.status = status;
	}


	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public Time getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(Time appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public int getDurationMinutes() {
		return durationMinutes;
	}

	public void setDurationMinutes(int durationMinutes) {
		this.durationMinutes = durationMinutes;
	}

	public AppointmentStatus getStatus() {
		return status;
	}

	public void setStatus(AppointmentStatus status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

}
