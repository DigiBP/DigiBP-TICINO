package ch.fhnw.digibp.api;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.ProcessEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
class InjectionDataEndpoint {

    @Autowired
    private ProcessEngine processEngine;

    @PostMapping(path = "/injection-data")
    public void postOrder(@ModelAttribute InjectionDataRequest injectionDataRequest) {
        String taskId = injectionDataRequest.getTaskId();
        String processInstance = injectionDataRequest.getProcessInstance();
        
        processEngine.getRuntimeService().setVariable(processInstance, "injectionDate", injectionDataRequest.getInjectionDate());
        processEngine.getRuntimeService().setVariable(processInstance, "embryosInjected", injectionDataRequest.getEmbryosInjected());
        processEngine.getRuntimeService().setVariable(processInstance, "surrogates", injectionDataRequest.getSurrogates());

        processEngine.getTaskService().complete(taskId);
    }

    @PutMapping(path = "/injection-data")
    public void updateInjectionData(@ModelAttribute InjectionDataRequest injectionDataRequest) {
        String taskId = injectionDataRequest.getTaskId();
        String processInstance = injectionDataRequest.getProcessInstance();
        
        processEngine.getRuntimeService().setVariable(processInstance, "embryosTransferred", injectionDataRequest.getEmbryosTransferred());
        processEngine.getRuntimeService().setVariable(processInstance, "transferDate", injectionDataRequest.getTransferDate());
        
        processEngine.getTaskService().complete(taskId);
    }

    private static class InjectionDataRequest {
        private String taskId;
        private String processInstance;
        private String injectionDate;
        private int embryosInjected;
        private int surrogates;
        private int embryosTransferred;
        private String transferDate;

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

        public String getInjectionDate()
        {
            return injectionDate;
        }        

        public void setInjectionDate( String injectionDate )
        {
            this.injectionDate = injectionDate;
        }

        public int getEmbryosInjected()
        {
            return embryosInjected;
        }

        public void setEmbryosInjected( int embryosInjected )
        {
            this.embryosInjected = embryosInjected;
        }

        public int getSurrogates()
        {
            return surrogates;
        }

        public void setSurrogates( int surrogates )
        {
            this.surrogates = surrogates;
        }

        public int getEmbryosTransferred()
        {
            return embryosTransferred;
        }

        public void setEmbryosTransferred( int embryosTransferred )
        {
            this.embryosTransferred = embryosTransferred;
        }

        public String getTransferDate()
        {
            return transferDate;
        }

        public void setTransferDate( String transferDate )
        {
            this.transferDate = transferDate;
        }

    }
}