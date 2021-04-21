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

    @Column(name = "nameOnCard")
    private String nameOnCard;

    @Column(name = "credit_card_number")
    private Long creditCardNumber;

    @Column(name = "credit_card_type")
    private String creditCardType;

    @Column(name = "expiration_date_month")
    private int expirationDateMonth;

    @Column(name="expiration_date_year")
    private int expirationDateYear;

//    @OneToOne(cascade = CascadeType.PERSIST)
//    private ByteUser byteUser;//change

    @OneToOne(cascade = CascadeType.PERSIST)
    private ByteOrder byteOrder;//c// d this to byte_user

    @Column(name = "payment_created")
    @CreationTimestamp
    private Date paymentCreated;

}

