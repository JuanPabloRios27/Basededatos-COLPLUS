package co.edu.unbosque.Backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.Backend.model.Empresarios;
//Importa todos los repositorios JPA, con el objetivo de tomar las configuraciones existentes hacia la base de datos.
public interface EmpresariosDAO extends JpaRepository<Empresarios, Integer>{
}
