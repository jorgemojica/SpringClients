package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Cliente;
import com.example.demo.service.ClienteService;

@RestController
@RequestMapping("api/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@GetMapping
	public List<Cliente> listarClientes() {
		return clienteService.listarClientes();
	}

	@RequestMapping(value = "{id}")
	public ResponseEntity<Cliente> buscarClientePorId(@PathVariable("id") Long id) {
		Optional<Cliente> clienteOptional = clienteService.buscarClientePorId(id);
		if (clienteOptional.isPresent()) {
			return ResponseEntity.ok(clienteOptional.get());
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@PostMapping
	public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
		Cliente clienteNuevo = clienteService.crearCliente(cliente);
		return ResponseEntity.ok(clienteNuevo);
	}

	@DeleteMapping(value = "{id}")
	public ResponseEntity<String> EliminarCliente(@PathVariable("id") Long id) {
		clienteService.eliminarCliente(id);
		return ResponseEntity.ok("Cliente eliminado correctamente");
	}
	
	@PutMapping
	public ResponseEntity<Cliente> actualizarCliente(@RequestBody Cliente cliente){
		Cliente clienteActualizar = clienteService.actualizarCliente(cliente);
		return ResponseEntity.ok(clienteActualizar);
	}

}
