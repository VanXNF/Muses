package com.victorxu.muses.search.model;

import com.victorxu.muses.search.contract.SearchResultContract;
import com.victorxu.muses.search.view.entity.ProductItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SearchResultModel implements SearchResultContract.Model {

    private int dataAmount = 30;
    private int dataNum = 10;
    private int pageSize = 6;
    private boolean isEnd = false;

    private List<ProductItem> mData;

    public SearchResultModel() {
        isEnd = dataNum >= dataAmount;
    }

    @Override
    public boolean getDataStatus() {
        isEnd = dataNum >= dataAmount;
        return isEnd;
    }

    @Override
    public List<ProductItem> getProductData() {
        mData = initTestData(dataNum);
        return mData;
    }

    @Override
    public List<ProductItem> getMoreData() {
        if (!isEnd) {
            mData = initTestData(pageSize);
            dataNum += pageSize;
        } else {
            mData = null;
        }
        return mData;
    }

    private List<ProductItem> initTestData(int num) {
        List<ProductItem> data = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < num; i++) {
            if (random.nextBoolean()) {
                data.add(new ProductItem(i, "https://img.alicdn.com/imgextra/i3/2048829272/O1CN012IMcnFurWfqbAUN_!!0-item_pic.jpg_430x430q90.jpg", "北欧风格玄关创意壁画挂画", "组合套装", "￥718"));
                data.add(new ProductItem(i, "https://img.alicdn.com/imgextra/i2/1779099327/TB24wCWpN9YBuNjy0FfXXXIsVXa_!!1779099327.jpg_430x430q90.jpg", "三联北欧创意油画大气壁画油画挂画", "现代简约 高档三联 风景挂画", "￥358"));
                data.add(new ProductItem(i, "https://img.alicdn.com/imgextra/i4/2122078663/O1CN012DrhaXqQtvjYMdJ_!!2122078663.jpg_430x430q90.jpg", "客厅装饰画沙发背景墙挂画新中式办公室", "独立 办公室 背景挂画", "￥418"));
            } else {
                data.add(new ProductItem(i, "https://img.alicdn.com/imgextra/i3/2048829272/O1CN011U7MIo2IMcnnwObD0_!!0-item_pic.jpg_430x430q90.jpg", "北欧风格现代简约组合挂画几何抽象画三联壁画", "几何抽象画 升级款", "￥220"));
                data.add(new ProductItem(i, "https://img.alicdn.com/imgextra/i1/1675711085/O1CN011JsyCTbDzUR7HRD_!!0-item_pic.jpg_430x430q90.jpg", "现代简约办公室餐厅墙面装饰壁画", "现代简约 装饰壁画", "￥286"));
                data.add(new ProductItem(i, "https://img.alicdn.com/imgextra/i4/1675711085/O1CN011JsyCYkgbcobAxN_!!0-item_pic.jpg_430x430q90.jpg", "北欧风格装饰画麋鹿挂画三联画", "环保画材 艺术微喷工艺", "￥306"));
            }
        }
        return data;
    }
}
