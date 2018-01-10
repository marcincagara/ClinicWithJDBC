package pl.sdacademy.database.dao;

import pl.sdacademy.Appointment;
import pl.sdacademy.Doctor;
import pl.sdacademy.Patient;

import java.util.List;

public interface DMLDAO {
    //Patient
    List<Patient> findAll();
    Patient findPatientById(int id);
    void deleteAllPatients();
    void deletePatient(int id);
    void updatePatient(Patient patient);
    void insertPatient(Patient patient);

    //Doctor
    Doctor findDoctorByName(String name);
    void insertDoctor(Doctor doctor);

    //Appointment
    List<Appointment> findAllAppointments();
    Appointment findAppointmentById(int id);
    Appointment findAppointmentByPatientId(int id);
    void deleteAllAppointments();
    void deleteAppointment(int id);
    void insertAppointment(Appointment appointment);
}
