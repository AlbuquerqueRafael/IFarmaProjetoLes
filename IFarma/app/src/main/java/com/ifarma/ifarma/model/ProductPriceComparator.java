package com.ifarma.ifarma.model;

import java.io.Serializable;
import java.util.Comparator;

public class ProductPriceComparator implements Comparator<Product>, Serializable {

    @Override
    public int compare(Product product, Product t1) {
        if (Double.compare(product.getPrice(), t1.getPrice()) < 0) {
            return -1;
        }else if (Double.compare(product.getPrice(), t1.getPrice()) > 0) {
            return 1;
        }
        return 0;
    }
}