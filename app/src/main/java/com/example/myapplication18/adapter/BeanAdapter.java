package com.example.myapplication18.adapter;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author 小狼不是哈士奇
 * @date 2021/3/11 10:02
 * @QQ 1481583730
 */
public abstract class BeanAdapter<SSR> extends AppCompatActivity {
    private SSR presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = getData();
        setContentView(getLayoutId());
        initView();
        initData();
    }

    protected abstract void initView();

    protected abstract void initData();

    protected abstract int getLayoutId();

    protected abstract SSR getData();
}
