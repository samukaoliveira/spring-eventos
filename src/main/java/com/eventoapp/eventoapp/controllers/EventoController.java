package com.eventoapp.eventoapp.controllers;

import com.eventoapp.eventoapp.models.Convidado;
import com.eventoapp.eventoapp.models.Evento;
import com.eventoapp.eventoapp.repository.ConvidadoRepository;
import com.eventoapp.eventoapp.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class EventoController {

    @Autowired
    private EventoRepository er;

    @Autowired
    private ConvidadoRepository cr;

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
        ModelAndView mv = new ModelAndView("evento/index");
        Iterable<Evento> eventos = er.findAll();
        mv.addObject("eventos", eventos);
        return mv;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView detalhesEvento(@PathVariable("id") long id){
        Evento evento = er.findById(id);
        ModelAndView mv = new ModelAndView("evento/show");
        mv.addObject("evento", evento);

        Iterable<Convidado> convidados = cr.findByEvento(evento);
        mv.addObject("convidados", convidados);
        return mv;
    }




    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String cadastraConvidado(@PathVariable("id") long id, Convidado convidado){
        Evento evento = er.findById(id);
        convidado.setEvento(evento);
        cr.save(convidado);
        return "redirect:/{id}";
    }


    @RequestMapping("/edit/{id}")
    public ModelAndView editarEvento(@PathVariable("id") long id){
        Evento evento = er.findById(id);
        ModelAndView mv = new ModelAndView("evento/edit");
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
        redirectAttributes.addFlashAttribute("alert", "Evento deletado com sucesso!");
        return "redirect:/eventos";
    }

    @RequestMapping("/deletarConvidado")
    public String deletarConvidado(Long id){
        Convidado convidado = cr.findById(id);
        Evento evento = convidado.getEvento();
        Long eventoId = evento.getId();
        String codigo = "" + eventoId;
        cr.delete(convidado);
        return "redirect:/" + codigo;
    }

    @RequestMapping(value="/editaConvidado", method= RequestMethod.POST)
    public String editaConvidado(@ModelAttribute Convidado convidado){
        cr.save(convidado);
        Evento evento = convidado.getEvento();
        Long evId = evento.getId();
        String codigo = "" + evId;
        return  "redirect:/" + codigo;
    }

}
