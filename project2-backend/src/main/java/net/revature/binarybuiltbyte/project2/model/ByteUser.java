package net.revature.binarybuiltbyte.project2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "byte_user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ByteUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    private String lastName;

    private String email;

    private String profilePic;

    @Column(name = "byte_role")
    @Enumerated(EnumType.ORDINAL)
    private Role byteRole;

    @Column(name = "user_created")
    @CreationTimestamp
    private Date userCreated;

    @Column(name = "user_terminated")
    private Date userTerminated;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "byteUser",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
    private List<Address> addresses;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "user",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
    private List<ByteOrder> byteOrders;

//    @OneToMany(
//            fetch = FetchType.LAZY,
//            mappedBy = "user",
//            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
//    private List<ProductOrderID> reviews;

}
