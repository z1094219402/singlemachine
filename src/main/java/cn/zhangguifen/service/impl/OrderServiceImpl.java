package cn.zhangguifen.service.impl;

import cn.zhangguifen.dao.OrderMapper;
import cn.zhangguifen.entities.Order;
import cn.zhangguifen.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yangken on 2020/9/10.
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Override
    public int insert(Order order) {
        return orderMapper.insertOrder(order);
    }
}