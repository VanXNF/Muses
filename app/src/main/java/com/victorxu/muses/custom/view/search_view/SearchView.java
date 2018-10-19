package com.victorxu.muses.custom.view.search_view;

import android.content.Context;
import android.content.res.TypedArray;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageButton;
import android.text.Editable;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.LinearLayout;
import android.text.TextWatcher;

import com.victorxu.muses.R;

public class SearchView extends LinearLayout implements TextWatcher, View.OnClickListener {

    private AppCompatEditText mEtSearchView;
    private OnSearchViewClickListener onSearchViewClickListener;
    private AppCompatImageButton mBtnClear;

    public SearchView(final Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.search_view, this, true);
        mEtSearchView = findViewById(R.id.search_view);
        mBtnClear = findViewById(R.id.button_search_clear);
        mBtnClear.setVisibility(GONE);
        mEtSearchView.addTextChangedListener(this);
        mBtnClear.setOnClickListener(this);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SearchView);
        String hint = array.getString(R.styleable.SearchView_hint);
        setSearchViewHint(hint);
        boolean isEditable = array.getBoolean(R.styleable.SearchView_editable, false);
        boolean isFocusableInTouchMode = array.getBoolean(R.styleable.SearchView_focusableInTouchMode, false);
        setSearchViewEditable(isEditable);
        setFocusableInTouchMode(isFocusableInTouchMode);
        if (!isEditable) {
            mEtSearchView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onSearchViewClickListener.onClick(v);
                }
            });
        }

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

    public void setOnSearchViewClickListener(OnSearchViewClickListener onSearchViewClickListener) {
        this.onSearchViewClickListener = onSearchViewClickListener;
    }

    public void setSearchViewHint(String hint) {
        mEtSearchView.setHint(hint);
    }

    public void setSearchViewEditable(boolean isEditable) {
        mEtSearchView.setInputType(isEditable ? InputType.TYPE_CLASS_TEXT : InputType.TYPE_NULL);
    }

    public void setFocusableInTouchMode(boolean focusableInTouchMode) {
        mEtSearchView.setFocusableInTouchMode(focusableInTouchMode);
    }
}
