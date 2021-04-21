package net.revature.binarybuiltbyte.project2.dto;

import lombok.Data;
import net.revature.binarybuiltbyte.project2.model.*;;

import java.util.Set;

@Data
public class Purchase {

    private ByteUser byteUser;
    private Address shippingAddress;
    private Address billingAddress;
    private ByteOrder byteOrder;
    private Payment payment;
    private Set<ProductOrder> productOrders;

}
