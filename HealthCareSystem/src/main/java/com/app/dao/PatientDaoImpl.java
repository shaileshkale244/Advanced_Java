package com.app.dao;

import static com.app.utils.DBConnection.closeConnection;
import static com.app.utils.DBConnection.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.core.Patient;

public class PatientDaoImpl implements PatientDao {
	private Connection cn;
	private PreparedStatement pst1, pst2;

	public PatientDaoImpl() throws SQLException {
		cn = getConnection();
		pst1 = cn.prepareStatement("select * from patients where email=? and password=?");
		pst2 = cn.prepareStatement("insert into patients values(default,?,?,?,?)");
	}

	@Override
	public Patient signIn(String email, String password) throws SQLException {
		pst1.setString(1, email);
		pst1.setString(2, password);
		try (ResultSet rst = pst1.executeQuery()) {
			if (rst.next())
				return new Patient(rst.getInt(1), rst.getString(2), email, password, rst.getDate(5));
		}
		return null;
	}

	@Override
	public String patientRegistration(Patient newPatient) throws SQLException {
		pst2.setString(1, newPatient.getName());
		pst2.setString(2, newPatient.getEmail());
		pst2.setString(3, newPatient.getPassword());
		pst2.setDate(4, newPatient.getDob());

		if (pst2.executeUpdate() == 1)
			return "Patient registered....!";
		return "Patient registration failed !";

	}

	public void cleanUp() throws SQLException {
		if (pst1 != null)
			pst1.close();
		if (pst2 != null)
			pst2.close();
		closeConnection();
	}
}
