package br.com.javaspring.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.javaspring.cursomc.domain.Cliente;
import br.com.javaspring.cursomc.domain.Pedido;
import br.com.javaspring.cursomc.repositories.PedidoRepository;
import br.com.javaspring.cursomc.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	public Pedido buscar(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: "+ id + ", Tipo: "+ Cliente.class.getName()));
	}

	
}
