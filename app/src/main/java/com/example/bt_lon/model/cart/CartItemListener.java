package com.example.bt_lon.model.cart;

public interface CartItemListener {
    void onCheckBoxChecked(int position, boolean isChecked);

    void onPlusButtonClicked(int position);

    void onMinusButtonClicked(int position);
}
