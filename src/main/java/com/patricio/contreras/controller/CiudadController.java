package com.patricio.contreras.controller;

import static java.util.stream.Collectors.toList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patricio.contreras.entity.Ciudad;
import com.patricio.contreras.entity.Region;
import com.patricio.contreras.service.ICiudadService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200" })
public class CiudadController {
 
	@Autowired
	private ICiudadService ciudadService;
	
	@GetMapping("/ciudades")
	public List<Ciudad> listar() throws Exception{
		return ciudadService.listar();
	}
	
	@GetMapping("/ciudades/page/{page}")
	public Page<Ciudad> index(@PathVariable Integer page) throws Exception{
		Pageable pageable = PageRequest.of(page, 4);
		
		return ciudadService.findAll(pageable);
	}
	
	@GetMapping("/ciudad/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) throws Exception{
		
		Ciudad ciudad = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			//lista por id la informacion de una ciudad
			ciudad= ciudadService.listarPorId(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos!");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(ciudad == null) {
			response.put("mensaje", "La ciudad con ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Ciudad>(ciudad,HttpStatus.OK);
	}
	
	@PostMapping("/ciudad")
	public ResponseEntity<?> create(@Valid @RequestBody Ciudad ciudad,BindingResult result ) throws Exception{
		// es la nueva ciudad creada
		//se inicializa
		Ciudad ciudadNew = null;
		Map<String, Object> response =new HashMap<>();
	if(result.hasErrors()) {
			
			List<String> errors = result.getFieldErrors()
					     .stream()
					     .map(err -> "El campo '"+ err.getField() + "' "+err.getDefaultMessage())
					     .collect(toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
		}
	try {
		  ciudadNew = ciudadService.registrar(ciudad);
		} catch (DataAccessException e) {
		response.put("mensaje", "Error al realizar el insert en la base de datos!");
		response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	 response.put("mensaje", "La ciudad ha sido creada con éxito! ");
	 response.put("ciudad",ciudadNew);
	 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/ciudad/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Ciudad ciudad,BindingResult result,@PathVariable Long id) throws Exception{
		//se obtiene la ciudad que se quiere modificar
		Ciudad ciudadActual = ciudadService.listarPorId(id);
		
		//ciudad ya actualizada
		Ciudad ciudadUpdated = null;
		
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			// se debe obtener los mensajes de error de cada campo 
			// y convertir estos en una lista de errores de tipo string
			
			// se debe convertir esta lista de fielderrors en String
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '"+ err.getField() + "' "+err.getDefaultMessage())// muy parecido  al operador map en angular (rxjs), mismo concepto!
					.collect(toList());// ahora podemos convertir de regreso el stream  aun tipo List
			response.put("errors", errors);
			// se responde con un responseentity con listados de error
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.BAD_REQUEST);
			
			// en lo anterior se recibe un field errors y lo convertimos a string
		}
		if(ciudadActual == null) {
			response.put("mensaje", "Error: no se pudo editar, la ciudad con ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		try {
			//modificamos los datos de la ciudad actual con los datos de la ciudad que te envian
			ciudadActual.setNombreCiudad(ciudad.getNombreCiudad());
			ciudadActual.setRegion(ciudad.getRegion());
			
			ciudadUpdated = ciudadService.registrar(ciudadActual);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar la ciudad en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La ciudad ha sido actualizada con éxito!");
		response.put("ciudad",ciudadUpdated);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		
		
	}
	
	@DeleteMapping("/ciudad/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) throws Exception{
		
		Ciudad ciudad = ciudadService.listarPorId(id);
		Map<String, Object> response = new HashMap<>();
		
		try {
			if(ciudad == null) {
				  response.put("mensaje", "No se encontró la ciudad con el ID: " + id);
		          return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
			ciudadService.eliminar(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "No se pudo eliminar la ciudad!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La ciudad fue eliminada con éxito!");
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}
	
	@GetMapping("ciudades/regiones")
	public List<Region> listarRegiones(){
		return ciudadService.findAllRegiones();
	}
}
