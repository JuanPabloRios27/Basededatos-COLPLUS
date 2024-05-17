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
//Aplicacion que entabla relacion con el HTTP.
@RestController
//Direccion en donde se realiza la configuracion existente de la tabla
@RequestMapping("Empresarios")
public class EmpresariosAPI {
	//Dependencia JPA de todos los metodos para el propio objeto
	@Autowired
	private EmpresariosDAO empresarioDAO;
	//Guarda la información
	@PostMapping("/guardar")
	public void guardar(@RequestBody Empresarios empresarios) {
		empresarioDAO.save(empresarios);
	}
	//Recibe la informacion.
	@GetMapping("/listar")
	public List<Empresarios> listar(){
		return empresarioDAO.findAll();
	}
	//Actualize la informacion
	@PutMapping("/actualizar")
	public void actualizar(@RequestBody Empresarios empresarios) {
		empresarioDAO.save(empresarios);
	}
	//Elimina la informacion
	@DeleteMapping("/eliminar/{codigo}")
	public void eliminar(@PathVariable("codigo") Integer id){
		empresarioDAO.deleteById(id);
	}
}
