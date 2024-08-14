package com.eventoapp.eventoapp.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import jakarta.validation.constraints.NotEmpty;


@Entity
public class Evento implements Serializable {

    private static final long serialVersionUIO = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "O nome é obrigatório")
    private String nome;

    @NotEmpty(message = "O local é obrigatório")
    private String local;

    @NotEmpty(message = "A data é obrigatório")
    private String data;

    @NotEmpty(message = "O horário é obrigatório")
    private String horario;

    @OneToMany
    private List<Convidado> convidados;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Convidado> getConvidados() {
        return convidados;
    }

    public void setConvidado(List<Convidado> convidados) {
        this.convidados = convidados;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evento evento = (Evento) o;
        return Objects.equals(nome, evento.nome) && Objects.equals(local, evento.local) && Objects.equals(data, evento.data) && Objects.equals(horario, evento.horario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, local, data, horario);
    }
}

