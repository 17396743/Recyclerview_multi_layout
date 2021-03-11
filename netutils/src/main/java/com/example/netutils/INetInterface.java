package com.example.netutils;

/**
 * @author 小狼不是哈士奇
 * @date 2021/3/11 10:28
 * @QQ 1481583730
 */
public interface INetInterface<B> {
    void setData(B b);
    void setError(String error);
}
