package ch.fhnw.digibp.utils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;

@Named
class ProcessUtils {
    public List<String> defineProjectSource (DelegateExecution execution, String feasibilityFactorString) {

        List<String> feasibilityFactors = (List<String>) execution.getVariable(feasibilityFactorString);

        System.out.println(feasibilityFactors.get(0));

        return new ArrayList<>();
    }
}