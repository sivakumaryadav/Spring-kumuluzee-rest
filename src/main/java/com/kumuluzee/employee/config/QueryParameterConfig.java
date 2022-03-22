package com.kumuluzee.employee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kumuluz.ee.rest.utils.QueryStringDefaults;
import static com.kumuluzee.employee.constant.AppConstants.DEFAULT_LIMIT;
import static com.kumuluzee.employee.constant.AppConstants.DEFAULT_OFFSET;
import static com.kumuluzee.employee.constant.AppConstants.MAX_LIMIT;;

@Configuration
public class QueryParameterConfig {

	@Bean
	public QueryStringDefaults getQueryStringDefaults() {
		return new QueryStringDefaults()
				.maxLimit(MAX_LIMIT)
				.defaultLimit(DEFAULT_LIMIT)
				.defaultOffset(DEFAULT_OFFSET);
	}
}
