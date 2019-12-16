package ch.fhnw.digibp.api;

import org.camunda.bpm.engine.ProcessEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
class ProjectFeasibilityEndpoint {

    @Autowired
    private ProcessEngine processEngine;

    @PostMapping(path = "/project-feasibility")
    public void postOrder(@ModelAttribute ProjectFeasiblityRequest projectFeasibilityRequest) {
        String taskId = projectFeasibilityRequest.getTaskId();

        if( projectFeasibilityRequest.getFeasibility() )
        {
            processEngine.getTaskService().setVariable(taskId, "feasibility", "Possible");
        }
        else
        {
            processEngine.getTaskService().setVariable(taskId, "feasibility", "Not Possible");
        }
        
        processEngine.getTaskService().complete(taskId);
    }

    private static class ProjectFeasiblityRequest {
        private String taskId;
        private boolean feasibility;

        public String getTaskId()
        {
            return taskId;
        }

        public void setTaskId( String taskId )
        {
            this.taskId = taskId;
        }

        public boolean getFeasibility()
        {
            return feasibility;
        }

        public void setFeasibility( boolean feasibility )
        {
            this.feasibility = feasibility;
        }
    }
}