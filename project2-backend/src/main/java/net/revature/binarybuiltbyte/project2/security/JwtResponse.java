package net.revature.binarybuiltbyte.project2.security;

import net.revature.binarybuiltbyte.project2.model.ByteUser;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class JwtResponse {

    private final String jwtToken;
    private ByteUser user;
    private String expireDate;

    public JwtResponse(String jwtToken, ByteUser user, Date expireDate) {

        this.jwtToken = jwtToken;
        this.user = user;
        this.expireDate = String.valueOf(expireDate.getTime());// this should return the number of seconds as a long?, should make it into a string?
    }

    public String getJwtToken(){
        return jwtToken;
    }

    public int getUserId(){
        return user.getId();
    }
    public String getUserEmail(){
        return user.getEmail();
    }
    public String getName(){
        return user.getUsername();
    }
    public String getExpireDate(){
        return expireDate;
    }
    public Date getUserCreated(){
        return user.getUserCreated();
    }

}
