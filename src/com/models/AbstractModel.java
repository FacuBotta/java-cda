package com.models;

import java.util.ArrayList;

public abstract class AbstractModel {
    public abstract void add();
    public abstract void update();
    public abstract void delete();
    public abstract boolean find();

    public static Object findBy(String param) {
        return null;
    }

    public static ArrayList<Object> findAll() {
        return null;
    }
}
