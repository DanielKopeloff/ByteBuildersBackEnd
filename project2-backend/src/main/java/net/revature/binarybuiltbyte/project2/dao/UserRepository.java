package net.revature.binarybuiltbyte.project2.dao;

import net.revature.binarybuiltbyte.project2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Integer> {
}
