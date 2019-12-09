package ch.fhnw.digibp.api;

import org.camunda.bpm.engine.ProcessEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
class MiceOrderedEndpoint {

    @Autowired
    private ProcessEngine processEngine;

    @PostMapping(path = "/mice-ordered")
    public void postOrder(@ModelAttribute OrderMiceRequest orderMiceRequest) {
        String taskId = orderMiceRequest.getTaskId();
        
        
        processEngine.getTaskService().complete(taskId);
    }

    private static class OrderMiceRequest {
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