package co.edu.unbosque.Backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.Backend.model.Peliculas;

public interface PeliculaDAO extends JpaRepository<Peliculas, Integer>{ 

}
