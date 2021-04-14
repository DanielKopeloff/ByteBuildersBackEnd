package net.revature.binarybuiltbyte.project2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Properties;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int review_id;

    private String comment;

    @Column(name = "review_created")
    @CreationTimestamp
    private Date reviewCreated;

    @Column(name = "review_terminated")
    private Date reviewTerminated;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id",
            insertable = false,
            updatable = false)
    private ByteUser byteUser;


}
