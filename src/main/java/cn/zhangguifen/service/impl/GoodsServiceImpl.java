package cn.zhangguifen.service.impl;

import cn.zhangguifen.dao.GoodsMapper;
import cn.zhangguifen.entities.Goods;
import cn.zhangguifen.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yangken on 2020/9/10.
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Override
    public Goods selectById(long id) {
        return goodsMapper.selectById(id);
    }

    @Override
    public int seckill(long id) {
        return goodsMapper.seckillById(id);
    }
}