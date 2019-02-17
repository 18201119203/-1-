package com.example.zhoukao1_model.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zhoukao1_model.R;
import com.example.zhoukao1_model.adapter.ShopDataAdapter;
import com.example.zhoukao1_model.bean.ShopBean;
import com.example.zhoukao1_model.bean.ShopData;
import com.example.zhoukao1_model.bean.ShopYiang;
import com.example.zhoukao1_model.contract.ShopContract;
import com.example.zhoukao1_model.gen.GreenDaoUtils;
import com.example.zhoukao1_model.gen.ShopDataDao;
import com.example.zhoukao1_model.presenter.ShopPresenter;
import com.gyf.barlibrary.ImmersionBar;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements ShopContract.conView,ShopDataAdapter.onClickitem{

    private Unbinder bind;

    @BindView(R.id.rv)
    XRecyclerView rv;
    @BindView(R.id.but_name)
    Button but_name;
    @BindView(R.id.et_name)
    EditText et_name;
    private int page=1;
    private String count="5";
    private ShopPresenter shopPresenter;
    private ShopDataAdapter shopDataAdapter;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //沉浸式
        ImmersionBar.with(this)
                .keyboardEnable(true)
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .statusBarColor(R.color.colorPrimary)
                .init();
        bind = ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        shopPresenter = new ShopPresenter(this);
        shopDataAdapter = new ShopDataAdapter(this);
        shopDataAdapter.setOnclickitem(this);
        rv.setLayoutManager(new GridLayoutManager(this,2));
        rv.setAdapter(shopDataAdapter);
        rv.setPullRefreshEnabled(true);
        rv.setLoadingMoreEnabled(true);
        //刷新加载
        rv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                initShopdata();
            }

            @Override
            public void onLoadMore() {
                page++;
                initShopdata();
            }
        });

    }


    @OnClick(R.id.but_name)
    public void but(View view){
        initShopdata();
    }

    //向p层传值
    private void initShopdata() {
        String s = et_name.getText().toString();
        if ("".equals(s)){
            return;
        }
        HashMap<String,String> params = new HashMap<>();
        params.put("keyword",s);
        params.put("page",page+"");
        params.put("count",count);
        shopPresenter.getshopData(params);
    }

    private void initShopYiang(String id){
        shopPresenter.getshopYiang(id);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
        ImmersionBar.with(this).destroy();
        shopPresenter.uBindView();
    }

    @Override
    public void shopDataSuccess(ShopBean shopBean) {

        ShopData shopData = new ShopData();
        for (ShopBean.shopData Data : shopBean.result) {
            shopData.setCommodityId(Long.parseLong(Data.commodityId));
            shopData.setCommodityName(Data.commodityName);
            shopData.setMasterPic(Data.masterPic);
            shopData.setPrice(Data.price);
        }
        ShopDataDao shopDataDao = new GreenDaoUtils().getDaoSession().getShopDataDao();
        shopDataDao.insert(shopData);

        if (shopBean!=null){
            if (page==1){
                shopDataAdapter.setList(shopBean.result);
            }else{
                shopDataAdapter.addList(shopBean.result);
            }
            rv.loadMoreComplete();
            rv.refreshComplete();
        }
    }

    @Override
    public void shopYiangSuccess(ShopYiang shopYiang) {
        EventBus.getDefault().postSticky(shopYiang);
        startActivity(new Intent(MainActivity.this,ShopActivity.class));
    }

    @Override
    public void Frailure(String msg) {


    }

    @Override
    public void getId(String id) {
        this.id = id;
        initShopYiang(id);
    }
}
