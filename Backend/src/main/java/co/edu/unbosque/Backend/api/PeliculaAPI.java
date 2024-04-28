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
import co.edu.unbosque.Backend.dao.PeliculaDAO;
import co.edu.unbosque.Backend.model.Peliculas;

@RestController
@RequestMapping("Pelicula")
public class PeliculaAPI {
	
	@Autowired
	private PeliculaDAO peliculaDAO;
	
	@PostMapping("/guardar")
	public void guardar(@RequestBody Peliculas pelicula) {
		peliculaDAO.save(pelicula);
	}
	@GetMapping("/listar")
	public List<Peliculas> listar(){
		return peliculaDAO.findAll();
	}
	@DeleteMapping("/eliminar{codigo}")
	public void eliminar(@PathVariable("Codigo") Integer id){
		peliculaDAO.deleteById(id);
	}
	@PutMapping("/actualizar")
	public void actualizar(@RequestBody Peliculas pelicula) {
		peliculaDAO.save(pelicula);
	}

}
