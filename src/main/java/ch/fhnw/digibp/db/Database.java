package ch.fhnw.digibp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;

@Named
public class Database {
    public List<String> registerProject(DelegateExecution execution, String firstname, String lastname, String address,
            String phone, String email, String institution, String projectType, String strain, String genes,
            String providedReagents) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://g65mtwvif31z21d8:deoa5ltibxjdp6vm@n7qmaptgs6baip9z.chr7pe7iynqr.eu-west-1.rds.amazonaws.com:3306/sd104zmnv09fsqm0");

            String sql = "INSERT INTO customer(firstname, lastname, address, phone, email, institution) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, (String) execution.getVariable(firstname));
            statement.setString(2, (String) execution.getVariable(lastname));
            statement.setString(3, (String) execution.getVariable(address));
            statement.setString(4, (String) execution.getVariable(phone));
            statement.setString(5, (String) execution.getVariable(email));
            statement.setString(6, (String) execution.getVariable(institution));
            statement.executeUpdate();

            java.util.Date date = new java.util.Date();
            String sqlProject = "INSERT INTO project(customer_id, type, background_strain, created_at, completed) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statementProject = con.prepareStatement(sqlProject,
                    PreparedStatement.RETURN_GENERATED_KEYS);

            // Get and Set the customer_id field
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                statementProject.setLong(1, generatedKeys.getLong(1));
            }

            statementProject.setString(2, (String) execution.getVariable(projectType));
            statementProject.setString(3, (String) execution.getVariable(strain));
            statementProject.setDate(4, new Date(date.getTime()));
            statementProject.setInt(5, 0);
            statementProject.executeUpdate();

            long projectId = 0;
            // get project id
            generatedKeys = statementProject.getGeneratedKeys();
            if (generatedKeys.next()) {
                projectId = generatedKeys.getLong(1);
            }

            if (projectId > 0) {
                //List<String> genesOfInterest = (List<String>) execution.getVariable(genes);
                String sqlGenes = "INSERT INTO gene_of_interest(project_id, description) VALUES (?, ?)";
                PreparedStatement statementGenes = con.prepareStatement(sqlGenes);
                statementGenes.setLong(1, projectId);

                /*for (String gene : genesOfInterest) {
                    if (gene.trim().length() > 0) {
                        statementGenes.setString(2, gene);
                        statementGenes.executeUpdate();
                    }
                }*/

                List<String> reagents = (List<String>) execution.getVariable(providedReagents);
                String sqlReagents = "INSERT INTO project_reagents(project_id, reagent_id) VALUES(?, ?)";
                PreparedStatement statementReagents = con.prepareStatement(sqlReagents);
                statementReagents.setLong(1, projectId);

                String reagentSelect = "SELECT id FROM reagent WHERE name = ?";
                PreparedStatement statementReagentsSelect = con.prepareStatement(reagentSelect);

                for (String reagent : reagents) {
                    statementReagentsSelect.setString(1, reagent);
                    ResultSet reagentSet = statementReagentsSelect.executeQuery();
                    if (reagentSet.next()) {
                        statementReagents.setLong(2, reagentSet.getLong("id"));
                        statementReagents.executeUpdate();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        List<String> returnValue = new ArrayList<String>();
        returnValue.add((String) execution.getVariable(email));
        returnValue.add((String) execution.getVariable(projectType));
        returnValue.add((String) execution.getVariable(strain));

        return returnValue;
    }

    public boolean doNothing()
    {
        return true;
    }

    public boolean completeProject( DelegateExecution execution )
    {
        boolean samplesOk = (boolean) execution.getVariable("samplesOk");

        return true;
    }
}