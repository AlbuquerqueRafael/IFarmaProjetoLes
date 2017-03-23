package com.ifarma.ifarma.model;

public enum OrdenationType {
    PRICE("PRICE"),
    UNDEFIN("UNDEFINED");

    private final String name;

    OrdenationType(String s) {
        name = s;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
