package cn.zhangguifen.dao;

import cn.zhangguifen.entities.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created by yangken on 2020/9/10.
 */
@Mapper
@Repository
public interface OrderMapper {
    /**
     * 生成订单
     *
     * @param order 订单信息
     * @return
     */
    @Insert("insert into `order` (pid,uid) values (#{pid},#{uid})")
    int insertOrder(Order order);
}