package com.ifarma.ifarma.model;

import java.util.Comparator;

/**
 * Created by Rafael on 3/18/2017.
 */

public class ProductPriceComparator implements Comparator<Product> {

    @Override
    public int compare(Product product, Product t1) {
        if (product.getPrice() < t1.getPrice()) {
            return -1;
        }else if (product.getPrice() > t1.getPrice()) {
            return 1;
        }

        return 0;
    }
}