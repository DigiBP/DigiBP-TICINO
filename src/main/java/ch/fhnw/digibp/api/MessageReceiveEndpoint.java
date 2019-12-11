package ch.fhnw.digibp.api;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.runtime.MessageCorrelationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
class MessageReceiveEndpoint {

    @Autowired
    private ProcessEngine processEngine;

    @PostMapping(path = "/confirm-samples")
    public void postOrder(@ModelAttribute MessageRequest messageRequest) {
        MessageCorrelationResult result = processEngine.getRuntimeService().createMessageCorrelation("transgenic_mice_feedback_received")
            .processInstanceId(messageRequest.getTaskId())
            .setVariable("samplesOk", messageRequest.getSampleResult())
            .correlateWithResult();
    }

    private static class MessageRequest {
        private String taskId;
        private boolean sampleResult;

        public String getTaskId()
        {
            return taskId;
        }

        public void setTaskId( String taskId )
        {
            this.taskId = taskId;
        }

        public boolean getSampleResult()
        {
            return sampleResult;
        }

        public void setSampleResult( boolean sampleResult )
        {
            this.sampleResult = sampleResult;
        }
    }
}