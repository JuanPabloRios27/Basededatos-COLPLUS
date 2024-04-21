package co.edu.unbosque.Backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.Backend.model.Libros;

public interface LibrosDAO extends JpaRepository<Libros, Long>{
	
}
