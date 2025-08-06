package fr.perso.skillcheck.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller("/sessions")
public class SessionController {

    @Autowired
    private SessionService      sessionService;
    
}
