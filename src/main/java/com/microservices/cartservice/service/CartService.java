package com.microservices.cartservice.service;

import com.microservices.cartservice.VO.ResponseTemplateVO;
import com.microservices.cartservice.VO.User;
import com.microservices.cartservice.entity.Cart;
import com.microservices.cartservice.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CartService {


    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public ResponseTemplateVO getCartWithUser(Long cartId) {
        ResponseTemplateVO vo = new ResponseTemplateVO();
        Cart cart = cartRepository.findByCartId(cartId);

        User user =
                restTemplate.getForObject("http://USER-SERVICE/users/" + cart.getUserId(), User.class);

        vo.setCart(cart);
        vo.setUser(user);
        return vo;
    }
}
