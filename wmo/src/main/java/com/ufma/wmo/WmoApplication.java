package com.ufma.wmo;

import com.ufma.wmo.application.service.WmoService;
import com.ufma.wmo.domain.rawData.RowState;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

import java.io.*;
import java.util.List;
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

		/*
		Resource resource = context.getResource("classpath:/data/cidades/cidades.csv");
		InputStream is = resource.getInputStream();
		InputStreamReader streamReader = new InputStreamReader(is);
		Reader reader = new BufferedReader(streamReader);

		CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withDelimiter(','));
		List<CSVRecord> records = csvParser.getRecords();

		int valorInicial = records.size();

		if(!records.isEmpty()){
			records.remove(0);
		}

		int cores = Runtime.getRuntime().availableProcessors();
		cores = cores == 0 ? 1 : cores;
		int range = records.size() / cores;


		int cont = 0;

		for(int i = 0; i < cores; i ++){
			int interval = i * range;
			int maxLength = i == cores - 1 ? records.size() : (i + 1) * range;

			int siz = records.subList(interval, maxLength).size();
			cont += siz;
			System.out.println(interval + " : " + maxLength + " - " + siz);
		}
		System.out.println("SIZE: " + cont + ", INICIAL: " + valorInicial);

		 */
		return args -> wmoService.run();

	}


}
