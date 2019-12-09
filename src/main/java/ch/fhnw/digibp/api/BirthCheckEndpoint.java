package ch.fhnw.digibp.api;

import java.util.concurrent.TimeUnit;

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
    public void postOrder(@ModelAttribute BirthCheckRequest birthCheckRequest) {
        String taskId = birthCheckRequest.getTaskId();

        processEngine.getTaskService().setVariable(taskId, "births", birthCheckRequest.getBirth());
        processEngine.getTaskService().complete(taskId);
    }

    private static class BirthCheckRequest {
        private String taskId;
        private boolean birth;

        public String getTaskId()
        {
            return taskId;
        }

        public void setTaskId( String taskId )
        {
            this.taskId = taskId;
        }

        public boolean getBirth()
        {
            return birth;
        }

        public void setBirth( boolean birth )
        {
            this.birth = birth;
        }
    }
}