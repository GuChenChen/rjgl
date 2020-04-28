package com.fykj.scaffold.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author feihj.
 * @date: 2019/11/22 9:24
 */
@Component
@Configuration()
@PropertySource(value="classpath:application.yml")
@ConfigurationProperties(prefix = "referer")
public class RefererConfig {

	private List<String> refererList;

	public List<String> getRefererList() {
		return refererList;
	}

	public void setRefererList(List<String> refererList) {
		this.refererList = refererList;
	}
}
