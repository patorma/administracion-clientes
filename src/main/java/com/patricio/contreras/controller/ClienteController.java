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
import com.patricio.contreras.entity.Cliente;
import com.patricio.contreras.service.IClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200" })
public class ClienteController {
	
	@Autowired
	private IClienteService clienteService;
	
	@GetMapping("/clientes")
	public List<Cliente> listar() throws Exception{
		return clienteService.listar();
	}
	
	@GetMapping("/clientes/page/{page}")
	public Page<Cliente> index(@PathVariable Integer page) throws Exception{
		Pageable pageable = PageRequest.of(page, 4);
		
		return clienteService.findAll(pageable);
	}
	
	@GetMapping("/cliente/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) throws Exception{
		
		Cliente cliente = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			//lista por id la informacion de un  cliente
			cliente = clienteService.listarPorId(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos!");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(cliente == null) {
			response.put("mensaje", "El cliente con ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Cliente>(cliente,HttpStatus.OK);
	}
	
	@PostMapping("/cliente")
	public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente,BindingResult result ) throws Exception{
		// es el nuevo cliente creado
		//se inicializa
		Cliente clienteNew = null;
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
		  clienteNew = clienteService.registrar(cliente);
		} catch (DataAccessException e) {
		response.put("mensaje", "Error al realizar el insert en la base de datos!");
		response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	 response.put("mensaje", "El cliente ha sido creado con éxito! ");
	 response.put("cliente",clienteNew);
	 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/cliente/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente,BindingResult result,@PathVariable Long id) throws Exception{
		//se obtiene el cliente  que se quiere modificar
		Cliente clienteActual = clienteService.listarPorId(id);
		
		//cliente ya actualizado
		Cliente clienteUpdated = null;
		
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
		if(clienteActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el cliente con ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		try {
			//modificamos los datos del cliente actual con los datos del cliente que te envian
			clienteActual.setNombre(cliente.getNombre());
			clienteActual.setApellido(cliente.getNombre());
			clienteActual.setEmail(cliente.getEmail());
			clienteActual.setFechaRegistro(cliente.getFechaRegistro());
			clienteActual.setTelefono(cliente.getTelefono());
			clienteActual.setCiudad(cliente.getCiudad());
			
			clienteUpdated = clienteService.registrar(clienteActual);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el gasto en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El cliente ha sido actualizado con éxito!");
		response.put("cliente",clienteUpdated);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		
		
	}
	
	@DeleteMapping("/cliente/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) throws Exception{
		
		Cliente cliente = clienteService.listarPorId(id);
		Map<String, Object> response = new HashMap<>();
		
		try {
			if(cliente == null) {
				  response.put("mensaje", "No se encontró el cliente con el ID: " + id);
		          return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
			clienteService.eliminar(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "No se pudo eliminar el cliente!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El cliente fue eliminado con éxito!");
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}
	
	//se listan ciudaes disponibles para asociar una al cliente
	// en el frontend
	@GetMapping("/clientes/ciudades")
	public List<Ciudad> listarCiudades(){
		return clienteService.findAllCiudades();
	}

}
