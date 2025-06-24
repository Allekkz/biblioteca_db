/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unincor.biblioteca_db.model.dao;

import com.unincor.biblioteca_db.configurations.MySQL;
import com.unincor.biblioteca_db.model.domain.Autor;
import com.unincor.biblioteca_db.model.domain.Livro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alex
 */
public class LivroDao {

    public void inserirLivro(Livro livro) {
        String sql = "INSERT INTO livros(titulo, genero, id_autor) VALUES (?, ?, ?)";
        try (Connection con = MySQL.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, livro.getTitulo());
            ps.setString(2, livro.getGenero());
            if (livro.getAutor() != null) {
                ps.setLong(3, livro.getAutor().getIdAutor());
            } else {
                ps.setObject(3, null);
            }
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(LivroDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Buscar:
    public List<Livro> listarTodosLivros() {
        String sql = "SELECT * FROM Livros";
        List<Livro> livros = new ArrayList<>();
        try (Connection con = MySQL.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                livros.add(construirLivroSql(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(LivroDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return livros;
    }

    public Livro buscarLivroPorId(Long id) {
        String sql = "SELECT * FROM Livros where id_livro = ?";
        try (Connection con = MySQL.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return construirLivroSql(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LivroDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // Buscar livro pelo código // Teste, corrigir em aula:
    public Livro buscarLivroPorCodigoLivro(String codigoLivro) {
        String sql = "SELECT * FROM Livros where id_livro = ?";
        try (Connection con = MySQL.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, codigoLivro);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return construirLivroSql(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LivroDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    // ==

    public Livro construirLivroSql(ResultSet rs) throws SQLException {
        Livro livro = new Livro();
        livro.setIdLivro(rs.getLong("id_livro"));
        // Mudar aqui:
        livro.setTitulo(rs.getString("titulo"));
        livro.setGenero(rs.getString("genero"));
        // Teste CASO TENHA UM OBJETO NOS ATRIBUTOS DE UMA TABELA:
        Autor autor = new AutorDao().buscarAutorPorId(rs.getLong("id_autor"));
        autor.setIdAutor(rs.getLong("id_autor"));
        livro.setAutor(autor);
        // --

        return livro;
    }

    // Main: 
    public static void main(String[] args) {
        //Inserir livro aqui:
        LivroDao livroDao = new LivroDao();

        var l = livroDao.buscarLivroPorId(1l);
        System.out.println("Id: " + l.getIdLivro()
                + " Nome: " + l.getTitulo());
    }
}