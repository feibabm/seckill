package com.fndsoft.controller;

import com.fndsoft.mybatis.entity.Seckill;
import com.fndsoft.pojo.CommonResponse;
import com.fndsoft.pojo.SecKillResult;
import com.fndsoft.service.SecKillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by feibabm on 2017/9/6 0006.
 */
@RestController
@RequestMapping("/seckill")
public class SecKillController {

    @Autowired
    private SecKillService secKillService;

    @RequestMapping("/product/list")
    public Object getAllSecKill(){
        List<Seckill> allSecKill = secKillService.getAllSecKill();
        return CommonResponse.buildRespose4Success(allSecKill,"获得秒杀商品页成功");
    }

//    @RequestMapping("/curenttime")
//    public Object getSystemDate(){
//        return CommonResponse.buildRespose4Success(System.currentTimeMillis(), "系统时间");
//    }
    @RequestMapping(value = "/product/{productId}")
    public Object secKillProduct(@RequestParam("userId") String userId, @PathVariable("productId") long  productId){
        SecKillResult secKillResult = secKillService.secKillProduct(userId, productId);
        return CommonResponse.buildRespose4Success(secKillResult, "处理成功");
    }
}
