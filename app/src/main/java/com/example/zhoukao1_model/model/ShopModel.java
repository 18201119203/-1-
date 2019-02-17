package com.example.zhoukao1_model.model;

import com.example.zhoukao1_model.api.Api;
import com.example.zhoukao1_model.contract.ShopContract;
import com.example.zhoukao1_model.retrofitutils.RetrofitCallback;
import com.example.zhoukao1_model.retrofitutils.RetrofitUtils;

import java.util.HashMap;

public class ShopModel implements ShopContract.conModel {


    @Override
    public void getshopData(HashMap<String, String> params, final getResponceDataCallback callback) {
        String keyword = params.get("keyword");
        String page = params.get("page");
        String count = params.get("count");
        RetrofitUtils.initRetrofitUtils().doGet(Api.selectUrl + "?keyword=" + keyword + "&page=" + page + "&count=" + count, new RetrofitCallback() {
            @Override
            public void Success(String response) {
                if (callback!=null){
                    callback.Success(response);
                }
            }

            @Override
            public void Failure(String msg) {
                if (callback!=null){
                    callback.Frailure(msg);
                }
            }
        });


    }

    @Override
    public void getshopYiang(String id, final getResponceDataCallback callback) {
        RetrofitUtils.initRetrofitUtils().doGet(Api.shopUrl + "?commodityId=" + id, new RetrofitCallback() {
            @Override
            public void Success(String response) {
                if (callback!=null){
                    callback.Success(response);
                }
            }

            @Override
            public void Failure(String msg) {
                if (callback!=null){
                    callback.Frailure(msg);
                }
            }
        });

    }

    public interface getResponceDataCallback{
        void Success(String res);
        void Frailure(String msg);
    }

}
