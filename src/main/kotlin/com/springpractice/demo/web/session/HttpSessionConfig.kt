package com.springpractice.demo.web.session

import org.springframework.context.annotation.Bean
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession

@EnableRedisHttpSession
class HttpSessionConfig {
    @Bean
    fun connectionFactory(): JedisConnectionFactory {
        return JedisConnectionFactory()
    }
}
