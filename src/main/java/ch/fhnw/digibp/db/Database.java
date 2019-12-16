package ch.fhnw.digibp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;

import ch.fhnw.digibp.config.yaml.DatabaseProperties;

@Named
public class Database {

    @Autowired
	private DatabaseProperties dbProperties;

    public void registerProject(DelegateExecution execution) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection( dbProperties.getConnection() );

            String sql = "INSERT INTO customer(firstname, lastname, address, zip, place, phone, email, institution) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, (String) execution.getVariable("firstname"));
            statement.setString(2, (String) execution.getVariable("lastname"));
            statement.setString(3, (String) execution.getVariable("address"));
            statement.setInt(4, (Integer) execution.getVariable("zip"));
            statement.setString(5, (String) execution.getVariable("place"));
            statement.setString(6, (String) execution.getVariable("phone"));
            statement.setString(7, (String) execution.getVariable("email"));
            statement.setString(8, (String) execution.getVariable("institution"));
            statement.executeUpdate();

            String sqlProject = "INSERT INTO project(customer_id, project_type, background, reagent, sequence, created_at, completed) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statementProject = con.prepareStatement(sqlProject,
                    PreparedStatement.RETURN_GENERATED_KEYS);

            // Get and Set the customer_id field
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                statementProject.setLong(1, generatedKeys.getLong(1));
            }
            
            statementProject.setString(2, (String) execution.getVariable("projectType"));
            statementProject.setString(3, (String) execution.getVariable("background"));
            statementProject.setString(4, (String) execution.getVariable("reagent"));
            statementProject.setString(5, (String) execution.getVariable("sequence"));
            statementProject.setTimestamp( 6, new Timestamp( new Date().getTime() ) );
            statementProject.setInt(7, 0);
            statementProject.executeUpdate();

            long projectId = 0;
            // get project id
            generatedKeys = statementProject.getGeneratedKeys();
            if (generatedKeys.next()) {
                projectId = generatedKeys.getLong(1);
            }

            execution.setVariable("projectId", projectId);

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateProject( DelegateExecution execution, boolean feasible )
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection( dbProperties.getConnection() );

            String sql = "UPDATE project SET feasible = ?, completed = ? WHERE id = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setBoolean(1, feasible);
            statement.setBoolean(2, !feasible);
            statement.setLong(3, (Long) execution.getVariable("projectId"));
            statement.executeUpdate();

            con.close();
        } catch( Exception e )
        {
            e.printStackTrace();
        }
    }

    public void recordInjectionData( DelegateExecution execution )
    {
        try {
            LocalDate injectionDate = convertStringToDate( (String) execution.getVariable("injectionDate") );
            injectionDate = injectionDate.plusDays(1); //TODO check if still needed after deployment

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection( dbProperties.getConnection() );

            String sql = "INSERT INTO injection(project_id, injection_date, embryos_injected, surrogates) VALUES( ?, ?, ?, ? )";
            PreparedStatement statement = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setLong(1, (Long) execution.getVariable("projectId"));
            statement.setDate(2, java.sql.Date.valueOf(injectionDate));
            statement.setInt(3, (Integer) execution.getVariable("embryosInjected"));
            statement.setInt(4, (Integer) execution.getVariable("surrogates"));
            
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                execution.setVariable("injectionId", generatedKeys.getLong(1));
            }

            con.close();
        } 
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }

    public void updateInjectionData( DelegateExecution execution )
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection( dbProperties.getConnection() );

            LocalDate transferDate = convertStringToDate( (String) execution.getVariable("transferDate") );

            String sql = "UPDATE injection SET embryos_transferred = ?, transfer_date = ? WHERE project_id = ? AND id = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, (Integer) execution.getVariable("embryosTransferred"));
            statement.setDate(2, java.sql.Date.valueOf(transferDate));
            statement.setLong(3, (Long) execution.getVariable("projectId"));
            statement.setLong(4, (Long) execution.getVariable("injectionId"));
            
            statement.executeUpdate();
            con.close();
        } 
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }

    public void recordBirthData( DelegateExecution execution )
    {
        try 
        {
            LocalDate birthdate = convertStringToDate( (String) execution.getVariable("birthdate") );

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection( dbProperties.getConnection() );

            int maleBirths = (Integer) execution.getVariable("maleBirths");
            int femaleBirths = (Integer) execution.getVariable("femaleBirths");

            String sql = "INSERT INTO mouse(project_id, birthdate, sex) VALUES(  ?, ?, ? )";
            PreparedStatement statement = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setLong(1, (Long) execution.getVariable("projectId"));
            statement.setDate(2, java.sql.Date.valueOf(birthdate));
            
            for( int i = 1; i <= maleBirths; i++ )
            {
                statement.setString(3, "male");
                statement.executeUpdate();
            }

            for( int i = 1; i <= femaleBirths; i++ )
            {
                statement.setString(3, "female");
                statement.executeUpdate();
            }
            con.close();
        } 
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }

    public void recordSamples( DelegateExecution execution )
    {
        try 
        {
            LocalDate biopsydate = convertStringToDate( (String) execution.getVariable("biopsydate") );

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection( dbProperties.getConnection() );

            Map<Integer, List<Integer>> sampleData = (Map<Integer, List<Integer>>) execution.getVariable("sampleData");

            String sql = "INSERT INTO biopsy(project_id, mouse_id, biopsy_id, biopsy_date) VALUES( ?, ?, ?, ? )";
            PreparedStatement statement = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setLong(1, (Long) execution.getVariable("projectId"));
            statement.setDate(4, java.sql.Date.valueOf(biopsydate));
            
            for( Integer mouseId : sampleData.keySet() )
            {
                statement.setInt(2, mouseId);
                for( Integer biopsyId : sampleData.get( mouseId ) )
                {
                    statement.setInt(3, biopsyId);
                    statement.executeUpdate();
                }
            }
            con.close();
        } 
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }

    public void completeProject( DelegateExecution execution )
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection( dbProperties.getConnection() );

            String sql = "UPDATE project SET completed = 1 WHERE id = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setLong(1, (Long) execution.getVariable("projectId"));
            statement.executeUpdate();

            con.close();
        } 
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }

    private LocalDate convertStringToDate( String date ) throws Exception
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return LocalDate.parse(date, formatter);
    }
}