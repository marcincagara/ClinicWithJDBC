package pl.sdacademy;

import pl.sdacademy.database.dao.DMLDAO;

import java.util.Random;

public class Appointment {
    private int id;
    private int doctorId;
    private int patientId;
    private String date;
    private Random random = new Random();
    private int day = random.nextInt(30) + 1;
    private int month = random.nextInt(11) + 1;
    private DMLDAO dmldao;

    public Appointment(int doctorId, int patientId){
        this.doctorId = doctorId;
        this.patientId = patientId;
        date = day + "." + month + ".2018";
    }

    public Appointment(){}

    public int getId() {
        return id;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
