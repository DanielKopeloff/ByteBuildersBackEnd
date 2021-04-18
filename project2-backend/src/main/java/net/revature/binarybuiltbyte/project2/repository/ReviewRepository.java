package net.revature.binarybuiltbyte.project2.repository;

import net.revature.binarybuiltbyte.project2.model.ByteUser;
import net.revature.binarybuiltbyte.project2.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RepositoryRestResource(collectionResourceRel = "reviews", path = "review")
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    /** shows all reviews for a product
     *
     * @param productId product id for all of its reviews
     * @return a list of reviews for given product id
     */
    @Query(value="select r.id, r.\"comment\" , r.rating, r.review_created, r.review_terminated, r.byte_order_id from review r join byte_order bo on r.byte_order_id=bo.id join product_order po on po.byte_order_id = bo.id where po.product_id = :productId ;", nativeQuery = true)
    @RestResource(path = "product_reviews", rel = "product_reviews")
    List<Review> findAllReviewsForProduct(@Param("productId") int productId);
}
