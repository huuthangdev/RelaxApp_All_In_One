package com.example.doantotnghiep.Service;

public class APIService {

    public static Dataservice getService() {
        String base_url = "https://huuthangshop.000webhostapp.com/Server/";
        return APIRetrofitClient.getClient(base_url).create(Dataservice.class);
    }
}
