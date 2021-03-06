package br.com.javaspring.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

@Service
public class DBService {

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

	public void instantiateTestDatabase() throws ParseException {

		Categoria cat1 = new Categoria("Informatica");
		Categoria cat2 = new Categoria("Escritório");
		Categoria cat3 = new Categoria("Cama mesa e banho");
		Categoria cat4 = new Categoria("Eletrônicos");
		Categoria cat5 = new Categoria("Jardinagem");
		Categoria cat6 = new Categoria("Decoração");
		Categoria cat7 = new Categoria("Perfumaria");

		Produto p1 = new Produto("Computador", 2000.00);
		Produto p2 = new Produto("Impressora", 800.00);
		Produto p3 = new Produto("Mouse", 80.00);
		Produto p4 = new Produto("Mesa de escritório", 300.00);
		Produto p5 = new Produto("Toalha", 50.00);
		Produto p6 = new Produto("Colcha", 200.00);
		Produto p7 = new Produto("TV true color", 1200.00);
		Produto p8 = new Produto("Roçadeira", 800.00);
		Produto p9 = new Produto("Abajour", 100.00);
		Produto p10 = new Produto("Pendente", 180.00);
		Produto p11 = new Produto("Shampoo", 90.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));

		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));

		categoriaRepo.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepo.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

		Estado est1 = new Estado("Minas Gerais");
		Estado est2 = new Estado("São Paulo");

		Cidade c1 = new Cidade("Uberlândia", est1);
		Cidade c2 = new Cidade("São Paulo", est2);
		Cidade c3 = new Cidade("Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepo.saveAll(Arrays.asList(est1, est2));
		cidadeRepo.saveAll(Arrays.asList(c1, c2, c3));

		Cliente cli1 = new Cliente("Maria Silva", "maria@gmail.com", "36379866509", TipoCliente.PESSOAFISICA);
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

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepo.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepo.saveAll(Arrays.asList(pag1, pag2));

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		itemPedidoRepo.saveAll(Arrays.asList(ip1, ip2, ip3));
	}

}
