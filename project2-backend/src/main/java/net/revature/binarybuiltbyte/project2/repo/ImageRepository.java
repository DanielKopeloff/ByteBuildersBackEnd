package net.revature.binarybuiltbyte.project2.repo;

import java.util.Optional;

import net.revature.binarybuiltbyte.project2.model.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageModel, Long> {

    Optional<ImageModel> findByName(String name);


}

