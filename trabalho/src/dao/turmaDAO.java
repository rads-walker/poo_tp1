/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Turma;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Danilo
 */
public class turmaDAO {
    
    public void create(Turma t){
        
        Connection con = conecao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO turma (idTurma, nome) VALUES (?,?)");
            stmt.setInt(1, t.getId());
            stmt.setString(2, t.getNome());
            System.out.println(stmt);
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com successo!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar!");
        }finally{
            conecao.closeConnection(con, stmt);
        }
    }
    
    public List<Turma> read(){
        Connection con = conecao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs =  null;
        
        List turmas = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("Select * from turma");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Turma turma = new Turma();
                turma.setId(rs.getInt("idTurma"));
                turma.setNome(rs.getString("nome"));
                turmas.add(turma);
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao recuperar do banco!");
        }finally{
            conecao.closeConnection(con, stmt, rs);
        }
        return turmas;
    }
}
