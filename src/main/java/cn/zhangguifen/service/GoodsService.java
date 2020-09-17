package cn.zhangguifen.service;

import cn.zhangguifen.entities.Goods;

/**
 * Created by yangken on 2020/9/10.
 */
public interface GoodsService {
    Goods selectById(long id);

    int seckill(long id);
}