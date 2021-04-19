package net.revature.binarybuiltbyte.project2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String comment;

    @Column(name = "review_created")
    @CreationTimestamp
    private Date reviewCreated;

    @Column(name = "review_terminated")
    private Date reviewTerminated;

    @ManyToOne
    private ByteOrder byteOrder;

    @Column(name = "rating")
    private int rating;

}
