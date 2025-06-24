/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unincor.biblioteca_db.model.dao;

import com.unincor.biblioteca_db.configurations.MySQL;
import com.unincor.biblioteca_db.model.domain.Autor;
import java.sql.Connection;
import java.sql.Date;
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
public class AutorDao {
    public void inserirAutor(Autor Autor) {
        String sql = "INSERT INTO autores(nome, nacionalidade, data_nascimento) VALUES (?, ?, ?)";
        try (Connection con = MySQL.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, Autor.getNome());
            //Inserir valores corretos // FEITO
            ps.setString(2, Autor.getNacionalidade());
            ps.setDate(3, Date.valueOf(Autor.getData_nascimento()));
            // --
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(AutorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Autor> buscarTodosAutores() {
        List<Autor> clientes = new ArrayList<>();
        String sql = "SELECT * FROM autores";
        try (Connection con = MySQL.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                var cliente = construirAutorSql(rs);
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AutorDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return clientes;
    }

    public Autor buscarAutorPorId(Long idAutor) {
        String sql = "SELECT * FROM autores WHERE id_autor = ?";
        try (Connection con = MySQL.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, idAutor);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return construirAutorSql(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AutorDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public Autor construirAutorSql(ResultSet rs) throws SQLException {
        Autor cliente = new Autor();
        cliente.setIdAutor(rs.getLong("id_autor"));
        cliente.setNome(rs.getString("nome"));
        //novos:
        
        return cliente;
    }

    public static void main(String[] args) {
        //Inserir o cliente aqui depois...
//        Autor cliente = new Autor(null, "AlexPFCampos", "21324654", LocalDate.now(),
//               "alexpfc@gmail.com", "4564654897", "389102312749128903");
        AutorDao clienteDao = new AutorDao();
//        var clientes = clienteDao.buscarTodosAutors();
//        clientes.forEach(c -> System.out.println("ID: " + c.getIdAutor() + " Nome: " + c.getNome() + " Cpf: " + c.getCpf()
//                + " Data nascimento: " + c.getDataNascimento() + " Email: " + c.getEmail() + " telefone: " + c.getTelefone()
//                + " senha_hash: " + c.getSenhaHash()
//        ));
        
        var c = clienteDao.buscarAutorPorId(1l);
        System.out.println("Id: " + c.getIdAutor()
                + " Nome: " + c.getNome());
        
    }
}
