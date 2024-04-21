package co.edu.unbosque.Backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.Backend.model.Empresarios;

public interface EmpresariosDAO extends JpaRepository<Empresarios, Integer>{
}
