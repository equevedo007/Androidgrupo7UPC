package com.example.androidgrupo7upc.util;

import static android.content.Context.MODE_PRIVATE;
import static com.example.androidgrupo7upc.util.Constants.CLASE_BOOLEAN;
import static com.example.androidgrupo7upc.util.Constants.CLASE_INT;
import static com.example.androidgrupo7upc.util.Constants.CLASE_LONG;
import static com.example.androidgrupo7upc.util.Constants.CLASE_STRING;
import static com.example.androidgrupo7upc.util.Constants.S_CERO;
import static java.lang.String.format;
import static java.lang.String.valueOf;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.androidgrupo7upc.model.IllnessType;
import com.example.androidgrupo7upc.model.MasterType;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Util {

    public static Object getValueJSON(JSONObject jsonObject, String name) throws JSONException {
        return jsonObject.isNull(name) ? null : jsonObject.get(name);
    }

    public static List<MasterType> getMasterList(Map<String, String> map) {
        return map.entrySet().stream()
                .map(entry -> new MasterType(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    public static List<IllnessType> getIllnessList(Map<String, String> map) {
        return map.entrySet().stream()
                .map(entry -> new IllnessType(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    public static <T> Object getSharedPreference(Class<T> tClass, Context context, String key) {
        Object sharedPreferenceObj = null;
        SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPreferences", MODE_PRIVATE);

        if (CLASE_STRING.equals(tClass.getSimpleName())) {
            sharedPreferenceObj = sharedPreferences.getString(key, Constants.TEXTO_VACIO);
        } else if (CLASE_INT.equals(tClass.getSimpleName())) {
            sharedPreferenceObj = sharedPreferences.getInt(key, 0);
        } else if (CLASE_BOOLEAN.equals(tClass.getSimpleName())) {
            sharedPreferenceObj = sharedPreferences.getBoolean(key, false);
        } else if (CLASE_LONG.equals(tClass.getSimpleName())) {
            sharedPreferenceObj = sharedPreferences.getLong(key, 0);
        }
        return sharedPreferenceObj;
    }

    public static <T> void setSharedPreference(Class<T> tClass, Context context, String key, Object value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (CLASE_STRING.equals(tClass.getSimpleName())) {
            editor.putString(key, (String) value);
        } else if (CLASE_INT.equals(tClass.getSimpleName())) {
            editor.putInt(key, (Integer) value);
        } else if (CLASE_BOOLEAN.equals(tClass.getSimpleName())) {
            editor.putBoolean(key, (Boolean) value);
        } else if (CLASE_LONG.equals(tClass.getSimpleName())) {
            editor.putLong(key, (Long) value);
        }

        editor.apply();
    }

    public static String getStringLocalDateTime(LocalDateTime dateInput, String format) {
        if (dateInput != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            return dateInput.format(formatter);
        }
        return null;
    }

    public static String buildDate(int day, int month, int year) {
        month++;
        return format("%s/%s/%s",
                day < 10 ? S_CERO + day : valueOf(day),
                month < 10 ? S_CERO + month : valueOf(month),
                year);
    }

}
