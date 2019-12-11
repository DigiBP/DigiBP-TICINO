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

    @PostMapping(path = "/order")
    public void postOrder(@ModelAttribute TransgenicMouseRequest transgenicMouseRequest) {
        Map<String, Object> processVars = new HashMap<>();
        processVars.put("firstname", transgenicMouseRequest.getFirstname());
        processVars.put("lastname", transgenicMouseRequest.getLastname());
        processVars.put("address", transgenicMouseRequest.getAddress());
        processVars.put("phone", transgenicMouseRequest.getPhone());
        processVars.put("email", transgenicMouseRequest.getEmail());
        processVars.put("institution", transgenicMouseRequest.getInstitution());
        processVars.put("projectType", transgenicMouseRequest.getProjectType());
        processVars.put("strain", transgenicMouseRequest.getStrain());
        //processVars.put("genes", transgenicMouseRequest.getGenes());
        processVars.put("reagents", transgenicMouseRequest.getReagents());

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
        private List<String> reagents;
        private String sequence;

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public List<String> getReagents() {
            return reagents;
        }

        public void setReagents(List<String> reagents) {
            this.reagents = reagents;
        }

        public List<String> getGenes() {
            return genes;
        }

        public void setGenes(List<String> genes) {
            this.genes = genes;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setLastname( String lastname ) {
            this.lastname = lastname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setAddress( String address ) {
            this.address = address;
        }

        public String getAddress() {
            return address;
        }

        public void setPhone( String phone ) {
            this.phone = phone;
        }

        public String getPhone() {
            return phone;
        }

        public void setEmail( String email ) {
            this.email = email;
        }

        public String getEmail() {
            return email;
        }

        public void setInstitution( String institution ) {
            this.institution = institution;
        }

        public String getInstitution() {
            return institution;
        }

        public void setProjectType( String projectType ) {
            this.projectType = projectType;
        }

        public String getProjectType() {
            return projectType;
        }

        public void setStrain( String strain ) {
            this.strain = strain;
        }

        public String getStrain() {
            return strain;
        }

        public String getSequence() {
            return sequence;
        }

        public void setSequence( String sequence ) {
            this.sequence = sequence;
        }
    }
}