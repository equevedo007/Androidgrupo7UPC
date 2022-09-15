package com.example.androidgrupo7upc.model;

import androidx.annotation.NonNull;

public class MasterType extends ResponseType {

    private String id;
    private String value;

    public MasterType(String id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @NonNull
    @Override
    public String toString() {
        return value;
    }
}
