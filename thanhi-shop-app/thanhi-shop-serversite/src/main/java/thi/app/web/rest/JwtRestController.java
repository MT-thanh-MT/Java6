package thi.app.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import thi.app.model.jwt.JwtRequest;
import thi.app.model.jwt.JwtRespone;
import thi.app.service.impl.UserDetailServiceImpl;

@RestController
@CrossOrigin
public class JwtRestController {
    @Autowired
    UserDetailServiceImpl userDetailService;

    @PostMapping("/authenticate")
    public JwtRespone createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        return userDetailService.createJwtToken(jwtRequest);

    }
}
