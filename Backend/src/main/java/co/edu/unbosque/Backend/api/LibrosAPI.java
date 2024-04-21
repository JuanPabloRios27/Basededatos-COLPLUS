package co.edu.unbosque.Backend.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.Backend.dao.LibrosDAO;
import co.edu.unbosque.Backend.model.Libros;

@RestController
@RequestMapping("Libros")
public class LibrosAPI {
	
	@Autowired
	private LibrosDAO librosDAO;
	
	@PostMapping("/guardar")
	public void guardar(@RequestBody Libros libros) {
		librosDAO.save(libros);
	}
	@GetMapping("/listar")
	public List<Libros> listar(){
		return librosDAO.findAll();
	}
	@PutMapping("/actualizar")
	public void actualizar(@RequestBody Libros libros) {
		librosDAO.save(libros);
	}
	@DeleteMapping("/eliminar{codigo}")
	public void eliminar(@PathVariable("Id_libro") long id){
		librosDAO.deleteById(id);
	}

}
