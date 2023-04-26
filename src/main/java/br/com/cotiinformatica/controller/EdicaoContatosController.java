package br.com.cotiinformatica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EdicaoContatosController {
	
	@RequestMapping(value = "/admin/edicao-contatos")
	public ModelAndView edicaoContatos() {
		// WEB-INF/views/admin/edicao-contatos.jsp
		ModelAndView modelAndView = new ModelAndView("admin/edicao-contatos");
		return modelAndView;
	}
}