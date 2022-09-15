package com.example.androidgrupo7upc.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.List;

@JsonInclude(Include.NON_NULL)
public class UbigeoResponse {

    private List<DepartmentType> departmentTypeList;
    private List<ProvinceType> provinceTypeList;
    private List<DistrictType> districtTypes;

    public List<DepartmentType> getDepartmentTypeList() {
        return departmentTypeList;
    }

    public void setDepartmentTypeList(List<DepartmentType> departmentTypeList) {
        this.departmentTypeList = departmentTypeList;
    }

    public List<ProvinceType> getProvinceTypeList() {
        return provinceTypeList;
    }

    public void setProvinceTypeList(List<ProvinceType> provinceTypeList) {
        this.provinceTypeList = provinceTypeList;
    }

    public List<DistrictType> getDistrictTypes() {
        return districtTypes;
    }

    public void setDistrictTypes(List<DistrictType> districtTypes) {
        this.districtTypes = districtTypes;
    }
}
