package net.revature.binarybuiltbyte.project2.dao;

import net.revature.binarybuiltbyte.project2.model.ByteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "byteUser", path = "byte-user")
public interface UserRepository extends JpaRepository<ByteUser, Integer> {
}
