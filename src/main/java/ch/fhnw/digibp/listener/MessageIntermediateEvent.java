package ch.fhnw.digibp.listener;

import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;

import ch.fhnw.digibp.config.MessageSender;

import javax.mail.MessagingException;

@Named
class MessageIntermediateEvent extends MessageSender {
    
    public void sendMeetingRequest( DelegateExecution execution ) 
    {
        try {
			// subject
            msg.setSubject("Meeting Request");
			
            String firstname = (String) execution.getVariable("firstname");
            String lastname = (String) execution.getVariable("lastname");
			// content 
            msg.setText("Dear " + firstname + " " + lastname + 
                "\n\nThank you for your interest in our transgenic facility. We have received your order." + 
                "\nHowever, in order to fully understand your request we need to sit down together." +
                "\nPlease contact the head of facility to set up a meeting to discuss the details of the project." +
                "\n\nBest regards," +
                "\nFHNW Transgenic Laboratory");
			
            sendMessage( (String) execution.getVariable("email") );

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendRefusal( DelegateExecution execution )
    {
        try {
			// subject
            msg.setSubject("Your order at the FHNW Transgenic Laboratory");
			
            String firstname = (String) execution.getVariable("firstname");
            String lastname = (String) execution.getVariable("lastname");
			// content 
            msg.setText("Dear " + firstname + " " + lastname + 
                "\n\nThank you for your interest in our transgenic facility." + 
                "\nAfter carefully checking your request we came to the conclusion, that it cannot be done." +
                "\nWe apologise for the inconvenience." +
                "\n\nBest regards," +
                "\nFHNW Transgenic Laboratory");
			
            sendMessage( (String) execution.getVariable("email") );

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendSamples(DelegateExecution execution) {
        try {
			// subject
            msg.setSubject("Samples from your order at the FHNW Transgenic Laboratory");
			
            String firstname = (String) execution.getVariable("firstname");
            String lastname = (String) execution.getVariable("lastname");
			// content 
            msg.setText("Dear " + firstname + " " + lastname + 
                "\n\nWe hereby inform you, that the samples from your order are ready to be analysed." + 
                "\nAs soon as you are done with your analysis please let us know, if the mice are according to your wishes" +
                "\nby using the following form:" +
                "\n\n" + execution.getVariable("url") + "/forms/confirm-samples.html?processInstance=" + execution.getProcessInstanceId() +
                "\n\nBest regards," +
                "\nFHNW Transgenic Laboratory");
			
            sendMessage( (String) execution.getVariable("email") );

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}