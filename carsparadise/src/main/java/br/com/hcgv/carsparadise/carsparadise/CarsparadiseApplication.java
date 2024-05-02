package br.com.hcgv.carsparadise.carsparadise;

import br.com.hcgv.carsparadise.carsparadise.principal.Main;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CarsparadiseApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CarsparadiseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main principal = new Main();

		principal.exibirMenu();

	}
}
