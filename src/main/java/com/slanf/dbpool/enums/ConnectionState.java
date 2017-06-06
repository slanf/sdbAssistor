package com.slanf.dbpool.enums;

/**
 * Created by Song on 2017/5/31.
 * 链接状态
 */
public enum  ConnectionState {
    CREATED,  //创建
    READY,  //就绪
    RUNNING,  //运行中
    INTERUPTED,  //中断
    DEAD //死亡
}
