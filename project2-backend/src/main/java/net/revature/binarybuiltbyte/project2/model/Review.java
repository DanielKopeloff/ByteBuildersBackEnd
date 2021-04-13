package net.revature.binarybuiltbyte.project2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review  {

    /**
     * My thinking for this implementation of composite keys is to ensure that a user can only review a product
     * once. Since its composite the combination of the both userId and the ProductId must be unique.
     * Normally i hate this but i cant think of another way
     */
    @EmbeddedId
    private ProductOrderID productOrderID;

    private String comment;

    @Column(name = "review_created")
    @CreationTimestamp
    private Date reviewCreated;

    @Column(name = "review_terminated")
    private Date reviewTerminated;





}
