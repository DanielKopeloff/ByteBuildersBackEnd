package net.revature.binarybuiltbyte.project2.controller;

import net.revature.binarybuiltbyte.project2.dto.Purchase;
import net.revature.binarybuiltbyte.project2.service.CheckoutService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:4200" , "http://bytebuildersbucket.s3-website.us-east-2.amazonaws.com"})
@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {
    private final CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/purchase")
    public void placeOrder(@RequestBody Purchase purchase) {
        checkoutService.placeOrder(purchase);
    }

}
