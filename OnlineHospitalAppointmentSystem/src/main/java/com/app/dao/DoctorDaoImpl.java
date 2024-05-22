package com.app.dao;

import static com.app.utils.DBConnection.closeConnection;
import static com.app.utils.DBConnection.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.core.Doctor;

public class DoctorDaoImpl implements DoctorDao {
	private Connection cn;
	private PreparedStatement pst1, pst2;

	public DoctorDaoImpl() throws SQLException {
		cn = getConnection();
		pst1 = cn.prepareStatement("select name from doctors where doctor_id=? ");
		pst2 = cn.prepareStatement("select * from doctors ");
	}

	@Override
	public String getDoctorName(int doctorId) throws SQLException {
		pst1.setInt(1, doctorId);
		try (ResultSet rst = pst1.executeQuery()) {
			if (rst.next())
				return rst.getString(1);
			return null;
		}
	}

	@Override
	public List<Doctor> getAllDoctorDetails() throws SQLException {
		List<Doctor> allDocs = new ArrayList<>();
		try (ResultSet rst = pst2.executeQuery()) {
			while (rst.next()) {
				allDocs.add(new Doctor(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getString(4),
						rst.getString(5)));
			}
		}
		return allDocs;
	}

	public void cleanup() throws SQLException {
		if (pst1 != null)
			pst1.close();
		if (pst2 != null)
			pst2.close();
		closeConnection();
	}

}
