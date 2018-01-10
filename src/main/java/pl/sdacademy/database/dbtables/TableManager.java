package pl.sdacademy.database.dbtables;

import pl.sdacademy.database.dbsettings.ConnectionFactory;
import pl.sdacademy.database.dbsettings.DBSettings;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TableManager {

    private static final Connection connection = ConnectionFactory.getConnection(
        DBSettings.DB_CONNECTION,
        DBSettings.DB_USER,
        DBSettings.DB_PASSWORD);

    private static void createStatement(String StatementQuery){
        try {
            Statement statement = connection.createStatement();
            statement.execute(StatementQuery);
            statement.close();
            // statement.executeUpdate() //insert, update, delete (DDF)
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void dropAllTables()  {
        try {
        Statement statement = connection.createStatement();
        statement.executeUpdate("DROP SCHEMA clinic CASCADE");
        createStatement("CREATE SCHEMA clinic;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createAllTables(){
        createStatement(StatementBuilder.getCreatePatientTableStatement());
        createStatement(StatementBuilder.getCreateDoctorTableStatement());
        createStatement(StatementBuilder.getCreateAppointmentTableStatement());
        createStatement(StatementBuilder.getConstraintsToAppointment());
        createStatement(StatementBuilder.getConstraintsToPatient());
    }
}
