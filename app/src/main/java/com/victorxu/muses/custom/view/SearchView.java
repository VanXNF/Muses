package com.victorxu.muses.custom.view;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageButton;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.LinearLayout;
import android.text.TextWatcher;

import com.victorxu.muses.R;

public class SearchView extends LinearLayout implements TextWatcher, View.OnClickListener {

    private AppCompatEditText mEtSearchView;

    private AppCompatImageButton mBtnClear;

    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.search_view, this, true);
        mEtSearchView = findViewById(R.id.search_view);
        mBtnClear = findViewById(R.id.button_search_clear);
        mBtnClear.setVisibility(GONE);
        mEtSearchView.addTextChangedListener(this);
        mBtnClear.setOnClickListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String input = mEtSearchView.getText().toString().trim();
        if (input.isEmpty()) {
            mBtnClear.setVisibility(GONE);
        } else {
            mBtnClear.setVisibility(VISIBLE);
        }
    }

    /**
     * Clear Button
     */
    @Override
    public void onClick(View v) {
        mEtSearchView.setText("");
    }

    public void setSearchViewHint(String hint) {
        mEtSearchView.setHint(hint);
    }
}
