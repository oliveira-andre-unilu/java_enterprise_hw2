package unilu.jfe.hw2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import unilu.jfe.hw2.entities.Tweet;
import unilu.jfe.hw2.entities.User;

@Configuration
public class RedisConfig {
    
    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, Tweet> tweetTemplate(){
        final RedisTemplate<String, Tweet> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setEnableTransactionSupport(true);
        template.setKeySerializer(new StringRedisSerializer());

        return template;
    }

    @Bean
    public RedisTemplate<String, User> userTemplate(){
        final RedisTemplate<String, User> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setEnableTransactionSupport(true);
        template.setKeySerializer(new StringRedisSerializer());

        return template;
    }
}
