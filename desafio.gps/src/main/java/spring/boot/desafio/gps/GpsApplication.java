package spring.boot.desafio.gps;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import spring.boot.desafio.gps.model.PontosInteresse;
import spring.boot.desafio.gps.repository.PontosInteresseRepository;

@SpringBootApplication
public class GpsApplication implements CommandLineRunner{

	@Autowired
	private PontosInteresseRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(GpsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		PontosInteresse ponto = new PontosInteresse("Lanchonete", 27L, 12L);
		PontosInteresse ponto2 = new PontosInteresse("Posto", 31L, 18L);
		PontosInteresse ponto3 = new PontosInteresse("Joalheria", 15L, 12L);
		PontosInteresse ponto4 = new PontosInteresse("Floricultura", 19L, 21L);
		PontosInteresse ponto5 = new PontosInteresse("Pub", 12L, 8L);
		PontosInteresse ponto6 = new PontosInteresse("Supermercado", 23L, 6L);
		PontosInteresse ponto7 = new PontosInteresse("Churrascaria", 28L, 2L);
		
		repository.saveAll(Arrays.asList(ponto, ponto2, ponto3, ponto4, ponto5, ponto6, ponto7));
	}

}
