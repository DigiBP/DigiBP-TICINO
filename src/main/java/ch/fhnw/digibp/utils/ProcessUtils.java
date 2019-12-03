package ch.fhnw.digibp.utils;

import java.util.List;

import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;

@Named
class ProcessUtils {
    public List<Object> isProjectExternal (DelegateExecution execution, String feasibilityFactorString) {

        List<Object> feasibilityFactors = (List<Object>) execution.getVariable(feasibilityFactorString);

        String email = (String) feasibilityFactors.get(0); //get e-mail address

        feasibilityFactors.remove(0); //remove e-mail address from list

        feasibilityFactors.add(0, email.toLowerCase().contains("fhnw") ? "Internal" : "External");

        return feasibilityFactors;
    }
}