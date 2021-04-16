package net.revature.binarybuiltbyte.project2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
@RepositoryRestResource(collectionResourceRel = "payments", path = "payment")
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
