package com.vianet.open.demo.start;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.vianet.open.constants.IMIConfiguration;
import com.vianet.open.demo.controllers.IMIController;

@SpringBootApplication
@ComponentScan(basePackages={"com.vianet.open.demo"})
public class StartBoot {
	private static final Logger LOGGER = LoggerFactory.getLogger(IMIController.class);
	/**
	 * 使用fastjson
	 * @return HttpMessageConverters
	 */
	@Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
		SerializerFeature[] serializerFeature = new SerializerFeature[]{
			SerializerFeature.PrettyFormat, SerializerFeature.MapSortField,
			SerializerFeature.WriteNullListAsEmpty,SerializerFeature.WriteNullStringAsEmpty
		};
		List<MediaType> mediaTypes = new ArrayList<MediaType>();
		mediaTypes.add(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE));
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(serializerFeature);
		fastJsonConfig.setDateFormat(JSON.DEFFAULT_DATE_FORMAT);
		fastConverter.setSupportedMediaTypes(mediaTypes);
		fastConverter.setFastJsonConfig(fastJsonConfig);
		HttpMessageConverter<?> converter = fastConverter;
		return new HttpMessageConverters(converter);
    }

	private static void initIMIConfig() {
		
	    String imiConfigPath = "/home/work/apps/conf/vport/imi/imi-config.properties";
        String imiKsPath = "/home/work/apps/conf/vport/imi/imi-ks";

        try {
            IMIConfiguration.initConfigPath(imiConfigPath, imiKsPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
	}

	public static void main(String[] args) {
		SpringApplication.run(StartBoot.class, args);
		
		initIMIConfig();
	}

}