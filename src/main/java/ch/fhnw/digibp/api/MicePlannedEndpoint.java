package ch.fhnw.digibp.api;

import org.camunda.bpm.engine.ProcessEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
class MicePlannedEndpoint {

    @Autowired
    private ProcessEngine processEngine;

    @PostMapping(path = "/mice-planned")
    public void postOrder(@ModelAttribute PlanMiceRequest planMiceRequest) {
        //Map<String, Object> processVars = new HashMap<>();
        String taskId = planMiceRequest.getTaskId();
        
        
        processEngine.getTaskService().complete(taskId);
    }

    private static class PlanMiceRequest {
        private String taskId;
        private String bla;

        public String getTaskId()
        {
            return taskId;
        }

        public void setTaskId( String taskId )
        {
            this.taskId = taskId;
        }

        public String getBla()
        {
            return bla;
        }

        public void setBla( String bla )
        {
            this.bla = bla;
        }

    }
}