package com.example.myapplication18.model;

import com.example.myapplication18.contrat.Contract;
import com.example.netutils.INetInterface;
import com.example.netutils.RetrofitUtils;

/**
 * @author 小狼不是哈士奇
 * @date 2021/3/11 10:02
 * @QQ 1481583730
 */
public class Model implements Contract.IModel {

    @Override
    public <B> void setData(Contract.IPresenter presenter, String url, INetInterface<B> iNetInterface) {
        RetrofitUtils.getInstance().get(url, iNetInterface);
    }
}
