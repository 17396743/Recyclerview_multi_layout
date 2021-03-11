package com.example.netutils;

/**
 * @author 小狼不是哈士奇
 * @date 2021/3/11 10:29
 * @QQ 1481583730
 */
public interface INetInterfaces {
    public <B> void  get(String url, INetInterface<B> iNetInterface);
}
