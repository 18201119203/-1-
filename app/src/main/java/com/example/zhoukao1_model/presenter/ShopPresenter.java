package com.example.zhoukao1_model.presenter;

import android.view.View;

import com.example.zhoukao1_model.bean.ShopBean;
import com.example.zhoukao1_model.bean.ShopYiang;
import com.example.zhoukao1_model.contract.ShopContract;
import com.example.zhoukao1_model.model.ShopModel;
import com.google.gson.Gson;

import java.lang.ref.WeakReference;
import java.util.HashMap;

public class ShopPresenter extends ShopContract.conPresenter{

    private ShopContract.conView view;
    private WeakReference<ShopContract.conView> wr;
    private ShopModel shopModel;

    public ShopPresenter(ShopContract.conView view) {
        wr =  new WeakReference<>(view);
        this.view = wr.get();
        shopModel = new ShopModel();

    }
    @Override
    public void getshopData(HashMap<String, String> params) {

        shopModel.getshopData(params, new ShopModel.getResponceDataCallback() {
            @Override
            public void Success(String res) {
                if (view!=null){
                    ShopBean shopBean = new Gson().fromJson(res, ShopBean.class);
                    view.shopDataSuccess(shopBean);
                }
            }

            @Override
            public void Frailure(String msg) {
                if (view!=null){
                    view.Frailure(msg);
                }
            }
        });
    }

    @Override
    public void getshopYiang(String id) {

        shopModel.getshopYiang(id, new ShopModel.getResponceDataCallback() {
            @Override
            public void Success(String res) {
                if (view!=null){
                    ShopYiang shopYiang = new Gson().fromJson(res, ShopYiang.class);
                    view.shopYiangSuccess(shopYiang);
                }
            }

            @Override
            public void Frailure(String msg) {
                if (view!=null){
                    view.Frailure(msg);
                }
            }
        });


    }


    public void uBindView(){
        if (view!=null){
            wr.clear();
            wr=null;
            view=null;
        }
    }


}
