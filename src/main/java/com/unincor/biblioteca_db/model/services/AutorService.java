/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unincor.biblioteca_db.model.services;

import com.unincor.biblioteca_db.exceptions.CadastroException;
import com.unincor.biblioteca_db.model.dao.AutorDao;
import com.unincor.biblioteca_db.model.domain.Autor;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alex
 */
public class AutorService {
    private final AutorDao autorDao = new AutorDao();

    public void salvarAutor(Autor autor) throws CadastroException {
        if (autor.getNome() == null || autor.getNome().isBlank()) {
            throw new CadastroException("A autor não possui um nome!");
        }


        // validar se a autor esta com cidade e uf preenchido:
//        Autor autorCidade = autorDao.buscarAutorPorCodigoAutor(autor.getCidade());
//        Autor autorUf = autorDao.buscarAutorPorCodigoAutor(autor.getUf());
        if (autor.getNacionalidade()== null || autor.getNacionalidade().isBlank()) {
            throw new CadastroException("O autor não possui nacionalidade!");
        }

        autorDao.inserirAutor(autor);
    }
    
    public List<Autor> buscarAutor() {
        return autorDao.buscarTodosAutores();
    }

public static void main(String[] args) {
        AutorService autorService = new AutorService();

        Autor autor = new Autor(null, "Alex", "Brasil", LocalDate.parse("2002-02-02"));
        
        try {
            autorService.salvarAutor(autor);
        } catch (CadastroException ex) {
            Logger.getLogger(AutorService.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
}
