
package br.com.loja.telas;
import br.com.loja.dal.ModuloConexao;
import java.sql.*;
import javax.swing.JOptionPane;
public class TelaLogin extends javax.swing.JFrame {
Connection conexao = null;
PreparedStatement pst = null;
ResultSet rs = null;

public void logar(){
    String sql = "select* FROM usuarios WHERE login=? AND senha=?";
    
    try {
        
        pst = conexao.prepareStatement(sql);
        pst.setString(1, txtEmail.getText());
        //pst.setString(2, txtSenha.getText());
        String Captura_Senha = new String(txtSenha.getPassword());
        pst.setString(2, Captura_Senha);
        
        rs = pst.executeQuery();
        
        if( rs.next() ){
            //ir para tela principal
            //JOptionPane.showMessageDialog(null, "abrindo tela principal");
            
            String perfil = rs.getString(6);
            
            if(perfil.equals("admin")){
                TelaPrincipal Principal = new TelaPrincipal();
                Principal.setVisible(true);
                TelaPrincipal.menuCadastroUsuario.setEnabled(true);
                TelaPrincipal.menuRelatorio.setEnabled(true);
                TelaPrincipal.lblusuario.setText(rs.getString(2));
                this.dispose();
                 conexao.close();  
            }
            else{
                 TelaPrincipal Principal = new TelaPrincipal();
                Principal.setVisible(true);
                 this.dispose();
                 conexao.close();
            }
            
            
          
                    
        }else{
            JOptionPane.showMessageDialog(null, "usuarios/senha invalidos!! ");
        }
        
        
    }catch (Exception e) {
    JOptionPane.showMessageDialog(null, e);
}
}


    public TelaLogin() {
        initComponents();
        
        conexao = ModuloConexao.conector();
       // System.out.println("resutado: " + conexao);
       
       if (conexao != null ){
           //conectado
           
           //lblStatus.setText("conectado!!");
           lblStatus1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/loja/icones/dbok.png")));
           
       }else{
           //nao conectado
          // lblStatus.setText( "não conectado!!");
          lblStatus1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/loja/icones/dberror.png")));
          
       }
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtSenha = new javax.swing.JPasswordField();
        btnLogar = new javax.swing.JButton();
        lblStatus1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tela de Login");
        setResizable(false);

        jLabel1.setText("Email");

        jLabel2.setText("Senha");

        btnLogar.setText("Logar");
        btnLogar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogarActionPerformed(evt);
            }
        });

        lblStatus1.setText(":)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                            .addComponent(txtSenha)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(lblStatus1)
                        .addGap(73, 73, 73)
                        .addComponent(btnLogar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLogar)
                    .addComponent(lblStatus1))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogarActionPerformed
       logar();
    }//GEN-LAST:event_btnLogarActionPerformed

    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblStatus1;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JPasswordField txtSenha;
    // End of variables declaration//GEN-END:variables
}
