/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost.model.dao;

import com.fatec.garagemlocalhost.database.Database;
import com.fatec.garagemlocalhost.model.entities.Usuario;
import com.fatec.garagemlocalhost.model.enums.TipoUsuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Christian
 */
public class UsuarioDAO {
        private final Database database;
     
    public UsuarioDAO(Database database){
        this.database = database;
    }
    
    public List<Usuario> findAll() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();

        String sql = "SELECT * FROM usuarios";
        PreparedStatement st = database.getConnnection().prepareStatement(sql);
        ResultSet rs = st.executeQuery();

        while(rs.next()){
            Usuario usuario = new Usuario();
            usuario.setId(rs.getInt("id_usuario"));
            usuario.setNome(rs.getString("nome"));
            usuario.setEmail(rs.getString("email"));
            usuario.setSenha(rs.getString("senha"));
            usuario.setTipoUsuario(TipoUsuario.setInteiro(rs.getInt("tipo_usuario")));
        }
        rs.close();
        st.close();
        
        return usuarios;
    }
    
    public Optional<Usuario> findById(Integer id) throws SQLException {

        String sql = "SELECT * FROM usuarios WHERE id_usuario = ?";
        PreparedStatement st = database.getConnnection().prepareStatement(sql);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();

        Usuario usuario = null;
        if(rs.next()){
            usuario = new Usuario();
            usuario.setId(rs.getInt("id_usuario"));
            usuario.setNome(rs.getString("nome"));
            usuario.setEmail(rs.getString("email"));
            usuario.setSenha(rs.getString("senha"));
            usuario.setTipoUsuario(TipoUsuario.setInteiro(rs.getInt("tipo_usuario")));
        }
        st.close();
        rs.close();
        return Optional.ofNullable(usuario);
    }
    
    public List<Usuario> findAllByNome(String nome) throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();

        String sql = "SELECT * FROM usuarios WHERE nome LIKE '%?%'";
        PreparedStatement st = database.getConnnection().prepareStatement(sql);
        st.setString(1, nome);
        ResultSet rs = st.executeQuery();

        while(rs.next()){
            Usuario usuario = new Usuario();
            usuario.setId(rs.getInt("id_usuario"));
            usuario.setNome(rs.getString("nome"));
            usuario.setEmail(rs.getString("email"));
            usuario.setSenha(rs.getString("senha"));
            usuario.setTipoUsuario(TipoUsuario.setInteiro(rs.getInt("tipo_usuario")));
            usuarios.add(usuario);
        }
        st.close();
        rs.close();
       
        return usuarios;
    }
    
    public Optional<Usuario> findByNomeOrEmail(String texto)throws SQLException{
        String sql = "SELECT * FROM usuarios WHERE ";
        if(texto.trim().contains("@") && texto.trim().contains(".com")){
            sql+= "email = ?;";
        }else{
            sql += "nome = ?;";
        }
    
        PreparedStatement st = database.getConnnection().prepareStatement(sql);
        st.setString(1, texto);
        ResultSet rs = st.executeQuery();

        Usuario usuario = null;
        if(rs.next()){
            usuario = new Usuario();
            usuario.setId(rs.getInt("id_usuario"));
            usuario.setNome(rs.getString("nome"));
            usuario.setEmail(rs.getString("email"));
            usuario.setSenha(rs.getString("senha"));
            usuario.setTipoUsuario(TipoUsuario.setInteiro(rs.getInt("tipo_usuario")));
        }
        st.close();
        rs.close();

        return Optional.ofNullable(usuario);
        
    }
    
    public void create(Usuario usuario) throws SQLException {

        String sql = "INSERT INTO usuarios (nome, email, senha, tipo_usuario) VALUES (?,?,?,?)";
        PreparedStatement st = database.getConnnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        st.setString(1, usuario.getNome());
        st.setString(2, usuario.getEmail());
        st.setString(3, usuario.getSenha());
        st.setInt(4, usuario.getTipoUsuario().getNumero());

        int linhas = st.executeUpdate();

        if(linhas > 0){
            ResultSet rs = st.getGeneratedKeys();
            if(rs.next()){
                usuario.setId(rs.getInt(1));
            }
            rs.close();
        }
        st.close();
    }
    
    public void update(Usuario usuario) throws SQLException {

        String sql = "UPDATE usuarios SET id_usuario = ?, nome = ?, email = ?, senha = ?, tipo_usuario = ?";
        PreparedStatement st = database.getConnnection().prepareStatement(sql);

        st.setInt(1, usuario.getId());
        st.setString(2, usuario.getNome());
        st.setString(3, usuario.getEmail());
        st.setString(4, usuario.getSenha());
        st.setInt(5, usuario.getTipoUsuario().getNumero());

        int linhasAfetadas = st.executeUpdate();

        if(linhasAfetadas > 0){
            System.out.println("Linhas Afetadas: " + linhasAfetadas);
        }
    }
    
    public void deleteById(Integer id) throws SQLException {

        String sql = "DELETE FROM usuarios WHERE id_usuario = ?";
        PreparedStatement st = database.getConnnection().prepareStatement(sql);

        st.setInt(1,id);

        int linhasAfetadas = st.executeUpdate();

        if(linhasAfetadas > 0){
            System.out.println("Linhas Afetadas: " + linhasAfetadas);
        }
    }
}
