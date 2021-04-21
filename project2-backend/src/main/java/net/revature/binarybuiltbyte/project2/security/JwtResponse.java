package net.revature.binarybuiltbyte.project2.security;

import net.revature.binarybuiltbyte.project2.model.ByteUser;

import java.util.Date;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class JwtResponse {

    private final String jwtToken;
    private ByteUser user;
    private Date date;


    public JwtResponse(String jwtToken, ByteUser user, Date date) {
        this.jwtToken = jwtToken;
        this.user = user;
        this.date = date;
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
        return user.getFirstName();
    }

    public Date getDate(){
        return date;
    }

}

// Wonder whast gonna happen