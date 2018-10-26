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
                data.add(new ProductItem(i, "https://s1.ax1x.com/2018/04/02/CS2Ljg.jpg", "客厅北欧风格装饰画", "现代简约 高档三联 风景挂画", "￥716"));
            } else {
                data.add(new ProductItem(i, "https://s1.ax1x.com/2018/03/30/9vIx9P.jpg", "荷花禅意装饰画", "实木挂轴画 现代简约 卷轴墙壁画", "￥439"));
            }
        }
        return data;
    }
}
