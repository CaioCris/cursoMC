package br.com.javaspring.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.javaspring.cursomc.domain.Categoria;
import br.com.javaspring.cursomc.domain.Cidade;
import br.com.javaspring.cursomc.domain.Cliente;
import br.com.javaspring.cursomc.domain.Endereco;
import br.com.javaspring.cursomc.domain.Estado;
import br.com.javaspring.cursomc.domain.ItemPedido;
import br.com.javaspring.cursomc.domain.Pagamento;
import br.com.javaspring.cursomc.domain.PagamentoComBoleto;
import br.com.javaspring.cursomc.domain.PagamentoComCartao;
import br.com.javaspring.cursomc.domain.Pedido;
import br.com.javaspring.cursomc.domain.Produto;
import br.com.javaspring.cursomc.domain.enums.EstadoPagamento;
import br.com.javaspring.cursomc.domain.enums.TipoCliente;
import br.com.javaspring.cursomc.repositories.CategoriaRepository;
import br.com.javaspring.cursomc.repositories.CidadeRepository;
import br.com.javaspring.cursomc.repositories.ClienteRepository;
import br.com.javaspring.cursomc.repositories.EnderecoRepository;
import br.com.javaspring.cursomc.repositories.EstadoRepository;
import br.com.javaspring.cursomc.repositories.ItemPedidoRepository;
import br.com.javaspring.cursomc.repositories.PagamentoRepository;
import br.com.javaspring.cursomc.repositories.PedidoRepository;
import br.com.javaspring.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursoMcApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepo;
	@Autowired
	private ProdutoRepository produtoRepo;
	@Autowired
	private EstadoRepository estadoRepo;
	@Autowired
	private CidadeRepository cidadeRepo;
	@Autowired
	private ClienteRepository clienteRepo;
	@Autowired
	private EnderecoRepository enderecoRepo;
	@Autowired
	private PedidoRepository pedidoRepo;
	@Autowired
	private PagamentoRepository pagamentoRepo;
	@Autowired
	private ItemPedidoRepository itemPedidoRepo;

	public static void main(String[] args) {
		SpringApplication.run(CursoMcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria("Informatica");
		Categoria cat2 = new Categoria("Escritório");
		
		Produto p1 = new Produto("Computador", 2000.00);
		Produto p2 = new Produto("Impressora", 800.00);
		Produto p3 = new Produto("Mouse", 88.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepo.saveAll(Arrays.asList(cat1, cat2));
		produtoRepo.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado("Minas Gerais");
		Estado est2 = new Estado("São Paulo");
		
		Cidade c1 = new Cidade("Uberlândia", est1);
		Cidade c2 = new Cidade("São Paulo", est2);
		Cidade c3 = new Cidade("Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepo.saveAll(Arrays.asList(est1, est2));
		cidadeRepo.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente("Maria Silva", "maria@gmail.com", "36379866509",TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("20939350", "944556632"));
		
		Endereco end1 = new Endereco("Rua Flores", "300", "Apto 303", "Jardim", "38220098", cli1, c1);
		Endereco end2 = new Endereco("Avenida Matos", "20", "Sala 3", "Centro", "38227798", cli1, c2);
		
		clienteRepo.saveAll(Arrays.asList(cli1));
		enderecoRepo.saveAll(Arrays.asList(end1, end2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(sdf.parse("30/09/2017 10:32"), cli1, end1);
		Pedido ped2 = new Pedido(sdf.parse("10/10/2017 19:35"), cli1, end2);
		
		Pagamento pag1 = new PagamentoComCartao(EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pag1);
		
		Pagamento pag2 = new PagamentoComBoleto(EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pag2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepo.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepo.saveAll(Arrays.asList(pag1,pag2));
		
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepo.saveAll(Arrays.asList(ip1,ip2,ip3));
	}
}
