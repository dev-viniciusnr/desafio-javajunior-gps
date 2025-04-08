package spring.boot.desafio.gps.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import spring.boot.desafio.gps.model.PontosInteresse;

public interface PontosInteresseRepository extends JpaRepository<PontosInteresse, Long>{

	@Query(
			"""
				SELECT p FROM PontosInteresse p 
				WHERE(p.x >= :xMin AND p.x <= :xMax AND p.y >= :yMin AND p.y <= :yMax)
			
			"""
			)
	
	List<PontosInteresse> findPontosInteresseProximos(
			@Param("xMin") long xMin,
			@Param("xMax") long xMax,
			@Param("yMin") long yMin,
			@Param("yMax") long yMax
			);
	
}
