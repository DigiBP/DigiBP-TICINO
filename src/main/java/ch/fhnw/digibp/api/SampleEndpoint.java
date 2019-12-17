package ch.fhnw.digibp.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.camunda.bpm.engine.ProcessEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
class SampleEndpoint {

    @Autowired
    private ProcessEngine processEngine;

    @PostMapping(path = "/record-samples")
    public void postSampleData(@ModelAttribute SampleRequest sampleRequest) {
        String taskId = sampleRequest.getTaskId();
        String processInstance = sampleRequest.getProcessInstance();
        Map<Integer, List<Integer>> sampleData = new HashMap<>();
        List<Integer> mouseIds = sampleRequest.getMouseId();
        List<Integer> biopsyIds = sampleRequest.getBiopsyId();

        for( int i = 0; i < mouseIds.size(); i++ )
        {
            if( !sampleData.keySet().contains( mouseIds.get(i) ) )
            {
                sampleData.put(mouseIds.get(i), new ArrayList<>());
            }
            sampleData.get(mouseIds.get(i)).add( biopsyIds.get(i) );
        }

        processEngine.getRuntimeService().setVariable(processInstance, "sampleData", sampleData);
        processEngine.getRuntimeService().setVariable(processInstance, "biopsydate", sampleRequest.getBiopsydate());
        
        processEngine.getTaskService().complete(taskId);
    }

    private static class SampleRequest {
        private String taskId;
        private String processInstance;
        private List<Integer> mouseId;
        private List<Integer> biopsyId;
        private String biopsydate;

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

        public List<Integer> getMouseId()
        {
            return mouseId;
        }

        public void setMouseId( List<Integer> mouseId )
        {
            this.mouseId = mouseId;
        }

        public List<Integer> getBiopsyId()
        {
            return biopsyId;
        }

        public void setBiopsyId ( List<Integer> biopsyId )
        {
            this.biopsyId = biopsyId;
        }

        public String getBiopsydate()
        {
            return biopsydate;
        }

        public void setBiopsydate( String biopsydate )
        {
            this.biopsydate = biopsydate;
        }
    }
}