package net.revature.binarybuiltbyte.project2.security;

import net.revature.binarybuiltbyte.project2.model.ByteUser;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class JwtResponse {

    private final String jwtToken;

    public JwtResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getJwtToken(){
        return jwtToken;
    }
    //might add response like in video
//    TokenExpire = new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000);

}
