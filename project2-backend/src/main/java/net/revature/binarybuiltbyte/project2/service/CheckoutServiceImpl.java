package net.revature.binarybuiltbyte.project2.service;

import net.revature.binarybuiltbyte.project2.dto.Purchase;
import net.revature.binarybuiltbyte.project2.model.ByteOrder;
import net.revature.binarybuiltbyte.project2.model.ByteUser;
import net.revature.binarybuiltbyte.project2.model.ProductOrder;
import net.revature.binarybuiltbyte.project2.repository.ByteUserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final ByteUserRepository byteUserRepository;

    public CheckoutServiceImpl(ByteUserRepository byteUserRepository) {
        this.byteUserRepository = byteUserRepository;
    }

    @Override
    @Transactional
    public void placeOrder(Purchase purchase) {
        ByteOrder byteOrder = purchase.getByteOrder();

        Set<ProductOrder> productOrders = purchase.getProductOrders();
        productOrders.forEach(byteOrder::add);

        byteOrder.setBillingAddress(purchase.getBillingAddress());
        byteOrder.setShippingAddress(purchase.getShippingAddress());

        ByteUser byteUser = purchase.getByteUser();
        byteUser.add(byteOrder);

        byteUserRepository.save(byteUser);
    }
}
