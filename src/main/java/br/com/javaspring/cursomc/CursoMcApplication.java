package br.com.javaspring.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.javaspring.cursomc.domain.Categoria;
import br.com.javaspring.cursomc.repositories.CategoriaRepository;

@SpringBootApplication
public class CursoMcApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepo;

	public static void main(String[] args) {
		SpringApplication.run(CursoMcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria("Informatica");
		Categoria cat2 = new Categoria("Escritório");
		
		categoriaRepo.saveAll(Arrays.asList(cat1, cat2));
		
	}
}
