package pl.sdacademy.database.dao;

import pl.sdacademy.Appointment;
import pl.sdacademy.Doctor;
import pl.sdacademy.Patient;
import pl.sdacademy.database.dbsettings.ConnectionFactory;
import pl.sdacademy.database.dbsettings.DBSettings;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoImpl implements DMLDAO {
    private final Connection connection = ConnectionFactory.getConnection(DBSettings.DB_CONNECTION, DBSettings.DB_USER, DBSettings.DB_PASSWORD);
    private static final String FIND_ALL = "SELECT * FROM clinic.\"patient\"";
    private static final String FIND_ALL_APPOINTMENT = "SELECT * FROM clinic.\"appointment\"";
    private static final String FIND_PATIENT_BY_ID = "SELECT * FROM clinic.\"patient\" WHERE id=?";
    private static final String FIND_APPOINTMENT_BY_ID = "SELECT * FROM clinic.\"appointment\" WHERE id=?";
    private static final String FIND_APPOINTMENT_BY_PATIENT_ID = "SELECT * FROM clinic.\"appointment\" WHERE patientid=?";
    private static final String FIND_DOCTOR_BY_NAME = "SELECT * FROM clinic.\"doctor\" WHERE lastname=?";
    private static final String INSERT_INTO_PATIENT = "INSERT INTO clinic.\"patient\" (name, lastname, petname, petspecies, illness) VALUES (?,?,?,?,?)";
    private static final String INSERT_INTO_DOCTOR = "INSERT INTO clinic.\"doctor\" (name, lastname, specialization) VALUES (?,?,?)";
    private static final String UPDATE = "UPDATE clinic.\"patient\" SET (name, lastname, petname, petspecies, illness, appointment) = (?, ?, ?, ?, ?,?) WHERE id=?;";
    private static final String DELETE = "DELETE FROM clinic.\"patient\" WHERE id=?;";
    private static final String DELETE_APPOINTMENT = "DELETE FROM clinic.\"appointment\" WHERE id=?;";
    private static final String DELETE_ALL_APPOINTMENTS = "DELETE FROM clinic.\"appointment\"";
    private static final String DELETE_ALL_PATIENTS = "DELETE FROM clinic.\"patient\"";
    private static final String INSERT_INTO_APPOINTMENT = "INSERT INTO clinic.\"appointment\" (id, doctorid, patientid, date) VALUES(?,?,?,?)";

    private List<Patient> getPatientsFromResultSet(ResultSet resultSet) {
        List<Patient> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("name");
                String lastName = resultSet.getString("lastname");
                String petName = resultSet.getString("petname");
                String petSpiece = resultSet.getString("petspecies");
                String illness = resultSet.getString("illness");
                Patient patient = new Patient();
                patient.setId(id);
                patient.setName(firstName);
                patient.setSurname(lastName);
                patient.setPetName(petName);
                patient.setPetSpecies(petSpiece);
                patient.setIllness(illness);
                result.add(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private List<Doctor> getDoctorsFromResultSet(ResultSet resultSet) {
        List<Doctor> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("name");
                String lastName = resultSet.getString("lastname");
                String specialization = resultSet.getString("specialization");

                Doctor doctor = new Doctor();
                doctor.setId(id);
                doctor.setName(firstName);
                doctor.setSurname(lastName);
                doctor.setSpecialization(specialization);
                result.add(doctor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private List<Appointment> getAppointmentsFromResultSet(ResultSet resultSet) {
        List<Appointment> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int doctorid = resultSet.getInt("doctorid");
                int patientid = resultSet.getInt("patientid");
                String date = resultSet.getString("date");
                Appointment appointment = new Appointment();
                appointment.setId(id);
                appointment.setDoctorId(doctorid);
                appointment.setPatientId(patientid);
                appointment.setDate(date);

                result.add(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    public List<Patient> findAll() {
        Statement statement = null;
        List<Patient> result = new ArrayList<Patient>();
        try {
            statement = connection.createStatement();
            result = getPatientsFromResultSet(statement.executeQuery(FIND_ALL));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public List<Appointment> findAllAppointments() {
        Statement statement = null;
        List<Appointment> result = new ArrayList<>();
        try {
            statement = connection.createStatement();
            result = getAppointmentsFromResultSet(statement.executeQuery(FIND_ALL_APPOINTMENT));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public Patient findPatientById(int id) {
        PreparedStatement statement = null;
        Patient result = null;
        try {
            statement = connection.prepareStatement(FIND_PATIENT_BY_ID);
            statement.setInt(1, id);
            List<Patient> patients = getPatientsFromResultSet(statement.executeQuery());
            if (patients.size() > 0) {
                result = patients.get(0);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Doctor findDoctorByName(String name) {
        PreparedStatement statement = null;
        Doctor result = null;
        try {
            statement = connection.prepareStatement(FIND_DOCTOR_BY_NAME);
            statement.setString(1, name);
            List<Doctor> patients = getDoctorsFromResultSet(statement.executeQuery());
            if (patients.size() > 0) {
                result = patients.get(0);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Appointment findAppointmentById(int id) {
        PreparedStatement statement = null;
        Appointment result = null;
        try {
            statement = connection.prepareStatement(FIND_APPOINTMENT_BY_ID);
            statement.setInt(1, id);
            List<Appointment> appointments = getAppointmentsFromResultSet(statement.executeQuery());
            if (appointments.size() > 0) {
                result = appointments.get(0);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Appointment findAppointmentByPatientId(int id) {
        PreparedStatement statement = null;
        Appointment result = null;
        try {
            statement = connection.prepareStatement(FIND_APPOINTMENT_BY_PATIENT_ID);
            statement.setInt(1, id);
            List<Appointment> appointments = getAppointmentsFromResultSet(statement.executeQuery());
            if (appointments.size() > 0) {
                result = appointments.get(0);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    public void deletePatient(int id) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteAppointment(int id) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE_APPOINTMENT);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePatient(Patient patient) {
        Appointment appointment = findAppointmentById(patient.getId());
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE);
            statement.setInt(7, patient.getId());
            if (patient.getName() == null) {
                statement.setNull(1, 0);
            } else {
                statement.setString(1, patient.getName());
            }
            if (patient.getSurname() == null) {
                statement.setNull(2, 0);
            } else {
                statement.setString(2, patient.getSurname());
            }
            if (patient.getPetName() == null) {
                statement.setNull(3, 0);
            } else {
                statement.setString(3, patient.getPetName());
            }
            if (patient.getPetSpecies() == null) {
                statement.setNull(4, 0);
            } else {
                statement.setString(4, patient.getPetSpecies());
            }
            if (patient.getIllness() == null) {
                statement.setNull(5, 0);
            } else {
                statement.setString(5, patient.getIllness());
            }
            if (appointment == null) {
                statement.setNull(6, 0);
            } else {
                statement.setString(6, appointment.getDate());
            }
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void insertPatient(Patient patient) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(INSERT_INTO_PATIENT);
            statement.setString(1, patient.getName());
            statement.setString(2, patient.getSurname());
            statement.setString(3, patient.getPetName());
            statement.setString(4, patient.getPetSpecies());
            statement.setString(5, patient.getIllness());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertDoctor(Doctor doctor) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(INSERT_INTO_DOCTOR);
            statement.setString(1, doctor.getName());
            statement.setString(2, doctor.getSurname());
            statement.setString(3, doctor.getSpecialization());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertAppointment(Appointment appointment) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(INSERT_INTO_APPOINTMENT);
            statement.setInt(1, appointment.getId());
            statement.setInt(2, appointment.getDoctorId());
            statement.setInt(3, appointment.getPatientId());
            statement.setString(4, appointment.getDate());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAllPatients() {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE_ALL_PATIENTS);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAllAppointments() {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE_ALL_APPOINTMENTS);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

