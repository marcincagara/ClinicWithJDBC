package pl.sdacademy.database.dbtables;

public class StatementBuilder {

    public static String getCreatePatientTableStatement(){
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE clinic.patient ( ");
        sb.append("id SERIAL PRIMARY KEY,");
        sb.append("name character varying(20) NOT NULL,");
        sb.append("lastname character varying(20) NOT NULL,");
        sb.append("petname character varying(20) NOT NULL,");
        sb.append("petspecies character varying(20) NOT NULL,");
        sb.append("illness character varying(100) NOT NULL);");
        return sb.toString();
    }

    public static String getConstraintsToPatient(){
        StringBuilder sb = new StringBuilder();
        sb.append("ALTER TABLE clinic.patient ");
        sb.append("add column appointment integer,");
        sb.append("add constraint fk_appointment FOREIGN KEY (appointment) references clinic.appointment (id);");
        return sb.toString();
    }

    public static String getConstraintsToAppointment(){
        StringBuilder sb = new StringBuilder();
        sb.append("ALTER TABLE clinic.appointment ");
        sb.append("add column doctorid integer,");
        sb.append("add column patientid integer,");
        sb.append("add constraint fk_doctor FOREIGN KEY (doctorid) references clinic.doctor (id),");
        sb.append("add constraint fk_patient FOREIGN KEY (patientid) references clinic.patient (id);");
        return sb.toString();
    }

    public static String getCreateDoctorTableStatement() {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE clinic.doctor (");
        sb.append("id SERIAL PRIMARY KEY,");
        sb.append("name character varying(20)  NOT NULL,");
        sb.append("lastname character varying(20)  NOT NULL,");
        sb.append("specialization character varying(40)  NOT NULL);");
        return sb.toString();
    }

    public static String getCreateAppointmentTableStatement() {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE clinic.appointment (");
        sb.append("id SERIAL PRIMARY KEY,");
        sb.append("date character varying(20)  NOT NULL);");

        return sb.toString();
    }
}
