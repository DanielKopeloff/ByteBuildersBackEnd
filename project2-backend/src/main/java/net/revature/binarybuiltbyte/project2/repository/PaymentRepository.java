package net.revature.binarybuiltbyte.project2.repository;


import net.revature.binarybuiltbyte.project2.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = {"http://localhost:4200" , "http://bytebuildersbucket.s3-website.us-east-2.amazonaws.com"})
@RepositoryRestResource(collectionResourceRel = "payments", path = "payment")
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
