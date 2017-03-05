package com.ifarma.ifarma.services;

import com.ifarma.ifarma.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Gabriel on 03/03/2017.
 */

public class CartService {

    private static Map<Product, Integer> cartList = new HashMap<>();

    public static void addToCart(Product product){
        cartList.put(product, 1);
    }

    public static Map<Product, Integer>  getCartList(){
        return cartList;
    }

    public static void removeFromCart(Product product){
        cartList.remove(product);
    }

    public static void editCart(Product product, Integer amount){
        cartList.put(product, amount);
    }

    public static void closeCart(){
        cartList = new HashMap<>();
    }

    public static String getTotalPrice() {
        double soma = 0;
        for (Product product : cartList.keySet()) {
            soma += product.getPrice();
        }

        String somaFormatada = "R$ " + String.format("%.2f", soma);

        return somaFormatada;
    }

}
