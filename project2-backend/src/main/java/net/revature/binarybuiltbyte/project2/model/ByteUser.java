package net.revature.binarybuiltbyte.project2.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    @Column(name = "username", unique = true)
    @NotNull
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
    private Role role;

    @Column(name = "user_created")
    @CreationTimestamp
    private Date userCreated;

    @Column(name = "user_terminated")
    private Date userTerminated;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "byteUser",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
    private Set<ByteOrder> byteOrders = new HashSet<>();

    public void add(ByteOrder byteOrder) {
        if (byteOrder != null) {
            if (byteOrders == null) {
                byteOrders = new HashSet<>();
            }
            byteOrders.add(byteOrder);
            byteOrder.setByteUser(this);
        }
    }

}
