package br.com.jajatur.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.jajatur.factory.ConnectionFactory;
import br.com.jajatur.model.Contrata;

public class ContrataDAO {

	//Contratar pacote
	public void save(Contrata contrato) {
		
		String sql = "INSERT INTO contrato(codCliente, codProdutoViagem, codContrato, dataAquisicaoProdutoViagem) VALUES(?, ?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			//criar uma conexão com banco de dados
			conn = ConnectionFactory.createConnectionToMySQL();
			
			//criamos uma PreaparedStatement para executar uma query
			pstm = (PreparedStatement) conn.prepareCall(sql);
			
			//adicionar os valores que são esperados pela query
			pstm.setInt(1, contrato.getCodCliente());
			pstm.setInt(2, contrato.getCodProdutoViagem());
			pstm.setInt(3, contrato.getCodContrato());
			pstm.setDate(4, (java.sql.Date) new Date(contrato.getDataAquisicaoProdutoViagem().getTime()));
			
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
	
	//Buscar contratos
	public List<Contrata> getContratos(){
		
		String sql = "SELECT * FROM contrata";
		
		List<Contrata> contrato = new ArrayList<Contrata>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		//Classe que vai recuperar os dados do banco
		ResultSet rset = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				Contrata contratoDados = new Contrata();
				
				//recuperar os dados
				contratoDados.setCodCliente(rset.getInt("codCliente"));
				contratoDados.setCodProdutoViagem(rset.getInt("codProdutoViagem"));
				contratoDados.setCodContrato(rset.getInt("codContrato"));
				contratoDados.setDataAquisicaoProdutoViagem(rset.getDate("dataAquisicaoProdutoViagem"));
				
				contrato.add(contratoDados);
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
		
		return contrato;
		
	}

	public void deleteContratoByCodCliente(int codCliente) {
		String sql = "DELETE FROM contrata WHERE codCliente = ?";
		
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
