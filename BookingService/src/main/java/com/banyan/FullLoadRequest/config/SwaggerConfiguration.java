package com.banyan.FullLoadRequest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	@Bean
	public Docket api() {

		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.banyan.FullLoadRequest.controllers"))
				.paths(PathSelectors.any()).build();
	}

	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		ObjectMapper mapper = new ObjectMapper();
		//Ignore Serialization Exceptions when converting Byte[] to Blob
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(mapper);
		return converter;
	}
}


/*	
 	@Autowired
	ClobExRepository clobRepo;
 // Temporary CLOB Test
@PostMapping("/saveAsClob/{id}")
public void saveClob(@PathVariable Integer id) {
	
	fullLoad = fullLoadService.buildFullLoad(id);
	if (fullLoad == null) {
		System.out.println(id + " does not exist as a rate quote!");
		return;
	}
	ClobEx clob = new ClobEx();
	clob.setFullLoad(fullLoad);
	System.out.println(clob.fullLoad.length());
	clobRepo.save(clob);
}

@GetMapping("/getClob")
public Object getClob() {

	List<ClobEx> clobs = new ArrayList<>();
	ClobEx clob = new ClobEx();
	clobs = (List<ClobEx>) clobRepo.findAll();
	while (clobs.size() != 0) {
		System.out.println(clobs.size());
		clob = clobs.get(clobs.size()-1);
		fullLoad = clob.getFullLoad();
		return fullLoad;
	}
	return null;
}*/


