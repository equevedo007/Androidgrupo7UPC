package com.example.androidgrupo7upc.network.impl;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;
import static com.android.volley.Request.Method.GET;
import static com.example.androidgrupo7upc.network.RESTManager.getContext;
import static com.example.androidgrupo7upc.network.RESTManager.getRequestQueue;
import static com.example.androidgrupo7upc.util.Constants.WS_OPEVISO_GENERAL_PATH;
import static com.example.androidgrupo7upc.util.Util.getValueJSON;
import static java.lang.String.valueOf;

import com.android.volley.toolbox.JsonArrayRequest;
import com.example.androidgrupo7upc.model.DepartmentType;
import com.example.androidgrupo7upc.model.DistrictType;
import com.example.androidgrupo7upc.model.ProvinceType;
import com.example.androidgrupo7upc.model.UbigeoResponse;
import com.example.androidgrupo7upc.network.RESTManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UbigeoApi {

    public static void getDepartments(final RESTManager.RESTListener<UbigeoResponse> ubigeoListener) {
        String url = WS_OPEVISO_GENERAL_PATH + "/v1/ubigeo/dep";

        JsonArrayRequest jsonArrayResponse = new JsonArrayRequest(GET, url, null, response -> {
            UbigeoResponse ubigeoResponse = null;

            try {
                List<DepartmentType> departmentTypeList = new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {
                    JSONObject departamentoObject = response.getJSONObject(i);
                    DepartmentType departmentType = new DepartmentType();
                    departmentType.setIdDepartamento(valueOf(getValueJSON(departamentoObject, "idDepartamento")));
                    departmentType.setDescDepartamento(valueOf(getValueJSON(departamentoObject, "descDepartamento")));

                    departmentTypeList.add(departmentType);
                }
                ubigeoResponse = new UbigeoResponse();
                ubigeoResponse.setDepartmentTypeList(departmentTypeList);
            } catch (JSONException e) {
                makeText(getContext(), valueOf(e), LENGTH_LONG).show();
            }
            ubigeoListener.onResult(ubigeoResponse);
        }, error -> makeText(getContext(), valueOf(error), LENGTH_LONG).show());

        getRequestQueue().add(jsonArrayResponse);
    }

    public static void getProvinces(final RESTManager.RESTListener<UbigeoResponse> ubigeoListener, String idDepartamento) {
        String url = WS_OPEVISO_GENERAL_PATH + "/v1/ubigeo/prov?dep=" + idDepartamento;

        JsonArrayRequest jsonArrayResponse = new JsonArrayRequest(GET, url, null, response -> {
            UbigeoResponse ubigeoResponse = null;

            try {
                List<ProvinceType> provinceTypeList = new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {
                    JSONObject provinciaObject = response.getJSONObject(i);
                    ProvinceType provinceType = new ProvinceType();
                    provinceType.setIdProvincia(valueOf(getValueJSON(provinciaObject, "idProvincia")));
                    provinceType.setDescProvincia(valueOf(getValueJSON(provinciaObject, "descProvincia")));

                    provinceTypeList.add(provinceType);
                }
                ubigeoResponse = new UbigeoResponse();
                ubigeoResponse.setProvinceTypeList(provinceTypeList);
            } catch (JSONException e) {
                makeText(getContext(), valueOf(e), LENGTH_LONG).show();
            }
            ubigeoListener.onResult(ubigeoResponse);
        }, error -> makeText(getContext(), valueOf(error), LENGTH_LONG).show());

        getRequestQueue().add(jsonArrayResponse);
    }

    public static void getDistricts(final RESTManager.RESTListener<UbigeoResponse> ubigeoListener, String idProvincia) {
        String url = WS_OPEVISO_GENERAL_PATH + "/v1/ubigeo/dist?prov=" + idProvincia;

        JsonArrayRequest jsonArrayResponse = new JsonArrayRequest(GET, url, null, response -> {
            UbigeoResponse ubigeoResponse = null;

            try {
                List<DistrictType> districtTypeList = new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {
                    JSONObject distritoObject = response.getJSONObject(i);
                    DistrictType districtType = new DistrictType();
                    districtType.setIdUbigeo(valueOf(getValueJSON(distritoObject, "idUbigeo")));
                    districtType.setIdDistrito(valueOf(getValueJSON(distritoObject, "idDistrito")));
                    districtType.setDescDistrito(valueOf(getValueJSON(distritoObject, "descDistrito")));

                    districtTypeList.add(districtType);
                }
                ubigeoResponse = new UbigeoResponse();
                ubigeoResponse.setDistrictTypes(districtTypeList);
            } catch (JSONException e) {
                makeText(getContext(), valueOf(e), LENGTH_LONG).show();
            }
            ubigeoListener.onResult(ubigeoResponse);
        }, error -> makeText(getContext(), valueOf(error), LENGTH_LONG).show());

        getRequestQueue().add(jsonArrayResponse);
    }
}
