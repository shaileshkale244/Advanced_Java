package com.app.dao;

import java.sql.SQLException;
import java.util.List;

import com.app.core.Appointment;

public interface AppontmentDao {
	// Schedule new appointment
	String scheduleAppointment(Appointment apt) throws SQLException;

	// cancel scheduled appointment
	String cancelAppointment(int apptId) throws SQLException;

	// all appointments of patient
	List<Appointment> getAppointments(int patientId) throws SQLException;

}
