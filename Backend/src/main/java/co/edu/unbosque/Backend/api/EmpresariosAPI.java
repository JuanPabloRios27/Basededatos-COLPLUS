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
import co.edu.unbosque.Backend.dao.EmpresariosDAO;
import co.edu.unbosque.Backend.model.Empresarios;
@RestController
@RequestMapping("Empresarios")
public class EmpresariosAPI {
	
	@Autowired
	private EmpresariosDAO empresarioDAO;
	
	@PostMapping("/guardar")
	public void guardar(@RequestBody Empresarios empresarios) {
		empresarioDAO.save(empresarios);
	}
	@GetMapping("/listar")
	public List<Empresarios> listar(){
		return empresarioDAO.findAll();
	}
	@PutMapping("/actualizar")
	public void actualizar(@RequestBody Empresarios empresarios) {
		empresarioDAO.save(empresarios);
	}
	@DeleteMapping("/eliminar/{codigo}")
	public void eliminar(@PathVariable("codigo") Integer id){
		empresarioDAO.deleteById(id);
	}
}
