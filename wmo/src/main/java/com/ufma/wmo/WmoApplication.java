package com.ufma.wmo;

import com.ufma.wmo.application.service.WmoService;
import com.ufma.wmo.domain.rawData.RowState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.util.Map;

@SpringBootApplication
@Slf4j
public class WmoApplication {


	@Autowired
	private Map<String, RowState> stateMap;


	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = new SpringApplicationBuilder(WmoApplication.class)
				.web(WebApplicationType.NONE)
				.run();
		ctx.close();
		log.info("Finish application.");
	}

	@Bean
	ApplicationRunner runner(WmoService wmoService) throws IOException {
		return args -> wmoService.run();
	}


}
