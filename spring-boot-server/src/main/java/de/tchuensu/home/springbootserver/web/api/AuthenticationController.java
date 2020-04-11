package de.tchuensu.home.springbootserver.web.api;


import de.tchuensu.home.springbootserver.dao.dto.model.AthenticationData;
import de.tchuensu.home.springbootserver.services.AuthenticationServiceImpl;
import de.tchuensu.home.springbootserver.util.RestPreconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @autor  Rodrigue Tchuensu P.
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @PostMapping
    //@ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> authenticateUser (@RequestBody AthenticationData userCredentials) {

        RestPreconditions.checkCredentialNotNull(userCredentials, "User Credential lacking");
        RestPreconditions.checkCredentialNotNull(userCredentials.getUsername(), "Username is required");
        RestPreconditions.checkCredentialNotNull(userCredentials.getPassword(), "Password is required");
        String generatedJWT = authenticationService.authenticateUser(userCredentials);

        return new ResponseEntity<>(generatedJWT, HttpStatus.OK);
    }
}
