package ch.fhnw.digibp.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.camunda.bpm.engine.ProcessEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
class CryoDataEndpoint {

    @Autowired
    private ProcessEngine processEngine;

    @PostMapping(path = "/cryo-data")
    public void postCryoData(@ModelAttribute CryoDataRequest cryoDataRequest) {
        String taskId = cryoDataRequest.getTaskId();
        String processInstance = cryoDataRequest.getProcessInstance();


        processEngine.getRuntimeService().setVariable(processInstance, "sampleName", cryoDataRequest.getSampleName());
        processEngine.getRuntimeService().setVariable(processInstance, "breeding", cryoDataRequest.getBreeding());
        processEngine.getRuntimeService().setVariable(processInstance, "cryotank", cryoDataRequest.getCryotank());
        processEngine.getRuntimeService().setVariable(processInstance, "rack", cryoDataRequest.getRack());
        processEngine.getRuntimeService().setVariable(processInstance, "box", cryoDataRequest.getBox());
        processEngine.getRuntimeService().setVariable(processInstance, "position", cryoDataRequest.getPosition());
        processEngine.getRuntimeService().setVariable(processInstance, "strain", getStrain());

        
        processEngine.getTaskService().complete(taskId);
    }

    @PutMapping(path = "/cryo-series-validation")
    public void postSeriesValidation(@ModelAttribute CryoValidationRequest cryoValidationRequest) 
    {
        String taskId = cryoValidationRequest.getTaskId();
        String processInstance = cryoValidationRequest.getProcessInstance();

        processEngine.getRuntimeService().setVariable(processInstance, "seriesValid", cryoValidationRequest.isSeriesValid());
        
        processEngine.getTaskService().complete(taskId);
    }

    private String getStrain()
    {
        List<String> strains = new ArrayList<>();
        strains.add("Gt(ROSA)26Sortm1(CAG-Brainbow2.1)Cle");
        strains.add("B6.D2(BKS)-Dock7m/J");

        Random random = new Random();

        return strains.get(random.nextInt(1));
    }

    private static class CryoDataRequest {
        private String taskId;
        private String processInstance;
        private String sampleName;
        private String breeding;
        private int cryotank;
        private int rack;
        private int box;
        private int position;


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

        public String getSampleName()
        {
            return sampleName;
        }

        public void setSampleName( String sampleName )
        {
            this.sampleName = sampleName;
        }

        public String getBreeding()
        {
            return breeding;
        }

        public void setBreeding( String breeding )
        {
            this.breeding = breeding;
        }

        public int getCryotank()
        {
            return cryotank;
        }

        public void setCryotank( int cryotank )
        {
            this.cryotank = cryotank;
        }

        public int getRack()
        {
            return rack;
        }

        public void setRack( int rack )
        {
            this.rack = rack;
        }

        public int getBox()
        {
            return box;
        }

        public void setBox( int box )
        {
            this.box = box;
        }

        public int getPosition()
        {
            return position;
        }

        public void setPosition( int position )
        {
            this.position = position;
        }
    }

    private static class CryoValidationRequest {
        private String taskId;
        private String processInstance;
        private boolean seriesValid;


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

        public boolean isSeriesValid()
        {
            return seriesValid;
        }

        public void setSeriesValid( boolean seriesValid )
        {
            this.seriesValid = seriesValid;
        }
    }
}