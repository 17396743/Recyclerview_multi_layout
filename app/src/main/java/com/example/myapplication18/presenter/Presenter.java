package com.example.myapplication18.presenter;

import com.example.myapplication18.contrat.Contract;
import com.example.myapplication18.model.Bean;
import com.example.myapplication18.model.Model;
import com.example.netutils.INetInterface;
import com.example.netutils.UrlsInterface;

/**
 * @author 小狼不是哈士奇
 * @date 2021/3/11 10:02
 * @QQ 1481583730
 */
public class Presenter implements Contract.IPresenter {
    private Contract.IModel model;
    private Contract.IView view;

    public Presenter(Contract.IView view) {
        model = new Model();
        this.view = view;
    }

    @Override
    public void getData() {
        model.setData(this, UrlsInterface.NEWURL, new INetInterface<Bean>() {
            @Override
            public void setData(Bean bean) {
                view.getData(bean);
            }

            @Override
            public void setError(String error) {
                view.getError(error);
            }
        });
    }
}
