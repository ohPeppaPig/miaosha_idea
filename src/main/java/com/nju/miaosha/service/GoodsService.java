package com.nju.miaosha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nju.miaosha.domain.MiaoshaGoods;
import com.nju.miaosha.dao.GoodsDao;
import com.nju.miaosha.redis.RedisService;
import com.nju.miaosha.vo.GoodsVo;

@Service
public class GoodsService {
	public static final String COOKIE1_NAME_TOKEN="token";
	
	@Autowired
	GoodsDao goodsDao;
	@Autowired
	RedisService redisService;
	
	public List<GoodsVo>  getGoodsVoList() {
		return goodsDao.getGoodsVoList();
	}
	
	public GoodsVo getGoodsVoByGoodsId(long goodsId) {
		return goodsDao.getGoodsVoByGoodsId(goodsId);
	}
	


	public void reduceStock(GoodsVo goodsvo) {
		MiaoshaGoods goods=new MiaoshaGoods();
		goods.setGoodsId(goodsvo.getId());
		//goods.setStockCount(goodsvo.getGoodsStock()-1);  sql里面去运算
		//goodsDao.reduceStock(goods.getGoodsId());
		goodsDao.reduceStock(goods);
	}
	/**
	 * 考虑有可能下单失败的情况,下单失败那么就不去
	 */
	public boolean reduceStock1(GoodsVo goodsvo) {
		MiaoshaGoods goods=new MiaoshaGoods();
		goods.setGoodsId(goodsvo.getId());
		//goods.setStockCount(goodsvo.getGoodsStock()-1);  sql里面去运算
		//goodsDao.reduceStock(goods.getGoodsId());
		int ret=goodsDao.reduceStock1(goods);
		System.out.println("reduceStock1:"+ret);
		return ret>0;
		//return true;
	}
}
