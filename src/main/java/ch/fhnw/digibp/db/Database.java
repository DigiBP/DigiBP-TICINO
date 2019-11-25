package ch.fhnw.digibp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;

@Named
public class Database {
    public String registerProject(DelegateExecution execution, String firstname, String lastname) {
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://g65mtwvif31z21d8:deoa5ltibxjdp6vm@n7qmaptgs6baip9z.chr7pe7iynqr.eu-west-1.rds.amazonaws.com:3306/sd104zmnv09fsqm0");

            String sql = "INSERT INTO customer(firstname, lastname, address, phone, email, institution) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, (String) execution.getVariable(firstname));
            statement.setString(2, (String) execution.getVariable(lastname));
            statement.setString(3, "");
            statement.setString(4, "");
            statement.setString(5, "");
            statement.setString(6, "");
            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return "";
    }
}