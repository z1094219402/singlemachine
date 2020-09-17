package cn.zhangguifen;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
//@Slf4j
public class SinglemachineApplicationTests {
	
    @Autowired
    RedisTemplate redisTemplate;

    int[] array = new int[]{6,1,3,4,12,67,2,5,9,7};
    
    private static Logger log = LoggerFactory.getLogger(SinglemachineApplicationTests.class);

//	@Test
//	public void contextLoads() {
//        log.error("%dit is wrong..",5);
//	}

//	@Test
//	public void setRedis(){
//		try {
//            /****************String*****************/
////            String store = redisTemplate.opsForValue().get("bluetime");
////            if(store==null || store.equals("")) {
////                redisTemplate.opsForValue().set("bluetime","hello world");
////                store = redisTemplate.opsForValue().get("bluetime");
////            }
//
//            /****************Set*****************/
////            Set<String> result = redisTemplate.opsForSet().members("setthings");
////            if(result.size()==0){
////                log.info("ERROR!!!the size is ..[ "+result.size()+" ]");
////                log.info("ERROR!!!the result is ..[ "+result+" ]");
////                for(int i = 0; i < 10; i++){
////                    String name = "mongey_"+i;
////                    redisTemplate.opsForSet().add("setthings",name);
////                }
////            }
////            result = redisTemplate.opsForSet().members("setthings");
////            log.info("the size is ..[ "+result.size()+" ]");
////            log.info("the result is ..[ "+result+" ]");
//            //get factor in range way
////            String rangString = redisTemplate.opsForSet().randomMember("setthings");
////            redisTemplate.opsForSet().randomMembers("setthings",3);
////            Set<String> result = redisTemplate.opsForSet().members("setthings");
////            log.info("the size is ..[ "+result.size()+" ]");
////            log.info("the rang string result is ..[ "+rangString+" ]");
////            //pop factor
////            redisTemplate.opsForSet().pop("setthings");
////            result = redisTemplate.opsForSet().members("setthings");
////            result = redisTemplate.opsForSet().members("setthings");
////            log.info("after pop:the size is ..[ "+result.size()+" ]");
////            log.info("after pop:the result is ..[ "+result+" ]");
//            /****************List*****************/
////            ListOperations<String,String> list = redisTemplate.opsForList();
//            if(redisTemplate.opsForList().size("listthingABC")==0){
//                log.info("list:the size is ..[ "+redisTemplate.opsForList().size("listthingABC")+" ]");
//                log.info("the result is ..[ "+redisTemplate.opsForList().range("listthingABC",0, -1)+" ]");
//                for(int i = 0; i < 12; i ++){
//                    redisTemplate.opsForList().leftPush("listthingABC","sky_"+i);
//                }
//
//            }
//            log.info("list:the size is ..[ "+redisTemplate.opsForList().size("listthingABC")+" ]");
//            log.info("the result is ..[ "+redisTemplate.opsForList().range("listthingABC",0, -1)+" ]");
//
//
//        }catch(Exception e){
//            log.error("it is wrong..");
//        }
//	}

    /**
     * 排序算法测试
     *
     * 冒泡排序
     *
     * @author yang
     * @date 2020-9-17
     */
	@Test
    public void maopao(){
	    //6,1,3,4,12,67,2,5,9,7
        StringBuilder result = new StringBuilder("the origin array is: [ ");
        for(int i : array){
            result.append(i).append(",");
        }
        result.append(" ]");
        log.info(result.toString());
        int temp;
	    for (int i = 0; i < array.length; i ++){
//	        temp = array[i];
	        for(int j = 0; j < array.length-i-1; j++){
	            if(array[j] > array[j+1]){
	                temp = array[j+1];
	                array[j+1] = array[j];
	                array[j] = temp;
                }
            }
        }
        StringBuilder builder = new StringBuilder("the origin array is: [ ");
        for(int i : array){
            builder.append(i).append(",");
        }
        builder.append(" ]");
        log.info(builder.toString());
    }

    /**
     * new！！
     * 测试git新分支
     * 选择排序
     * @author yang 2020-9-17
     */
    @Test
    public void xuanze(){
        //6,1,3,4,12,67,2,5,9,7
        StringBuilder result = new StringBuilder("the origin array is: [ ");
        for(int i : array){
            result.append(i).append(",");
        }
        result.append(" ]");
        log.info(result.toString());

        int min;
        int index;
        for (int j = 0 ; j < array.length; j++){
            min = array[j];
            index = j;
            for (int k = j; k < array.length-1; k++){
                if(array[k+1] < min){
                    min = array[k+1];
                    index = k + 1;
                }
            }
            array[index] = array[j];
            array[j] = min;
        }
        StringBuilder builder = new StringBuilder("the xuanze sortes array is: [ ");
        for(int i : array){
            builder.append(i).append(",");
        }
        builder.append(" ]");
        log.info(builder.toString());
    }

    /**
     * 排序算法测试
     *
     * 插入排序
     *
     * @author yang
     * @Date 2020-9-17
     */
    @Test
    public void charu() {
        //6,1,3,4,12,67,2,5,9,7
        StringBuilder result = new StringBuilder("the origin array is: [ ");
        for (int i : array) {
            result.append(i).append(",");
        }
        result.append(" ]");
        log.info(result.toString());

//        if (array.length == 0)
//            return array;
        int current;
        for (int i = 0; i < array.length - 1; i++) {
            current = array[i + 1];
            int preIndex = i;
            while (preIndex >= 0 && current < array[preIndex]) {
                array[preIndex + 1] = array[preIndex];
                preIndex--;
            }
            array[preIndex + 1] = current;
        }
        StringBuilder builder = new StringBuilder("the charu sortes array is: [ ");
        for (int i : array) {
            builder.append(i).append(",");
        }
        builder.append(" ]");
        log.info(builder.toString());
    }

}
