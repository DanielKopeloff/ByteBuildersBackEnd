package net.revature.binarybuiltbyte.project2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.revature.binarybuiltbyte.project2.model.ByteUser;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "street_address")
    private String streetAddress;

    private String city;

    private String state;

    private String zip;

    @OneToOne
    @PrimaryKeyJoinColumn
    private ByteOrder byteOrder;
}
