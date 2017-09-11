package com.fndsoft.mybatis;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Created by feibabm on 2017/4/5 0005.
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
