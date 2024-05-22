package com.app.dao;

import java.sql.SQLException;
import java.util.List;

import com.app.core.Doctor;

public interface DoctorDao {
String getDoctorName(int doctorId) throws SQLException;

List<Doctor> getAllDoctorDetails()throws SQLException;
}
