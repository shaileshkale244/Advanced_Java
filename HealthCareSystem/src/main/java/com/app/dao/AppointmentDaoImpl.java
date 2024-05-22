package com.app.dao;

import static com.app.utils.DBConnection.closeConnection;
import static com.app.utils.DBConnection.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.core.Appointment;
import com.app.core.AppointmentStatus;

public class AppointmentDaoImpl implements AppontmentDao {
	private Connection cn;
	private PreparedStatement pst1, pst2, pst3;

	public AppointmentDaoImpl() throws SQLException {
		cn = getConnection();
		pst1 = cn.prepareStatement("insert into appointments values(default,?,?,?,?,?,?)");
		pst2 = cn.prepareStatement("update appointments set status='CANCELLED' where appointment_id=?");
		pst3 = cn.prepareStatement("select * from appointments where patient_id=? and status='SCHEDULED'");
	}

	@Override
	public String scheduleAppointment(Appointment apt) throws SQLException {
		pst1.setInt(1, apt.getPatientId());
		pst1.setInt(2, apt.getDoctorId());
		pst1.setDate(3, apt.getAppointmentDate());
		pst1.setTime(4, apt.getAppointmentTime());
		pst1.setInt(5, apt.getDurationMinutes());
		pst1.setString(6, apt.getStatus().name());

		if (pst1.executeUpdate() == 1)
			return "Appointment scheduled....!";
		return "Appointment scheduling failed !";
	}

	@Override
	public String cancelAppointment(int apptId) throws SQLException {
		pst2.setInt(1, apptId);
		if (pst2.executeUpdate() == 1)
			return "Appointment cancelled....!";
		return "Appointment cancelling failed !";
	}

	@Override
	public List<Appointment> getAppointments(int patientId) throws SQLException {
		pst3.setInt(1, patientId);
		List<Appointment> apptList = new ArrayList<>();
		try (ResultSet rst = pst3.executeQuery()) {
			while (rst.next()) {
				apptList.add(new Appointment(rst.getInt(1), rst.getInt(2), rst.getInt(3), rst.getDate(4),
						rst.getTime(5), rst.getInt(6), AppointmentStatus.valueOf(rst.getString(7).toUpperCase())));
			}
		}
		return apptList;
	}
	
	public void cleanup() throws SQLException {
		if (pst1 != null)
			pst1.close();
		if (pst2 != null)
			pst2.close();
		if (pst3 != null)
			pst3.close();
		closeConnection();
	}

}
