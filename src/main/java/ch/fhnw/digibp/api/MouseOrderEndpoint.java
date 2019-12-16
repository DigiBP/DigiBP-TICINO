package ch.fhnw.digibp.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
    public boolean postOrder(@ModelAttribute TransgenicMouseRequest transgenicMouseRequest, HttpServletRequest request) {
        Map<String, Object> processVars = new HashMap<>();
        processVars.put("firstname", transgenicMouseRequest.getFirstname());
        processVars.put("lastname", transgenicMouseRequest.getLastname());
        processVars.put("address", transgenicMouseRequest.getAddress());
        processVars.put("zip", transgenicMouseRequest.getZip());
        processVars.put("place", transgenicMouseRequest.getPlace());
        processVars.put("phone", transgenicMouseRequest.getPhone());
        processVars.put("email", transgenicMouseRequest.getEmail());
        processVars.put("institution", transgenicMouseRequest.getInstitution());

        processVars.put("projectType", transgenicMouseRequest.getProjectType());
        processVars.put("background", transgenicMouseRequest.getBackground());
        processVars.put("reagent", transgenicMouseRequest.getReagent());
        processVars.put("sequence", transgenicMouseRequest.getSequence());

        processVars.put("url", request.getServerName());

        processEngine.getRuntimeService().startProcessInstanceByMessage("transgenic-mice_order-received", processVars);

        return true;
    }

    private static class TransgenicMouseRequest {
        private String firstname;
        private String lastname;
        private String address;
        private int zip;
        private String place;
        private String phone;
        private String email;
        private String institution;
        private String projectType;
        private String background;
        private String reagent;
        private String sequence;

        public void setFirstname(String firstname) {
            this.firstname = firstname;
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

        public void setZip( int zip ) 
        {
            this.zip = zip;
        }

        public int getZip()
        {
            return zip;
        }

        public void setPlace( String place ) {
            this.place = place;
        }

        public String getPlace() {
            return place;
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

        public void setBackground( String background ) {
            this.background = background;
        }

        public String getBackground() {
            return background;
        }

        public String getSequence() {
            return sequence;
        }

        public void setSequence( String sequence ) {
            this.sequence = sequence;
        }

        public String getReagent() {
            return reagent;
        }

        public void setReagent(String reagent) {
            this.reagent = reagent;
        }
    }
}