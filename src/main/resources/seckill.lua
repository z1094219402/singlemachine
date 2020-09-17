local product_id = KEYS[1]
local user_id = ARGV[1]
-- 商品库存key
local product_stock_key = 'seckill:' .. product_id .. ':store'
-- 商品秒杀结束标识的key
local end_product_key = 'seckill:' .. product_id .. ':end'

-- 存储秒杀成功的用户id的集合的key
local bought_users_key = 'seckill:' .. product_id .. ':uids'

--判断该商品是否秒杀结束
local is_end = redis.call('get',product_stock_key)

if  is_end and tonumber(is_end) ~=1 then
    return -1
end
-- 判断用户是否秒杀过
local is_in = redis.call('sismember',bought_users_key,user_id)

if is_in > 0 then
    return 0
end

-- 获取商品当前库存
local stock = redis.call('get',product_stock_key)

-- 如果库存<=0,则返回-1
if not stock or tonumber(stock) <=0 then
    redis.call("set",end_product_key,"1")
    return -1
end

-- 减库存,并且把用户的id添加进已购买用户set里
redis.call("decr",product_stock_key)
redis.call("sadd",bought_users_key,user_id)

return 1