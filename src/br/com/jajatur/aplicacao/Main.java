package br.com.jajatur.aplicacao;

import java.util.Scanner;

import br.com.jajatur.dao.AtendenteDAO;
import br.com.jajatur.dao.ClienteDAO;
import br.com.jajatur.dao.ContactaDAO;
import br.com.jajatur.dao.ContrataDAO;
import br.com.jajatur.dao.ProdutoViagemDAO;
import br.com.jajatur.model.Cliente;
import br.com.jajatur.model.Contacta;
import br.com.jajatur.model.Contrata;

public class Main {

	public static void main(String[] args) {
		
		try (Scanner entrada = new Scanner(System.in)) {
			int opcaoSelecionada = 50;
			
			while(opcaoSelecionada!=0) {
				System.out.println("Bem-vindxs a JajaTur");
				System.out.println("1. Cadastrar-se");
				System.out.println("2. Consultar seus dados");
				System.out.println("3. Contratar pacote de viagens");
				System.out.println("4. Contatar atendente");
				System.out.println("5. Verificar status de atendimentos");
				System.out.println("6. Atualizar status de atendimento");
				
				opcaoSelecionada = entrada.nextInt();
				
				switch(opcaoSelecionada) {
					case 1:
						ClienteDAO clienteDAO = new ClienteDAO();
						
						Cliente cliente = new Cliente();
				
						System.out.println("Insira seu nome:");
						cliente.setNomeCliente(entrada.next());
						System.out.println("Insira a sua idade:");
						cliente.setIdadeCliente(entrada.nextInt());
						System.out.println("Insira o seu CPF:");
						cliente.setCpfCliente(entrada.next());
						System.out.println("Insira o seu telefone (Exemplo: DD + 9XXXXXXXX):");
						cliente.setTelefoneCliente(entrada.next());
						System.out.println("Insira o seu Email:");
						cliente.setEmailCliente(entrada.next());
						
						clienteDAO.save(cliente);
					break;
					
					case 2:
						ClienteDAO clienteDAO1 = new ClienteDAO();
						
						String cpfCliente1 = new String();
						
						System.out.println("Digite o seu CPF:");
						cpfCliente1 = entrada.next();
						
						clienteDAO1.getDadosCliente(cpfCliente1);
						
					break;
					
					case 3:
						ProdutoViagemDAO produtosDAO = new ProdutoViagemDAO();
						
						ContrataDAO contratoDAO = new ContrataDAO();
						Contrata contrato = new Contrata();
						
						int opcaoContrata;
						
						System.out.println("Observe os nossos pacotes de viagem:");
						produtosDAO.getProdutoViagem();
						
						System.out.println("Deseja contratar alguns dos nossos pacotes?");
						System.out.println("1. Sim");
						System.out.println("2. Não");
						opcaoContrata = entrada.nextInt();
						
						if(opcaoContrata == 1) {
							System.out.println("Insira seu codCliente:");
							contrato.setCodCliente(entrada.nextInt());
							System.out.println("Insira o codProdutoViagem que deseja contratar:");
							contrato.setCodProdutoViagem(entrada.nextInt());
							contratoDAO.save(contrato);
						}		
					break;
					
					case 4:
						ContactaDAO contactaDAO = new ContactaDAO();
						Contacta atendimento = new Contacta();
						AtendenteDAO atendenteDAO = new AtendenteDAO();
						
						
						System.out.println("Por favor, identifique-se através do seu codCliente:");
						atendimento.setCodCliente(entrada.nextInt());
						
						System.out.println("Os atendentes que temos disponíveis são:");
						atendenteDAO.getAtendente();
						
						System.out.println("Selecione o codAtendente que deseja contactar:");
						atendimento.setCodAtendente(entrada.nextInt());
						
						System.out.println("Informe o assunto do contato:");
						atendimento.setAssuntoAtendimento(entrada.next());
						
						atendimento.setStatusAtendimento("Em aberto");
						
						contactaDAO.save(atendimento);
					break;
					
					case 5:
						ContactaDAO contactaDAO1 = new ContactaDAO();
						
						contactaDAO1.getAtendimentos();
					break;
					
					case 6:
						ContactaDAO contactaDAO2 = new ContactaDAO();
						Contacta atendimento1 = new Contacta();
						
						System.out.println("Informe o codAtendimento do seu atendimento:");
						atendimento1.setCodAtendimento(entrada.nextInt());
						
						System.out.println("Atualize o status do seu atendimento como 'em andamento', 'cancelado' ou 'concluído'");
						atendimento1.setStatusAtendimento(entrada.next());
						
						contactaDAO2.update(atendimento1);
						
					break;
				}
			}
		}
	}

}
