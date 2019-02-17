package com.example.zhoukao1_model.contract;

import com.example.zhoukao1_model.bean.ShopBean;
import com.example.zhoukao1_model.bean.ShopYiang;
import com.example.zhoukao1_model.model.ShopModel;

import java.util.HashMap;

public interface ShopContract {


     abstract class conPresenter{
        public abstract void getshopData(HashMap<String,String> params);
        public abstract void getshopYiang(String id);
    }

    interface conModel{
         void getshopData(HashMap<String,String> params,ShopModel.getResponceDataCallback callback);
         void getshopYiang(String id,ShopModel.getResponceDataCallback callback);

    }
    interface conView{
         void shopDataSuccess(ShopBean shopBean);
         void shopYiangSuccess(ShopYiang shopYiang);
         void Frailure(String msg);
    }


}
