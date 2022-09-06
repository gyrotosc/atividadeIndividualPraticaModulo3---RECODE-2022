package br.com.jajatur.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.jajatur.factory.ConnectionFactory;
import br.com.jajatur.model.ProdutoViagem;

public class ProdutoViagemDAO {

public List<ProdutoViagem> getProdutoViagem(){
		
		String sql = "SELECT * FROM produtoViagem";
		
		List<ProdutoViagem> produtoViagem = new ArrayList<ProdutoViagem>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		//Classe que vai recuperar os dados do banco
		ResultSet rset = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				ProdutoViagem produtoViagemDados = new ProdutoViagem();
				
				//recuperar o codCliente
				produtoViagemDados.setCodProdutoViagem(rset.getInt("codProdutoViagem"));
				produtoViagemDados.setValorPrevioProdutoViagem(rset.getFloat("valorPrevioProdutoViagem"));
				produtoViagemDados.setDataPartidaProdutoViagem(rset.getDate("dataPartidaProdutoViagem"));
				produtoViagemDados.setLocalPartidaProdutoViagem(rset.getString("localPartidaProdutoViagem"));
				
				produtoViagem.add(produtoViagemDados);
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
		
		return produtoViagem;
		
	}
	
}
