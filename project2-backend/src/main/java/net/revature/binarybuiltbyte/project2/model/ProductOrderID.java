package net.revature.binarybuiltbyte.project2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductOrderID implements Serializable {

    @OneToOne(cascade = CascadeType.PERSIST)
    private ByteUser userId;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Product productId;

}
