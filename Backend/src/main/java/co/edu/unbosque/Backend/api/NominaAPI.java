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
import co.edu.unbosque.Backend.dao.NominaDAO;
import co.edu.unbosque.Backend.model.Nomina;

@RestController
@RequestMapping("Nomina")
public class NominaAPI {
	@Autowired
	private NominaDAO nominaDAO;
	
	@PostMapping("/guardar")
	public void guardar(@RequestBody Nomina nomina) {
		nominaDAO.save(nomina);
	}
	@GetMapping("/listar")
	public List<Nomina> listar(){
		return nominaDAO.findAll();
	}
	@PutMapping("/actualizar")
	public void actualizar(@RequestBody Nomina nomina) {
		nominaDAO.save(nomina);
	}
	@DeleteMapping("/eliminar/{codigo}")
	public void eliminar(@PathVariable("codigo") int id){
		nominaDAO.deleteById(id);
	}
}
