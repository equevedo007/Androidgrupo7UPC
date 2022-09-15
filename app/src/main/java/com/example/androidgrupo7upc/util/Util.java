package com.example.androidgrupo7upc.util;

import com.example.androidgrupo7upc.model.MasterType;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Util {

    public static Object getValueJSON(JSONObject jsonObject, String name) throws JSONException {
        return jsonObject.isNull(name) ? null : jsonObject.get(name);
    }

    /*public static List<MasterType> getMasterList(Map<String, String> map){
        List<MasterType> masterTypes = new ArrayList<>();

        map.entrySet().stream()
                .map(entry -> Collections.nCopies(entry.getValue(), entry.getKey()))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        return masterTypes;
    }*/
}
