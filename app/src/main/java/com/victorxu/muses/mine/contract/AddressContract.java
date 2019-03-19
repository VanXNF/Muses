package com.victorxu.muses.mine.contract;

import com.victorxu.muses.gson.Address;

import java.util.List;

public interface AddressContract {
    interface Model {
        void getAddressData(okhttp3.Callback callback);

        void addAddressData(Address.AddressBean data, okhttp3.Callback callback);

        void deleteAddressData(int addressId, okhttp3.Callback callback);

        void updateAddressData(Address.AddressBean data, okhttp3.Callback callback);

        void cancelTask();
    }

    interface View {
        void initRootView(android.view.View view);

        void showAddress(List<Address.AddressBean> data);

        void showToast(int resId);

        void showToast(CharSequence text);
    }

    interface Presenter {
        void loadRootView(android.view.View view);

        void loadDataToView();

        void addAddress(Address.AddressBean data);

        void deleteAddress(int id);

        void updateAddress(Address.AddressBean data);

        void destroy();
    }
}
