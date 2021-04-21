package net.revature.binarybuiltbyte.project2.controller;

import net.revature.binarybuiltbyte.project2.model.ByteUser;
import net.revature.binarybuiltbyte.project2.repository.ByteUserRepository;
import net.revature.binarybuiltbyte.project2.security.*;

import net.revature.binarybuiltbyte.project2.model.ByteUser;
import net.revature.binarybuiltbyte.project2.security.JwtRequest;
import net.revature.binarybuiltbyte.project2.security.JwtResponse;
import net.revature.binarybuiltbyte.project2.security.UserAuthenticationService;
import net.revature.binarybuiltbyte.project2.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
public class HomeController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserAuthenticationService userService;

    @Autowired
    ByteUserRepository userRepository;

    @GetMapping("/")
    public String home() {
        return "Welcome to Byte Builder!!";
    }

    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception{

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        final UserDetails userDetails
                = userService.loadUserByUsername(jwtRequest.getUsername());

        ByteUser byteUser = userRepository.findByUsername(jwtRequest.getUsername());



        final String token =
                jwtUtil.generateToken(userDetails);

        return  new JwtResponse(token, byteUser, jwtUtil.getExpiration());
    }
}
