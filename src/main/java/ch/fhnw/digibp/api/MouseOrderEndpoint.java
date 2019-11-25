package ch.fhnw.digibp.api;

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
public class MouseOrderEndpoint {
    
    @Autowired
    private ProcessEngine processEngine;

    @PostMapping(path = "/order", consumes = "multipart/form-data", produces = "application/json")
    public void postOrder(@ModelAttribute TransgenicMouseRequest transgenicMouseRequest) {
        Map<String, Object> processVars = new HashMap<>();
        processVars.put("firstname", transgenicMouseRequest.getFirstName());
        processVars.put("lastname", transgenicMouseRequest.getLastName());

        processEngine.getRuntimeService().startProcessInstanceByMessage("transgenic-mice_order-received", processVars);
    }

    private static class TransgenicMouseRequest {
        private String firstname;
        private String lastname;
        private String address;
        private String phone;
        private String email;
        private String institution;
        private String projectType;
        private String strain;
        private List<String> genes;
        private List<String> providedReagents;

        public void setFirstName( String firstname ) {
            this.firstname = firstname;
        }

        public String getFirstName() {
            return firstname;
        }

        public void setLastName( String lastname ) {
            this.lastname = lastname;
        }

        public String getLastName() {
            return lastname;
        }
    }
}