package ch.fhnw.digibp.api;

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
        //Map<String, Object> processVars = new HashMap<>();
        String taskId = injectionDataRequest.getTaskId();
        
        
        processEngine.getTaskService().complete(taskId);
    }

    @PutMapping(path = "/injection-data")
    public void updateInjectionData(@ModelAttribute InjectionDataRequest injectionDataRequest) {
        //Map<String, Object> processVars = new HashMap<>();
        String taskId = injectionDataRequest.getTaskId();
        
        System.out.println("put method called");
        
        processEngine.getTaskService().complete(taskId);
    }

    private static class InjectionDataRequest {
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