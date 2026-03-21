package com.javatechie.stock_trading_client;

import com.javatechie.stock_trading_client.service.StockClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com")
public class StockTradingClientApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(StockTradingClientApplication.class, args);
	}

	StockClientService stockClientService;
	StockTradingClientApplication(StockClientService stockClientService){
		this.stockClientService=stockClientService;
	}
	@Override
	public void run(String... args) throws Exception {
		stockClientService.placeBulkOrders();
	}
}
