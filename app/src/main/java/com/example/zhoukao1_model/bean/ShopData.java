package com.example.zhoukao1_model.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class ShopData {

    //@Id(autoincrement = true)
    //@Unique
    @Property(nameInDb="commodityId")
    public long commodityId;
    public String commodityName;
    public String masterPic;
    public String price;
    @Generated(hash = 271625753)
    public ShopData(long commodityId, String commodityName, String masterPic,
            String price) {
        this.commodityId = commodityId;
        this.commodityName = commodityName;
        this.masterPic = masterPic;
        this.price = price;
    }
    @Generated(hash = 1119235689)
    public ShopData() {
    }
    public long getCommodityId() {
        return this.commodityId;
    }
    public void setCommodityId(long commodityId) {
        this.commodityId = commodityId;
    }
    public String getCommodityName() {
        return this.commodityName;
    }
    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }
    public String getMasterPic() {
        return this.masterPic;
    }
    public void setMasterPic(String masterPic) {
        this.masterPic = masterPic;
    }
    public String getPrice() {
        return this.price;
    }
    public void setPrice(String price) {
        this.price = price;
    }


}
