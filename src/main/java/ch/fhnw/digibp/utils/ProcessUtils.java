package ch.fhnw.digibp.utils;

import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;

@Named
class ProcessUtils {
    public void isProjectExternal (DelegateExecution execution) {

        String email = (String) execution.getVariable("email");

        execution.setVariable("isProjectInternal", email.toLowerCase().contains("fhnw") ? "Internal" : "External");
    }

    public boolean doNothing( )
    {
        return true;
    }
}