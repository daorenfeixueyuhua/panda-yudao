# java锁相关

## 基于redis分布式锁

### 参考文档

[七种方案！探讨Redis分布式锁的正确使用姿势](https://mp.weixin.qq.com/s?__biz=Mzg3NzU5NTIwNg==&mid=2247488142&idx=1&sn=79a304efae7a814b6f71bbbc53810c0c&chksm=cf21cda7f85644b11ff80323defb90193bc1780b45c1c6081f00da85d665fd9eb32cc934b5cf&token=162724582&lang=zh_CN&scene=21#wechat_redirect)

### SETNX + EXPIRE

```redis
setnx lock1 100






expire lock1 10
```

```text
非原子操作

```

### SETNX + value值是(系统时间+过期时间)

```redis




# 尝试加锁，如果锁不存在，直接加锁






setnx lock1 1689651737






# 获取锁，如果该锁已经过期






get


lock1






# 重新加锁






getset lock1 1689651826
```

缺点

```text
    过期时间是客户端自己生成的（System.currentTimeMillis()是当前系统的时间），必须要求分布式环境下，每个客户端的时间必须同步。
    如果锁过期的时候，并发多个客户端同时请求过来，都执行jedis.getSet()，最终只能有一个客户端加锁成功，但是该客户端锁的过期时间，可能被别的客户端覆盖
    该锁没有保存持有者的唯一标识，可能被别的客户端释放/解锁。




```

### 使用Lua脚本(包含SETNX + EXPIRE两条指令)

#### LUA 语法

```text
命令格式：EVAL script numkeys key [key …] arg [arg …]
- script参数是一段 Lua5.1 脚本程序。脚本不必(也不应该[^1])定义为一个 Lua 函数
- numkeys指定后续参数有几个key，即：key [key …]中key的个数。如没有key，则为0
- key [key …] 从 EVAL 的第三个参数开始算起，表示在脚本中所用到的那些 Redis 键(key)。在Lua脚本中通过KEYS[1], KEYS[2]获取。
- arg [arg …] 附加参数。在Lua脚本中通过ARGV[1],ARGV[2]获取。

```

#### 分布式锁

```redis
eval "if redis.call('setnx',KEYS[1],ARGV[1]) == 1 then redis.call('expire',KEYS[1],ARGV[2]) return 1 else return 0 end;" 1 lock1 value 60




```

### Redisson

```text

只要线程一加锁成功，就会启动一个watch dog看门狗，它是一个后台线程，会每隔10秒检查一下，如果线程1还持有锁，那么就会不断的延长锁key的生存时间。因此，Redisson就是使用watch dog解决了「锁过期释放，业务没执行完」问题
```

### redis集群分布式锁Redlock+Redisson

```text
搞多个Redis master部署，以保证它们不会同时宕掉。并且这些master节点是完全相互独立的，相互之间不存在数据同步。同时，需要确保在这多个master实例上，是与在Redis单实例，使用相同方法来获取和释放锁。

    1.获取当前时间，以毫秒为单位。
    2.按顺序向5个master节点请求加锁。客户端设置网络连接和响应超时时间，并且超时时间要小于锁的失效时间。（假设锁自动失效时间为10秒，则超时时间一般在5-50毫秒之间,我们就假设超时时间是50ms吧）。如果超时，跳过该master节点，尽快去尝试下一个master节点。
    3.客户端使用当前时间减去开始获取锁时间（即步骤1记录的时间），得到获取锁使用的时间。当且仅当超过一半（N/2+1，这里是5/2+1=3个节点）的Redis master节点都获得锁，并且使用的时间小于锁失效时间时，锁才算获取成功。（如上图，10s> 30ms+40ms+50ms+4m0s+50ms）
    如果取到了锁，key的真正有效时间就变啦，需要减去获取锁所使用的时间。
    如果获取锁失败（没有在至少N/2+1个master实例取到锁，有或者获取锁时间已经超过了有效时间），客户端要在所有的master节点上解锁（即便有些master节点根本就没有加锁成功，也需要解锁，以防止有些漏网之鱼）。
```

## 悲观锁与乐观锁

### 实现

```mysql
# 悲观锁

begin;

select *
from table_name
where id = 1 for
update;

update table_name
set column_value = 'value3'
where id = 1;

commit;

# 乐观锁

select *
from table_name
where id = 1;
begin;
update table_name
set column_value = 'value1'
where id = 1
  and column_value = 'value3';
commit;

```

### 参考文档

https://www.cnblogs.com/qiuhaitang/p/12485395.html

https://zhuanlan.zhihu.com/p/143866444
