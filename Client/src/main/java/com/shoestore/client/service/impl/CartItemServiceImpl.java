package com.shoestore.client.service.impl;

import com.shoestore.client.dto.request.CartItemDTO;
import com.shoestore.client.dto.request.VoucherDTO;
import com.shoestore.client.dto.response.CartItemResponseDTO;
import com.shoestore.client.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {

  private static final String SERVER_BASE_URL = "http://localhost:8080/cart/";

  @Autowired
  private RestTemplate restTemplate;

  @Override
  public List<CartItemResponseDTO> getCartItemsByCartId(int cartId) {
    String url = SERVER_BASE_URL+"cart-item/" + cartId;

    CartItemResponseDTO[] cartItems = restTemplate.getForObject(url, CartItemResponseDTO[].class);
    return cartItems != null ? Arrays.asList(cartItems) : List.of();
  }
  @Override
  public CartItemDTO getCartItemById(CartItemDTO.IdDTO id) {
    int cartId = id.getCartId();
    int productDetailId = id.getProductDetailId();
    String apiUrl = SERVER_BASE_URL + cartId + "/" + productDetailId;

    try {
      CartItemDTO cartItemDTO = restTemplate.getForObject(apiUrl, CartItemDTO.class);
      System.out.println(cartItemDTO);
      return cartItemDTO;
    } catch (HttpClientErrorException e) {
      if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
        System.out.println("Cart item not found: " + apiUrl);
        return null; // Không tìm thấy sản phẩm, trả về null
      }
      throw e; // Ném lỗi khác nếu không phải 404
    }
  }

  @Override
  public CartItemDTO addCartItem(CartItemDTO cartItemDTO) {
    String apiUrl = "http://localhost:8080/cart/add";
    ResponseEntity<CartItemDTO> response=restTemplate.postForEntity(
            apiUrl,cartItemDTO, CartItemDTO.class
    );
    return response.getBody();
  }

  @Override
  public CartItemDTO updateCartItem(CartItemDTO.IdDTO id, CartItemDTO cartItemDTO) {
    HttpEntity<CartItemDTO> request = new HttpEntity<>(cartItemDTO);
    int cardId=id.getCartId();
    int productDetailId=id.getProductDetailId();
    ResponseEntity<CartItemDTO> response = restTemplate.exchange(
            SERVER_BASE_URL +"/update/" + cardId+"/"+productDetailId,
            HttpMethod.PUT,
            request,
            CartItemDTO.class
    );
    return response.getBody();
  }

  @Override
  public void deleteCartItem(CartItemDTO.IdDTO id) {
    int cardId=id.getCartId();
    int productDetailId=id.getProductDetailId();
    String apiUrl=SERVER_BASE_URL+"/delete/"+cardId+"/"+productDetailId;
    System.out.println(apiUrl);
    restTemplate.delete(apiUrl);
  }




}
