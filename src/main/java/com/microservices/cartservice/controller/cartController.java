package com.microservices.cartservice.controller;

import com.microservices.cartservice.VO.ResponseTemplateVO;
import com.microservices.cartservice.entity.Cart;
import com.microservices.cartservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class cartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/addToCart")
    public Cart addCart(@RequestBody Cart cart) {
        return cartService.saveCart(cart);
    }

    @GetMapping("/{id}")
    public ResponseTemplateVO getCartWithUser(@PathVariable("id") Long cartId) {
        return cartService.getCartWithUser(cartId);
    }

}
