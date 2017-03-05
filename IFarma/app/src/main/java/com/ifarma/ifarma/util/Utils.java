package com.ifarma.ifarma.util;

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

}
