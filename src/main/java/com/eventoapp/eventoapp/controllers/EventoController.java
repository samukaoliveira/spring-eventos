package com.eventoapp.eventoapp.controllers;

import com.eventoapp.eventoapp.models.Evento;
import com.eventoapp.eventoapp.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EventoController {

    @Autowired
    private EventoRepository er;

    @RequestMapping(value="/cadastrarEvento", method= RequestMethod.GET)
    public String form(){
        return "evento/formEvento";
    }

    @RequestMapping(value="/cadastrarEvento", method= RequestMethod.POST)
    public String form(Evento evento){
        er.save(evento);
        return "redirect:/eventos";
    }

    @RequestMapping(value="/eventos")
    public ModelAndView listaEventos(){
        ModelAndView mv = new ModelAndView("/evento/index");
        Iterable<Evento> eventos = er.findAll();
        mv.addObject("eventos", eventos);
        return mv;
    }

    @RequestMapping("/{id}")
    public ModelAndView detalhesEvento(@PathVariable("id") long id){
        Evento evento = er.findById(id);
        ModelAndView mv = new ModelAndView("/evento/show");
        mv.addObject("evento", evento);
        return mv;
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView editarEvento(@PathVariable("id") long id){
        Evento evento = er.findById(id);
        ModelAndView mv = new ModelAndView("/evento/edit");
        mv.addObject("evento", evento);
        return mv;
    }

    @RequestMapping(value="/atualizarEvento", method= RequestMethod.POST)
    public String atualizarEvento(Evento evento, RedirectAttributes redirectAttributes){
        er.save(evento);
        redirectAttributes.addFlashAttribute("message", "Evento atualizado!");
        return "redirect:/eventos";
    }

    @RequestMapping("/deletarEvento/{id}")
    public String deletarEvento(long id, RedirectAttributes redirectAttributes){
        Evento evento = er.findById(id);
        er.delete(evento);
        redirectAttributes.addFlashAttribute("message", "Evento deletado com sucesso!");
        return "redirect:/eventos";
    }

}
