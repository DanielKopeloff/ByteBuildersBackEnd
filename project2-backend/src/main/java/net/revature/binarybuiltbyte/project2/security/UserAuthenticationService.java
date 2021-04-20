package net.revature.binarybuiltbyte.project2.security;



import net.revature.binarybuiltbyte.project2.model.ByteUser;
import net.revature.binarybuiltbyte.project2.repository.ByteUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationService implements UserDetailsService {

    @Autowired
    ByteUserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ByteUser byteUser = userRepository.findByUsername(username);
        if(byteUser == null) throw new UsernameNotFoundException(username);

        return new UserAuthentication(byteUser);
    }
}
