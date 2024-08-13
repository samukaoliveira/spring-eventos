package com.eventoapp.eventoapp.repository;

import com.eventoapp.eventoapp.models.Convidado;
import com.eventoapp.eventoapp.models.Evento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConvidadoRepository extends CrudRepository<Convidado, String> {
}
