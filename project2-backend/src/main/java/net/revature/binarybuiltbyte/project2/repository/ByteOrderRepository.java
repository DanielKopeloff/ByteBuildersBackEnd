package net.revature.binarybuiltbyte.project2.repository;

import net.revature.binarybuiltbyte.project2.model.ByteOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = {"http://localhost:4200" , "http://bytebuildersbucket.s3-website.us-east-2.amazonaws.com"})
@RepositoryRestResource(collectionResourceRel = "byteOrders", path = "byte-order")
public interface ByteOrderRepository extends JpaRepository<ByteOrder, Integer> {

    @Query(value="select bo.id " +
            "from byte_order bo , byte_user bu , product_order po " +
            "where bu.id = bo.byte_user_id and po.order_id = bo.id and po.product_id  = :productId and byte_user_id = :userId ; ", nativeQuery = true)
    @RestResource(path = "byteOrderReview", rel = "byteOrderReview")
    int findAByte(@Param("productId") int productId , @Param("userId") int userId);


}
