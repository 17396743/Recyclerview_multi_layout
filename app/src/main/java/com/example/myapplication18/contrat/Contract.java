package com.example.myapplication18.contrat;

import com.example.myapplication18.model.Bean;
import com.example.netutils.INetInterface;

/**
 * @author 小狼不是哈士奇
 * @date 2021/3/11 10:02
 * @QQ 1481583730
 */
public interface Contract {
    interface IModel {
        <B> void setData(IPresenter presenter, String url, INetInterface<B> iNetInterface);
    }

    interface IPresenter {
        void getData();
    }

    interface IView {
        void getData(Bean bean);

        void getError(String error);
    }
}
