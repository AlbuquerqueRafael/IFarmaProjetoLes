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

    private static HashMap<Product, Integer> cartList = new HashMap<Product, Integer>();
    private static List<Product> selectedItems = new ArrayList<>();

    public static boolean addToSelectedItem(Product product) {
        if (!selectedItems.contains(product)) {
            selectedItems.add(product);
            return true;
        } else {
            return false;
        }
    }

    public static boolean addToCart(Product product){
        boolean contains = false;
        if (cartList.isEmpty()){
            cartList.put(product, 1);
            return true;
        } else {
            for (Map.Entry<Product, Integer> entry : cartList.entrySet()) {
                if (entry.getKey().getNameProduct().equals(product.getNameProduct())) {
                    contains = true;
                    break;
                }
            }

            if (!contains)
                cartList.put(product, 1);

            return !contains;
        }
    }

    public static HashMap<Product, Integer>  getCartList(){
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
        selectedItems = new ArrayList<>();
    }

    public static String getTotalPrice(Double change) {
        double soma = 0;
        for (HashMap.Entry<Product, Integer> entry : cartList.entrySet()) {
            soma += entry.getKey().getPrice() * entry.getValue();
        }

        String somaFormatada = "R$ " + String.format("%.2f", soma + change);

        return somaFormatada;
    }

    public static List<Product> getSelectedItems() {
        return selectedItems;
    }
}
