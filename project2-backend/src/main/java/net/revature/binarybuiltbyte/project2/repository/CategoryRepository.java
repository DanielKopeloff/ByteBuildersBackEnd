package net.revature.binarybuiltbyte.project2.repository;

import net.revature.binarybuiltbyte.project2.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
