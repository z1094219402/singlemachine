//package cn.zhangguifen.controller;
//
//import cn.zhangguifen.entities.Order;
//import cn.zhangguifen.service.GoodsService;
//import cn.zhangguifen.service.OrderService;
////import cn.zhangguifen.util.JSONUtil;
//import com.fasterxml.jackson.core.JsonFactory;
//import com.fasterxml.jackson.core.JsonGenerator;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
////import org.junit.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;
//import org.springframework.data.redis.core.script.DefaultRedisScript;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.scripting.support.ResourceScriptSource;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.IOException;
//import java.io.StringWriter;
//import java.util.Collections;
//import java.util.Objects;
//
///**
// * Created by yangken on 2020/9/10.
// */
//@RestController
//@RequestMapping("/seckill")
////@Slf4j
//public class SeckillController {
//    @Autowired
//    private StringRedisTemplate redisTemplate;
//    @Autowired
//    private GoodsService goodsService;
//    @Autowired
//    private OrderService orderService;
//    @Autowired
//    private KafkaTemplate kafkaTemplate;
//
//    Logger log = LoggerFactory.getLogger(SeckillController.class);
//
//    /*******************************************************************/
//    /**
//     * 数据预热到redis后的实现,且使用lua脚本解决用户重复秒杀的问题,并引入kafka异步处理订单
//     *
//     * @param pid
//     * @param uid
//     * @return
//     */
//    @GetMapping("/{pid}/{uid}")
//    public String seckillWithLua(@PathVariable long pid, @PathVariable long uid) throws IOException {
////        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
////        redisScript.setResultType(Long.class);
////        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("seckill.lua")));
//        //        Long result = redisTemplate.execute(redisScript, Collections.singletonList(pid + ""), uid + "");
//
//        String end = redisTemplate.opsForValue().get("seckill:" + pid + ":end");
//        log.info("what was end with [ "+end+" ].");
//        if (Objects.equals(end, "1")) {
//            return "秒杀结束";
//        }
//        //返回的decrement为剩余的库存
//        Long decrement = redisTemplate.opsForValue().decrement("seckill:" + pid + ":store");
//
//        log.info("decrement:{}", decrement);
//        if (decrement >= 0) {
//            //            //执行秒杀逻辑
//            int result = goodsService.seckill(pid);
//            if (result == 1) {
//                //创建订单
//                Order order = new Order();
//                order.setPid(pid);
//                order.setUid(uid);
//                //将创建订单的消息放入kafka消息队列
////            kafkaTemplate.send("seckill", JSONUtil.objToString(order));
//                //对象转字符串
//                ObjectMapper mapper = new ObjectMapper();
//                StringWriter sw = new StringWriter();
//                JsonGenerator gen = new JsonFactory().createJsonGenerator(sw);
//                mapper.writeValue(gen, order);
//                gen.close();
//                String json = sw.toString();
//                kafkaTemplate.send("seckill", json);
//            }
//        }
////        return "秒杀失败";
//        return decrement > 0 ? "秒杀成功" : "秒杀失败";
//    }
//
//    //kafka消费者
//    @KafkaListener(topics = "seckill", groupId = "g1")
//    public void consumer(ConsumerRecord<?, String> record) throws IOException {
//        String value = record.value();
//        //字符串转对象
//        ObjectMapper mapper = new ObjectMapper();
//        Order order = mapper.readValue(value, Order.class);
//        log.info("result of order is [ "+value+" ]");
//        orderService.insert(order);
//    }
//
//    /*********************************************/
//
////    /**
////     * 数据预热到redis后的实现,且使用lua脚本解决用户重复秒杀的问题
////     * @param pid
////     * @param uid
////     * @return
////     */
////    @GetMapping("/{pid}/{uid}")
////    public String seckillWithLua(@PathVariable long pid, @PathVariable long uid) {
////        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
////        redisScript.setResultType(Long.class);
////        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("seckill.lua")));
////        Long result = redisTemplate.execute(redisScript, Collections.singletonList(pid + ""), uid + "");
////        log.info("result:{}", result);
////        log.info("there are still [ "+result+" ] be left.");
////
////        if (result == 1) {
////            //创建订单
////            Order order = new Order();
////            order.setPid(pid);
////            order.setUid(uid);
////            orderService.insert(order);
////        }
////        return result > 0 ? "秒杀成功" : "秒杀失败";
////    }
//
////    /**
////     * 数据预热到redis后的实现（添加秒杀结束标志）
////     *
////     * @param pid 商品id
////     * @param uid 用户id
////     * @return
////     */
////    @GetMapping("/{pid}/{uid}")
////    public String seckillWithRedis(@PathVariable long pid, @PathVariable long uid) {
////        String end = redisTemplate.opsForValue().get("seckill:" + pid + ":end");
////        log.info("what was end with [ "+end+" ].");
////        if (Objects.equals(end, "1")) {
////            return "秒杀结束";
////        }
////        //返回的decrement为剩余的库存
////        Long decrement = redisTemplate.opsForValue().decrement("seckill:" + pid + ":store");
////        log.info("there are still [ "+decrement+" ] be left.");
////        //剩余库存大于等于0都为秒杀成功
////        if (decrement >= 0) {
////            if (decrement == 0) {
////                //设置该商品秒杀状态为结束
////                redisTemplate.opsForValue().set("seckill:" + pid + ":end", "1");
////            }
////            //执行秒杀逻辑
////            int result = goodsService.seckill(pid);
////            if (result == 1) {
////                //创建订单
////                Order order = new Order();
////                order.setPid(pid);
////                order.setUid(uid);
////                orderService.insert(order);
////            }
////        }
////        log.info("decrement:{}", decrement);
////        return decrement < 0 ? "秒杀失败" : "秒杀成功";
////    }
//
////    /**
////     * 数据预热到redis后的实现
////     *
////     * @param pid 商品id
////     * @param uid 用户id
////     * @return
////     */
////    @GetMapping("/{pid}/{uid}")
////    public String seckillWithRedis(@PathVariable long pid, @PathVariable long uid) {
////        //返回的decrement为剩余的库存
////        Long decrement = redisTemplate.opsForValue().decrement("seckill:" + pid+":store");
////        log.info("there are still [ "+decrement+" ] be left.");
////        //剩余库存大于等于0都为秒杀成功
////        if (decrement >= 0) {
////            //创建订单
////            Order order = new Order();
////            order.setPid(pid);
////            order.setUid(uid);
////            orderService.insert(order);
////        }
////        log.info("decrement:{}", decrement);
////        return decrement < 0 ? "秒杀失败" : "秒杀成功";
////    }
//
////    /**
////     * 未引入redis和kafka版实现
////     *
////     * @param pid 商品id
////     * @param uid 用户id
////     * @return
////     */
////    @Transactional
////    @GetMapping("/{pid}/{uid}")
////    public String seckill(@PathVariable long pid, @PathVariable long uid) {
////        //1.从数据库获取商品的库存量
////        Goods goods = goodsService.selectById(pid);
////        int stock = goods.getStore();
////        //2.判断库存是否足够,足够则进入秒杀
////        if (stock > 0) {
////            //执行秒杀逻辑
////            int result = goodsService.seckill(pid);
////            if (result == 1) {
////                //创建订单
////                Order order = new Order();
////                order.setPid(pid);
////                order.setUid(uid);
////                orderService.insert(order);
////            }
////            return result > 0 ? "秒杀成功" : "秒杀失败";
////        }
////        return "秒杀失败";
////    }
//
//}