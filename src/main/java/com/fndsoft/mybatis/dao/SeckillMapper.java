package com.fndsoft.mybatis.dao;

import com.fndsoft.mybatis.MyMapper;
import com.fndsoft.mybatis.entity.Seckill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SeckillMapper extends MyMapper<Seckill> {
    //更新库存，减一
    int updateNumber(@Param("seckillId") long seckillId);
}