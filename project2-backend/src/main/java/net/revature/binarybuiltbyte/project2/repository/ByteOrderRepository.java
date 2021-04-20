package net.revature.binarybuiltbyte.project2.repository;

import net.revature.binarybuiltbyte.project2.model.ByteOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
@RepositoryRestResource(collectionResourceRel = "byteOrders", path = "byte-order")
public interface ByteOrderRepository extends JpaRepository<ByteOrder, Integer> {

    @Query(value="select bo.byte_user_id , bo.id  , bo.order_completed , bo.order_created , bo.status " +
            "from byte_order bo , byte_user bu , product_order po " +
            "where bo.byte_user_id = bu.id  and po.byte_order_id = bo.id and po.product_id = :productId and bo.byte_user_id=:userId  ; \n", nativeQuery = true)
    @RestResource(path = "byteOrderReview", rel = "byteOrderReview")
    ByteOrder findAByte(@Param("productId") int productId , @Param("userId") int userId);


}
