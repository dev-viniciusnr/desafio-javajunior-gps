package spring.boot.desafio.gps.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import spring.boot.desafio.gps.model.PontosInteresse;
import spring.boot.desafio.gps.model.PontosInteresseDTO;
import spring.boot.desafio.gps.repository.PontosInteresseRepository;

@RestController
public class PontosInteresseController {

	private final PontosInteresseRepository repository;
	
	public PontosInteresseController(PontosInteresseRepository repository) {
		this.repository = repository;
	}
	
	
	@PostMapping("/pontos-de-interesse")
	public ResponseEntity<Void> pontosInteresseCriar(@RequestBody PontosInteresseDTO body) {
		repository.save(new PontosInteresse(body.nome(), body.x(), body.y()));
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/listar/pontos-de-interesse")
	public ResponseEntity<List<PontosInteresse>> pontosInteresseListar() {
		List<PontosInteresse> listaInteresse = repository.findAll();
		return ResponseEntity.ok(listaInteresse);
	}
	
	@GetMapping("/listar/pontos-proximos")
	public ResponseEntity<List<PontosInteresse>> listarPontosProximos(@RequestParam("x") Long x, @RequestParam("y") Long y, @RequestParam("dmax") Long dmax) {
		
		long xMin = x - dmax;
		long xMax = x + dmax;
		long yMin = y - dmax;
		long yMax = x + dmax;
		
		List<PontosInteresse> pontosFiltrados = repository.findPontosInteresseProximos(xMin, xMax, yMin, yMax)
				.stream().filter(p -> distancia(x, y, p.getX(), p.getY()) <= dmax).toList();
		
		
		return ResponseEntity.ok(pontosFiltrados);
	}
	
	public double distancia(long x1, long y1, long x2, long y2) {
		return Math.hypot(x2 - x1, y2 - y1);
	}
	
	
}
