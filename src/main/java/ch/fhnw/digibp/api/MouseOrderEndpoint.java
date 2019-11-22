package ch.fhnw.digibp.api;

import org.camunda.bpm.engine.ProcessEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
public class MouseOrderEndpoint {
    
    @Autowired
    private ProcessEngine processEngine;
}