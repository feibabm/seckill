package com.fndsoft.service;

import com.fndsoft.mybatis.entity.Seckill;
import com.fndsoft.pojo.SecKillResult;

import java.util.List;

/**
 * Created by feibabm on 2017/9/6 0006.
 */
public interface SecKillService {

    List<Seckill> getAllSecKill();

    SecKillResult secKillProduct(String userPhone, long productId);
}
