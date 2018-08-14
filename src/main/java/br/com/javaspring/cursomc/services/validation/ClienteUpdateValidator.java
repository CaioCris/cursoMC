package br.com.javaspring.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.javaspring.cursomc.domain.Cliente;
import br.com.javaspring.cursomc.dto.ClienteDTO;
import br.com.javaspring.cursomc.repositories.ClienteRepository;
import br.com.javaspring.cursomc.resource.exception.FieldMessage;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ClienteRepository clienteRepo;

	@Override
	public void initialize(ClienteUpdate ann) {
	}

	@Override
	public boolean isValid(ClienteDTO obj, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();

		Cliente aux = clienteRepo.findByEmail(obj.getEmail());
		if (aux != null && aux.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "Email já existente"));
		}

		for (FieldMessage error : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(error.getMessage()).addPropertyNode(error.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

}
