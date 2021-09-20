package interfaces;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;

@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
@EnableDiscoveryClient
//@EnableFeignClients
//@ComponentScan(basePackages={"org.mybatis.jpetstore.mapper","org.mybatis.jpetstore.service" })
public class OrderApp {
    public static void main(String[] args) {
        SpringApplication.run(OrderApp.class, args);
    }
    
    @Bean
    public ProxyItemMapper getProxyItemMapper() {
    	return Feign.builder().encoder(new GsonEncoder()).decoder(new GsonDecoder()).target(ProxyItemMapper.class, "http://CatalogMS:8084");
    }
}
