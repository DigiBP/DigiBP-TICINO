package ch.fhnw.digibp.api;

import org.camunda.bpm.engine.ProcessEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
class BirthCheckEndpoint {

    @Autowired
    private ProcessEngine processEngine;

    @PostMapping(path = "/birth-check")
    public void postBirthCheck(@ModelAttribute BirthCheckRequest birthCheckRequest) {
        String taskId = birthCheckRequest.getTaskId();

        processEngine.getTaskService().setVariable(taskId, "births", birthCheckRequest.getBirth());
        processEngine.getTaskService().complete(taskId);
    }

    @PostMapping(path = "/record-birth")
    public void postBirthRecords(@ModelAttribute BirthCheckRequest birthCheckRequest) {
        String taskId = birthCheckRequest.getTaskId();

        String processInstance = birthCheckRequest.getProcessInstance();
        
        processEngine.getRuntimeService().setVariable(processInstance, "maleBirths", birthCheckRequest.getMaleBirths());
        processEngine.getRuntimeService().setVariable(processInstance, "femaleBirths", birthCheckRequest.getFemaleBirths());
        processEngine.getRuntimeService().setVariable(processInstance, "birthdate", birthCheckRequest.getBirthdate());

        processEngine.getTaskService().complete(taskId);
    }

    private static class BirthCheckRequest {
        private String taskId;
        private String processInstance;
        private boolean birth;
        private int maleBirths;
        private int femaleBirths;
        private String birthdate;

        public String getTaskId()
        {
            return taskId;
        }

        public void setTaskId( String taskId )
        {
            this.taskId = taskId;
        }

        public String getProcessInstance()
        {
            return processInstance;
        }

        public void setProcessInstance( String processInstance )
        {
            this.processInstance = processInstance;
        }

        public boolean getBirth()
        {
            return birth;
        }

        public void setBirth( boolean birth )
        {
            this.birth = birth;
        }

        public int getMaleBirths()
        {
            return maleBirths;
        }

        public void setMaleBirths( int maleBirths )
        {
            this.maleBirths = maleBirths;
        }

        public int getFemaleBirths()
        {
            return femaleBirths;
        }

        public void setFemaleBirths( int femaleBirths )
        {
            this.femaleBirths = femaleBirths;
        }

        public String getBirthdate()
        {
            return birthdate;
        }

        public void setBirthdate( String birthdate )
        {
            this.birthdate = birthdate;
        }
    }
}