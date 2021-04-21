package net.revature.binarybuiltbyte.project2.dto;

import lombok.Data;
import net.revature.binarybuiltbyte.project2.model.Address;;
import net.revature.binarybuiltbyte.project2.model.ByteOrder;
import net.revature.binarybuiltbyte.project2.model.ByteUser;
import net.revature.binarybuiltbyte.project2.model.ProductOrder;

import java.util.Set;

@Data
public class Purchase {

    private ByteUser byteUser;
    private Address shippingAddress;
    private Address billingAddress;
    private ByteOrder byteOrder;
    private Set<ProductOrder> productOrders;

}
