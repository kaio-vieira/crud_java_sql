package br.com.loja.dal;

import java.sql.*;

public class ModuloConexao {
    //Método para fazer a conexão
    public static Connection conector(){  
        java.sql.Connection conexao = null;
        // Chamar o driver
        String driver = "com.mysql.cj.jdbc.Driver";
        // Informações do banco
        String url = "jdbc:mysql://localhost:3306/bdlojaetec";
        String user= "root";
        String password ="";
        
        //Estabelecer a conexão
        
        try{
           Class.forName(driver);
           conexao = DriverManager.getConnection(url,user,password);
           return conexao;
        }catch(Exception e){
            return null;
            
        }
        
        
    }
    
}
