package br.com.jajatur.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.jajatur.factory.ConnectionFactory;
import br.com.jajatur.model.Atendente;

public class AtendenteDAO {

public List<Atendente> getAtendente(){
		
		String sql = "SELECT * FROM atendente";
		
		List<Atendente> atendente = new ArrayList<Atendente>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		//Classe que vai recuperar os dados do banco
		ResultSet rset = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				Atendente atendenteDados = new Atendente();
				
				//recuperar o codCliente
				atendenteDados.setCodAtendente(rset.getInt("codAtendente"));
				atendenteDados.setNomeAtendente(rset.getString("nomeAtendente"));
				
				atendente.add(atendenteDados);
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
		
		return atendente;
		
	}
	
}
