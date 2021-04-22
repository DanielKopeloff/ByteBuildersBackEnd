package net.revature.binarybuiltbyte.project2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Month;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "credit_card")
    private String creditCard;

    @Column(name = "expiration_date_month")
    private Month expirationDateMonth;

    @Column(name="expiration_date_year")
    private String expirationDateYear;

    @OneToOne(cascade = CascadeType.PERSIST)
    private ByteUser byte_user;//changed this to byte_user

    @Column(name = "payment_created")
    @CreationTimestamp
    private Date paymentCreated;

}

