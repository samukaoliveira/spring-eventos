package com.eventoapp.eventoapp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.Objects;

@Entity
public class Convidado {

    @NotEmpty(message = "O nome é obrigatório")
    private String nomeConvidado;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rg;

    @ManyToOne
    private Evento evento;

    public @NotEmpty(message = "O nome é obrigatório") String getNomeConvidado() {
        return nomeConvidado;
    }

    public void setNomeConvidado(@NotEmpty(message = "O nome é obrigatório") String nomeConvidado) {
        this.nomeConvidado = nomeConvidado;
    }

    public @NotEmpty(message = "O RG é obrigatório") Long getRg() {
        return rg;
    }

    public void setRg(@NotEmpty(message = "O RG é obrigatório") Long rg) {
        this.rg = rg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Convidado convidado = (Convidado) o;
        return Objects.equals(nomeConvidado, convidado.nomeConvidado) && Objects.equals(rg, convidado.rg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeConvidado, rg);
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
}
