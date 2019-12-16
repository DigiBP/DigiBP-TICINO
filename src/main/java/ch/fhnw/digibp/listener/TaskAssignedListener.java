package ch.fhnw.digibp.listener;

import javax.inject.Named;
import javax.mail.MessagingException;

import org.camunda.bpm.engine.delegate.DelegateTask;

import ch.fhnw.digibp.config.MessageSender;

@Named
class TaskAssignedListener extends MessageSender {

    public void confirmProjectListener( DelegateTask task ) 
    {
        try {
			// subject
            msg.setSubject("Please confirm the project feasibility");
			
			// content 
            msg.setText("Please confirm the feasibility of the Project with the ID: " + task.getVariable("projectId") +
                " by using the following form: " + task.getVariable("url") + "/forms/confirm-feasibility.html?taskId=" + task.getId() + "&processInstance=" + task.getProcessInstanceId());
			
            sendMessage( "employee@fhnw.ch" );

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void orderMiceListener( DelegateTask task )
    {
        try {
		
			// subject
            msg.setSubject("Please order mice");
			
			// content 
            msg.setText("Please order the mice for the Project with the ID: " + task.getVariable("projectId") +
                " by using the following form: " + task.getVariable("url") + "/forms/order-mice.html?taskId=" + task.getId() + "&processInstance=" + task.getProcessInstanceId());
			
            sendMessage( "employee@fhnw.ch" );

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void recordInjectionDataListener(DelegateTask task)
    {
        try {
		
			// subject
            msg.setSubject("Please record the injection data");
			
			// content 
            msg.setText("Please record the injection data for the Project with the ID: " + task.getVariable("projectId") +
                " by using the following form: " + task.getVariable("url") + "/forms/injection-data.html?taskId=" + task.getId() + "&processInstance=" + task.getProcessInstanceId() + "&method=post");
			
            sendMessage( "employee@fhnw.ch" );

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void updateInjectionDataListener(DelegateTask task)
    {
        try {
		
			// subject
            msg.setSubject("Please update the injection data");
			
			// content 
            msg.setText("Please update the injection data for the Project with the ID: " + task.getVariable("projectId") +
                " by using the following form: " + task.getVariable("url") + "/forms/injection-data.html?taskId=" + task.getId() + "&processInstance=" + task.getProcessInstanceId() + "&method=put");
			
            sendMessage( "employee@fhnw.ch" );

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void checkForBirthsListener(DelegateTask task)
    {
        try {
			// subject
            msg.setSubject("Check for births");
			
			// content 
            msg.setText("Please check wether mice have been born for the Project with the ID: " + task.getVariable("projectId") +
                " and confirm on the following page: " + task.getVariable("url") + "/forms/birth-check.html?taskId=" + task.getId() + "&processInstance=" + task.getProcessInstanceId());
			
            sendMessage( "employee@fhnw.ch" );

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void recordBirthsListener(DelegateTask task)
    {
        try {
			// subject
            msg.setSubject("Record births");
			
			// content 
            msg.setText("Please record the births for the Project with the ID: " + task.getVariable("projectId") +
                " by using the following form: " + task.getVariable("url") + "/forms/record-births.html?taskId=" + task.getId() + "&processInstance=" + task.getProcessInstanceId());
			
            sendMessage( "employee@fhnw.ch" );

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void recordSamplesListener(DelegateTask task)
    {
        try {
			// subject
            msg.setSubject("Record samples");
			
			// content 
            msg.setText("Please record the samples taken for the Project with the ID: " + task.getVariable("projectId") +
                " by using the following form: " + task.getVariable("url") + "/forms/record-samples.html?taskId=" + task.getId() + "&processInstance=" + task.getProcessInstanceId());
			
            sendMessage( "employee@fhnw.ch" );

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void enterCryoSampleDataListener(DelegateTask task)
    {
        try {
			// subject
            msg.setSubject("Enter Sample data");
			
			// content 
            msg.setText("Please record the data for the samples for the Project with the ID: " + task.getVariable("projectId") +
                " by using the following form: " + task.getVariable("url") + "/forms/cryo-sample-data.html?taskId=" + task.getId() + "&processInstance=" + task.getProcessInstanceId());
			
            sendMessage( "employee@fhnw.ch" );

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void validateCryoSeriesListener(DelegateTask task)
    {
        try {
			// subject
            msg.setSubject("Confirm validation");
			
			// content 
            msg.setText("Please validate the series for the Project with the ID: " + task.getVariable("projectId") +
                " on the following page: " + task.getVariable("url") + "/forms/cryo-series-validation.html?taskId=" + task.getId() + "&processInstance=" + task.getProcessInstanceId());
			
            sendMessage( "employee@fhnw.ch" );

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}