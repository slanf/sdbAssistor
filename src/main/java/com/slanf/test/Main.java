package com.slanf.test;

import com.slanf.reference.annotation.Bind;

/**
 * Created by Song on 2017/6/5.
 */
public class Main {
    @Bind("")
    SqlTest sqlTest;

    public void test(){
        sqlTest.select();
    }

    public static void main(String [] args){
        System.out.println("asd");
    }
}
