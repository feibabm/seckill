package com.fndsoft.service.impl;

import com.fndsoft.mybatis.dao.SeckillMapper;
import com.fndsoft.mybatis.dao.SuccessKilledMapper;
import com.fndsoft.mybatis.entity.Seckill;
import com.fndsoft.mybatis.entity.SuccessKilled;
import com.fndsoft.pojo.ProductInfo;
import com.fndsoft.pojo.SecKillResult;
import com.fndsoft.service.SecKillService;
import org.apache.commons.lang.SystemUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by feibabm on 2017/9/6 0006.
 */
@Service
@Transactional
public class SecKillServiceImpl implements SecKillService {

    @Autowired
    private SeckillMapper seckillMapper;
    
    @Autowired
    private SuccessKilledMapper successKilledMapper;
    

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<Seckill> getAllSecKill() {
        redisTemplate.opsForValue().set("test", "test");
        String test = (String)redisTemplate.opsForValue().get("test");
        System.out.println(test);
        return seckillMapper.selectAll();
    }

    @Override
    public SecKillResult secKillProduct(String userPhone, long productId) {
        Byte state = (Byte)redisTemplate.opsForValue().get(userPhone + "_"+ productId);
        //用户信息加载
        if(null == state){
            SuccessKilled successKilled = new SuccessKilled();
            successKilled.setSeckillId(productId);
            successKilled.setUserPhone(Long.valueOf(userPhone));
            successKilled = successKilledMapper.selectOne(successKilled);
            if(null == successKilled){
                return new SecKillResult(false, "该用户没有预约");
            }else{
                synchronized (this){
                    state = (Byte)redisTemplate.opsForValue().get(userPhone + "_"+ productId);
                    if(null == state){
                        redisTemplate.opsForValue().set(userPhone + "_" + productId, successKilled.getState(), 300, TimeUnit.SECONDS);
                        state = successKilled.getState();
                    }
                }

            }
        }
        if(state == -1){
            //查询产品信息
            ProductInfo productInfo = (ProductInfo)redisTemplate.opsForValue().get(productId);
            if(null == productInfo){
                Seckill seckill = seckillMapper.selectByPrimaryKey(productId);
                if(null == seckill){
                    return new SecKillResult(false, "没有该秒杀商品信息");
                }
                synchronized (this){
                    productInfo = (ProductInfo)redisTemplate.opsForValue().get(productId);
                    if(null == productInfo){
                        productInfo = new ProductInfo(seckill.getSeckillId(), seckill.getNumber(), seckill.getStartTime(), seckill.getEndTime());
                        redisTemplate.opsForValue().set(productId, productInfo, 300,TimeUnit.SECONDS);
                    }
                }
            }
            if( productInfo.getStartTime().after(new Date(System.currentTimeMillis()))){
                return new SecKillResult(false, "抢购还没有开始");
            } else if(productInfo.getEndTime().before(new Date(System.currentTimeMillis()))){
                return new SecKillResult(false, "抢购已经结束");
            } else {
                Long increment = redisTemplate.opsForValue().increment(productId, -1);
                if(increment > 0){
                    redisTemplate.opsForValue().increment(userPhone + "_"+ productId, 1);
                    //消息队列异步更新库存，以及用户的预约信息
                }else {
                    return new SecKillResult(false, "商品已经抢购完成");
                }
            }
        } else {
            return new SecKillResult(false, "您已抢购过该产品");
        }
        return null;
    }
}
