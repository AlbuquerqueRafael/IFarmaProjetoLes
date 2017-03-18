package com.ifarma.ifarma.model;

/**
 * Created by Rafael on 3/18/2017.
 */

public enum OrdenationType {
    PRICE("PRICE"),
    UNDEFIN("UNDEFINED");

    private final String name;

    private OrdenationType(String s) {
        name = s;
    }


    public String toString() {
        return this.name;
    }
}
