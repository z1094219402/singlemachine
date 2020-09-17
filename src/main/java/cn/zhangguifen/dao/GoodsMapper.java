package cn.zhangguifen.dao;

import cn.zhangguifen.entities.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * Created by yangken on 2020/9/10.
 */
@Mapper
@Repository
public interface GoodsMapper {
    /**
     * 根据id查询商品信息
     *
     * @param id 商品id
     * @return
     */
    @Select("select * from goods where id = #{id}")
    Goods selectById(long id);

    /**
     * 商品减库存
     *
     * @param id 商品id
     * @return
     */
    @Update("update goods set store = store - 1 where store > 0 and id = #{id}")
    int seckillById(long id);
}