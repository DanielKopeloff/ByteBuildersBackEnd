package net.revature.binarybuiltbyte.project2.repository;

import net.revature.binarybuiltbyte.project2.model.ByteUser;
import net.revature.binarybuiltbyte.project2.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.sql.Date;
import java.util.List;

@CrossOrigin("http://localhost:4200")
@RepositoryRestResource(collectionResourceRel = "byteUsers", path = "byte-user")
public interface ByteUserRepository extends JpaRepository<ByteUser, Integer> {

    ByteUser findByUsername(String username);

    /** find byte_user for review by byte_order
     *
     * @param byteOrderId unique id for each order that can match a review to a user
     * @return user for byte_order review
     */
    @Query(value="select bu.id, bu.byte_role, bu.email, bu.first_name, bu.last_name, bu.\"password\", bu.profile_pic, bu.user_created, bu.user_terminated, bu.username from byte_user bu join byte_order bo on bo.byte_user_id = bu.id join review r on r.byte_order_id = bo.id where bo.id = :byteOrderId LIMIT 1;", nativeQuery = true)
    @RestResource(path = "byte_order_review", rel = "byte_order_review")
    List<ByteUser> findByteUserByByteOrder(@Param("byteOrderId") int byteOrderId);

    /**
     * Find the byte user by the review Id
     */
    @Query(value="select bu.byte_role  , bu.email , bu.first_name  , bu.id  , bu.last_name  , bu.\"password\"  , bu.profile_pic  , bu.user_created  , bu.user_terminated  , bu.username " +
            "from review r , byte_order bo , byte_user bu " +
            "where r.byte_order_id = bo.id and r.id = :reviewId and bu.id = bo.byte_user_id ;\n;", nativeQuery = true)
    @RestResource(path = "reviewUser", rel = "reviewUser")
    ByteUser findByteUserByReview(@Param("reviewId") int reviewId);

    /**
     * Find the byte user by the review Id
     */
    @Query(value="select bu.byte_role  , bu.email  ,bu.first_name  , bu.id , bu.last_name , bu.last_name  , bu.\"password\"  , bu.profile_pic  , bu.user_created  , bu.user_terminated  , bu.username " +
            "from review r , byte_order bo , byte_user bu , product_order po " +
            "where r.byte_order_id  = bo.id and bu.id = bo.byte_user_id and po.byte_order_id = bo.id and po.product_id = :productId ; ", nativeQuery = true)
    @RestResource(path = "reviewUsers", rel = "reviewUsers")
    List<ByteUser> findByteUsersFromReview(@Param("productId") int productId);

}
