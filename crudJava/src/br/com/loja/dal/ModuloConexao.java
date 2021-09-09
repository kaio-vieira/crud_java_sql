
package br.com.loja.dal;

import java.sql.*;


public class ModuloConexao {
    
    public static Connection conector(){
        java.sql.Connection conexao = null;
        
        String driver = "com.mysql.jdbc.Driver";
        String Url = "jdbc:mysql://localhost:3306/bdlojaetec";
        String user = "root";
        String password = "";
        
        
        try{
          Class.forName(driver);
          conexao = DriverManager.getConnection(Url,user,password);
          return conexao;
        }catch (Exception e){
            return null;
        }
       
        
        
    }
    
    
}
