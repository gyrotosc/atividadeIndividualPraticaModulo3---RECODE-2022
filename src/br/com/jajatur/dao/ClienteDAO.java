package br.com.jajatur.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.jajatur.factory.ConnectionFactory;
import br.com.jajatur.model.Cliente;

public class ClienteDAO {
	
	//create
	public void save(Cliente cliente) {
		
		String sql = "INSERT INTO cliente(nomeCliente, idadeCliente, cpfCliente, telefoneCliente, emailCliente) VALUES(?, ?, ?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			//criar uma conexão com banco de dados
			conn = ConnectionFactory.createConnectionToMySQL();
			
			//criamos uma PreaparedStatement para executar uma query
			pstm = (PreparedStatement) conn.prepareCall(sql);
			
			//adicionar os valores que são esperados pela query
			pstm.setString(1, cliente.getNomeCliente());
			pstm.setInt(2, cliente.getIdadeCliente());
			pstm.setString(3, cliente.getCpfCliente());
			pstm.setInt(4, cliente.getTelefoneCliente());
			pstm.setString(5, cliente.getEmailCliente());
			
			//executar a query
			pstm.execute();
			
			System.out.println("Registro realizado com sucesso.");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// fechar as conexões
			try{
				if(pstm!=null){
					pstm.close();
				}
				if(conn!=null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	//read
	public List<Cliente> getDadosCliente(String cpfCliente){
		
		String sql = "SELECT * FROM cliente WHERE cpfCliente = ?";
		
		List<Cliente> cliente = new ArrayList<Cliente>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		//Classe que vai recuperar os dados do banco
		ResultSet rset = null;
		
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			pstm.setString(1, cpfCliente);
			
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				Cliente clienteDados = new Cliente();
				
				//recuperar o codCliente
				clienteDados.setCodCliente(rset.getInt("codCliente"));
				clienteDados.setNomeCliente(rset.getString("nomeCliente"));
				clienteDados.setIdadeCliente(rset.getInt("idadeCliente"));
				clienteDados.setCpfCliente(rset.getString("cpfCliente"));
				clienteDados.setTelefoneCliente(rset.getInt("telefoneCliente"));
				clienteDados.setEmailCliente(rset.getString("emailCliente"));
				
				cliente.add(clienteDados);
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			try {
				if(rset!=null) {
					rset.close();
				}
				if(pstm!=null) {
					pstm.close();
				}
				if(conn!=null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return cliente;
		
	}

	//update
	public void update(Cliente cliente) {
		String sql = "UPDATE cliente SET nomeCliente = ?, idadeCliente = ?, cpfCliente = ?, telefoneCliente = ?, emailCliente = ?" +
				"WHERE codCliente = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			//conexão com o banco
			conn = ConnectionFactory.createConnectionToMySQL();
			
			//classe para execução da query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			//adicionar os valores do UPDATE
			pstm.setString(1, cliente.getNomeCliente());
			pstm.setInt(2, cliente.getIdadeCliente());
			pstm.setString(3, cliente.getCpfCliente());
			pstm.setInt(4, cliente.getTelefoneCliente());
			pstm.setString(5, cliente.getEmailCliente());
			pstm.setInt(6, cliente.getCodCliente());
			
			pstm.execute();
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			try {
				if(conn!=null) {
					conn.close();
				}
				if(pstm!=null) {
					pstm.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//delete
	public void deleteByCodCliente(int codCliente) {
		String sql = "DELETE FROM cliente WHERE codCliente = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			pstm.setInt(1, codCliente);
			
			pstm.execute();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstm!=null) {
					pstm.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
