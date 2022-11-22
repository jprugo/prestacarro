package com.gwtsas.prestacarro.components;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties("com.gwtsas.prestacarro.pagination")
public class PageConfiguration {

	private String pageSize;

}
