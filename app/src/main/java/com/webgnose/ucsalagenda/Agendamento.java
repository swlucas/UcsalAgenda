package com.webgnose.ucsalagenda;

public class Agendamento {
    private String requerente;
    private String titulo;
    private String mensagem;
    private String horario;

    public Agendamento(){

    }

    public Agendamento(String requerente, String titulo, String mensagem, String horario) {
        this.requerente = requerente;
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.horario = horario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getRequerente() {
        return requerente;
    }

    public void setRequerente(String requerente) {
        this.requerente = requerente;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }



    @Override
    public String toString() {
        return titulo;
    }
}
