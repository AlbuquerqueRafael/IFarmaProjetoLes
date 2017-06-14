package com.ifarma.ifarma.util;

import com.ifarma.ifarma.model.Product;
import com.ifarma.ifarma.model.ProductPriceComparator;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Mafra on 18/02/2017.
 */
public class Utils {

    public static String convertEmail(String email){
        return email.replace(".", "dot");
    }

    public static void showSavingCostumerMsg(){
        System.out.println("SAVING CUSTOMER");
    }

    public static void showSavingProductMsg(){
        System.out.println("SAVING PRODUCT");
    }

    public static void showChildModifiedMsg(){
        System.out.println("FILHO MODIFICADO!");
    }

    public static void showChildRemovedMsg() {
        System.out.println("onChildRemoved");
    }

    public static void showChildMovedMsg() {
        System.out.println("onChildMoved");
    }

    public static void showOnCancelledMsg() {
        System.out.println("onCancelled");
    }

    public static void orderByPrice(List<Product> products){
        Collections.sort(products, new ProductPriceComparator());
    }

    public static String formatDate(Calendar data){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSS");

        return sdf.format(data.getTime());
    }
}

