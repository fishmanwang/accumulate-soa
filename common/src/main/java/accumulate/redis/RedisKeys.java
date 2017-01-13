package accumulate.redis;
/**
 * 定义和管理redis缓存中的key
 * @author rongjin.chen
 *
 */
public class RedisKeys {
	
	public static final String REDIS_CHECK_ALIVE_KEY ="redis_check_alive_key";
	public static final String LOCK_KEY_MODULE_RECORD = "insert.lock_{0}_{1}";
}
