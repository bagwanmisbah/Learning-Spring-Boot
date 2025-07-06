package com.misbah.psdatabase.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class PsdatabaseConfig
{
    @Bean
    public JdbcTemplate jdbcTemplate(final DataSource dataSource)
    {
        return new JdbcTemplate(dataSource);
    }
}
