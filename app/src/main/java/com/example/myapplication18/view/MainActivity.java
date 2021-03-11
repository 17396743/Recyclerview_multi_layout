package com.example.myapplication18.view;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.widget.Toast;

import com.example.myapplication18.R;
import com.example.myapplication18.adapter.BeanAdapter;
import com.example.myapplication18.adapter.RecyclerViewAdapter;
import com.example.myapplication18.contrat.Contract;
import com.example.myapplication18.model.Bean;
import com.example.myapplication18.presenter.Presenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

public class MainActivity extends BeanAdapter implements Contract.IView {

    private androidx.recyclerview.widget.RecyclerView rvMain;
    private com.scwang.smartrefresh.layout.SmartRefreshLayout smartMain;

    @Override
    protected void initView() {
        rvMain = (RecyclerView) findViewById(R.id.rv_main);
        smartMain = (SmartRefreshLayout) findViewById(R.id.smart_main);
    }

    @Override
    protected void initData() {
//        getData().getData();
        smartMain.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                getData().getData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getData().getData();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected Presenter getData() {
        return new Presenter(this);
    }

    @Override
    public void getData(Bean bean) {
        smartMain.finishRefresh();
        smartMain.finishLoadMore();
        Log.d("TAG", bean.getData().get(0).getTitle() + "完成");
        rvMain.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(bean, this);
        rvMain.setAdapter(recyclerViewAdapter);

        recyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(MainActivity.this, "你点击了第" + position+"个选项", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void getError(String error) {
        Log.e("TAG", "" + error);
    }
}