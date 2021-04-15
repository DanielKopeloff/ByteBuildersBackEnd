package net.revature.binarybuiltbyte.project2.repository;

import net.revature.binarybuiltbyte.project2.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin("http://localhost:4200")
@RepositoryRestResource(collectionResourceRel = "products", path = "product")
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value="select p.id, p.description, p.is_active,  p.picture, p.price, p.product_created, p.product_terminated, p.rating, p.sku, p.stock, p.category_id from product p join product_order po on p.id=po.product_id join byte_order bo on bo.id=po.order_id where bo.order_completed between :past and :now ;", nativeQuery = true)
    List<Product> findProductsByDate(@Param("past") Date past, @Param("now") Date now);
}

