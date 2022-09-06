package br.com.jajatur.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.jajatur.factory.ConnectionFactory;
import br.com.jajatur.model.Contacta;


public class ContactaDAO {

	//Iniciando contato
	public void save(Contacta contacto) {
		
		String sql = "INSERT INTO contacta(codCliente, codAtendente, assunto, status) VALUES(?, ?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			//criar uma conexão com banco de dados
			conn = ConnectionFactory.createConnectionToMySQL();
			
			//criamos uma PreaparedStatement para executar uma query
			pstm = (PreparedStatement) conn.prepareCall(sql);
			
			//adicionar os valores que são esperados pela query
			pstm.setInt(1, contacto.getCodCliente());
			pstm.setInt(2, contacto.getCodAtendente());
			pstm.setString(3, contacto.getAssuntoAtendimento());
			pstm.setString(4, contacto.getStatusAtendimento());
			
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
	
	//Visualizar atendimentos
	public List<Contacta> getAtendimentos(){
		
		String sql = "SELECT * FROM contacta";
		
		List<Contacta> atendimento = new ArrayList<Contacta>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		//Classe que vai recuperar os dados do banco
		ResultSet rset = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				Contacta atendimentoDados = new Contacta();
				
				//recuperar o codCliente
				atendimentoDados.setCodAtendente(rset.getInt("codAtendente"));
				atendimentoDados.setCodCliente(rset.getInt("codCliente"));
				atendimentoDados.setCodAtendimento(rset.getInt("codAtendimento"));
				atendimentoDados.setAssuntoAtendimento(rset.getString("assuntoAtendimento"));
				atendimentoDados.setStatusAtendimento(rset.getString("assuntoAtendimento"));
				
				atendimento.add(atendimentoDados);
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
		
		return atendimento;
		
	}
	
	//Atualizar atendimento
	public void update(Contacta atendimento) {
		String sql = "UPDATE contacta SET statusAtendimento = ?" +
				"WHERE codAtendimento = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			//conexão com o banco
			conn = ConnectionFactory.createConnectionToMySQL();
			
			//classe para execução da query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			//adicionar os valores do UPDATE
			pstm.setString(1, atendimento.getStatusAtendimento());
			pstm.setInt(2, atendimento.getCodAtendimento());
			
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
	
}
