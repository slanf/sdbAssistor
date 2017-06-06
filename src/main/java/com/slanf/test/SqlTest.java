package com.slanf.test;

import com.slanf.orm.annotation.Mapper;
import com.slanf.orm.annotation.Select;

/**
 * Created by Song on 2017/6/5.
 */
@Mapper
public interface SqlTest {
    @Select("select id from test where key = 1")
    int select();
}
