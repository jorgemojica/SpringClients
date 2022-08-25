package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Cliente;
import com.example.demo.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	public List<Cliente> listarClientes() {
		return clienteRepository.findAll();
	}

	public Optional<Cliente> buscarClientePorId(Long id) {
		return clienteRepository.findById(id);
	}

	public Cliente crearCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public void eliminarCliente(Long id) {
		clienteRepository.deleteById(id);
	}

	public Cliente actualizarCliente(Long id, Cliente cliente) {
		Optional<Cliente> clienteOptional = clienteRepository.findById(id);
		if (clienteOptional.isPresent()) {
			Cliente clienteActualizar = clienteOptional.get();
			clienteActualizar.setNombre(cliente.getNombre());
			clienteActualizar.setTelefono(cliente.getTelefono());
			clienteActualizar.setEdad(cliente.getEdad());
			clienteActualizar.setEstado(cliente.isEstado());
			clienteRepository.save(clienteActualizar);
			return clienteActualizar;
		} else {
			return null;
		}
	}

}
