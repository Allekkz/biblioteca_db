/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unincor.biblioteca_db.model.domain;

/**
 *
 * @author Alex
 */
public class Livro {

    private Long idLivro;
    private String titulo;
    private String genero;
    private Autor Autor;

    public Livro() {

    }

    public Livro(Long idLivro, String titulo, String genero, Autor Autor) {
        this.idLivro = idLivro;
        this.titulo = titulo;
        this.genero = genero;
        this.Autor = Autor;
    }

    public Long getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(Long idLivro) {
        this.idLivro = idLivro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Autor getAutor() {
        return Autor;
    }

    public void setAutor(Autor Autor) {
        this.Autor = Autor;
    }



}
