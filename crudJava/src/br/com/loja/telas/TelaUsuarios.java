
package br.com.loja.telas;
import java.sql.*;
import br.com.loja.dal.ModuloConexao;
import javax.swing.JOptionPane;

public class TelaUsuarios extends javax.swing.JInternalFrame {
 Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public TelaUsuarios() {
        initComponents();
          conexao = ModuloConexao.conector();
    }

  private void consultar(){
        String sql = "SELECT * FROM usuarios WHERE iduser=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtid.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                txtnome.setText(rs.getString(2));
                txttelefone.setText(rs.getString(3));
                txtlogin.setText(rs.getString(4));
                txtsenha.setText(rs.getString(5));
                comboperfil.setSelectedItem(rs.getString(6));
            } else {
                JOptionPane.showMessageDialog(null, "Usuário não cadastrado!");
                txtnome.setText(null);
                txttelefone.setText(null);
                txtlogin.setText(null);
                txtsenha.setText(null);
                comboperfil.setSelectedItem(null);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

  private void adicionar(){
        String sql = "INSERT INTO usuarios(iduser,usuario,fone,login,senha,perfil) VALUES(?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtid.getText());
            pst.setString(2, txtnome.getText());
            pst.setString(3, txttelefone.getText());
            pst.setString(4, txtlogin.getText());
            String captura_senha = new String(txtsenha.getText());
            pst.setString(5, captura_senha);
            pst.setString(6, comboperfil.getSelectedItem().toString());
            if (txtid.getText().isEmpty() || txtnome.getText().isEmpty() || txttelefone.getText().isEmpty() || 
                    txtlogin.getText().isEmpty() || txtsenha.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null,"Preencha todos os campos obrigatórios!");
                
            } else {
                int adicionado = pst.executeUpdate();
            if (adicionado>0){
                JOptionPane.showMessageDialog(null,"Usuário adicionado com sucesso!");
                txtid.setText(null);
                txtnome.setText(null);
                txttelefone.setText(null);
                txtlogin.setText(null);
                txtsenha.setText(null);
                
            }
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void alterar(){
        String sql = "UPDATE usuarios set usuario=?, fone=?, login=?, senha=?, "
                + "perfil=? WHERE iduser=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtnome.getText());
            pst.setString(2, txttelefone.getText());
            pst.setString(3, txtlogin.getText());
            String captura_senha = new String(txtsenha.getText());
            pst.setString(4, captura_senha);
            pst.setString(5, comboperfil.getSelectedItem().toString());
            pst.setString(6, txtid.getText());
            if (txtid.getText().isEmpty() || txtnome.getText().isEmpty() || txttelefone.getText().isEmpty() || 
                    txtlogin.getText().isEmpty() || txtsenha.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null,"Preencha todos os campos obrigatórios!");
            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado>0){
                    JOptionPane.showMessageDialog(null,"Dados do usuário alterado com sucesso!");
                    txtid.setText(null);
                    txtnome.setText(null);
                    txttelefone.setText(null);
                    txtlogin.setText(null);
                    txtsenha.setText(null);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void remover(){
        //antes de remover fazer uma confirmação de remoção
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja remover este usuário?",
                "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION){
            String sql = "DELETE FROM usuarios WHERE iduser=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtid.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0){
                    JOptionPane.showMessageDialog(null, "Usuário removido com sucesso!");
                    txtid.setText(null);
                    txtnome.setText(null);
                    txttelefone.setText(null);
                    txtlogin.setText(null);
                    txtsenha.setText(null);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
 }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBox1 = new javax.swing.JCheckBox();
        txtid = new javax.swing.JTextField();
        txtnome = new javax.swing.JTextField();
        txttelefone = new javax.swing.JTextField();
        txtsenha = new javax.swing.JTextField();
        txtlogin = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        comboperfil = new javax.swing.JComboBox<>();
        btnadicionar = new javax.swing.JButton();
        btnselecionar = new javax.swing.JButton();
        btnalterar = new javax.swing.JButton();
        btnapagar = new javax.swing.JButton();

        jCheckBox1.setText("jCheckBox1");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Cadasttro de Usuarios");

        txtid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidActionPerformed(evt);
            }
        });

        jLabel1.setText("ID");

        jLabel2.setText("NOME");

        jLabel3.setText("TELEFONE");

        jLabel4.setText("SENHA");

        jLabel5.setText("LOGIN");

        jLabel6.setText("PERFIL");

        comboperfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin", "user" }));
        comboperfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboperfilActionPerformed(evt);
            }
        });

        btnadicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/loja/icones/create.png"))); // NOI18N
        btnadicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnadicionarActionPerformed(evt);
            }
        });

        btnselecionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/loja/icones/pesquisar.png"))); // NOI18N
        btnselecionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnselecionarActionPerformed(evt);
            }
        });

        btnalterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/loja/icones/update.png"))); // NOI18N
        btnalterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnalterarActionPerformed(evt);
            }
        });

        btnapagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/loja/icones/delete.png"))); // NOI18N
        btnapagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnapagarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtnome, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(btnadicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnselecionar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txttelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtsenha, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addGap(18, 18, 18))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(btnalterar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(comboperfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtlogin, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnapagar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 52, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtlogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(comboperfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnselecionar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnapagar)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnadicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnalterar)))
                .addContainerGap(71, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidActionPerformed

    private void comboperfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboperfilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboperfilActionPerformed

    private void btnadicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnadicionarActionPerformed
       adicionar();
    }//GEN-LAST:event_btnadicionarActionPerformed

    private void btnselecionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnselecionarActionPerformed
         consultar();
    }//GEN-LAST:event_btnselecionarActionPerformed

    private void btnalterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnalterarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnalterarActionPerformed

    private void btnapagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnapagarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnapagarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnadicionar;
    private javax.swing.JButton btnalterar;
    private javax.swing.JButton btnapagar;
    private javax.swing.JButton btnselecionar;
    private javax.swing.JComboBox<String> comboperfil;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtlogin;
    private javax.swing.JTextField txtnome;
    private javax.swing.JTextField txtsenha;
    private javax.swing.JTextField txttelefone;
    // End of variables declaration//GEN-END:variables
}
