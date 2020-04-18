package com.lucciani.cobranca.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lucciani.cobranca.model.StatusTitulo;
import com.lucciani.cobranca.model.Titulo;
import com.lucciani.cobranca.repository.TitulosRepository;

@Controller
@RequestMapping("/titulos")
public class TituloController {

	/*
	 * @titulosRepository: Injeta a dependencia do Objeto repository
	 * 
	 */
	
	@Autowired
	private TitulosRepository titulosRepository;

	/*
	 * @mv: retorna um objeto ModelAndView cadastro-titulo
	 * 
	 */
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView("cadastro-titulo");
		mv.addObject(new Titulo());
		return mv;
	}
	
	/*
	 * @titulo: Valida o objeto na entidade
	 * 
	 * @erros: Trata o erro relacionado a validações
	 * 
	 * @attributes: Envia a mensagem de sucesso na sessão e faz o redirect para a nova pagina
	 * 
	 * 
	 */

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Titulo titulo, Errors erros, RedirectAttributes attributes) {
		if(erros.hasErrors()) {
			return "cadastro-titulo";
		}
		
		titulosRepository.save(titulo);

		attributes.addFlashAttribute("mensagem", "Titulo salvo com sucesso!");
		return "redirect:/titulos/novo";
	}
	
	/*
	 * 
	 * 
	 * 
	 */

	@RequestMapping
	public ModelAndView pesquisar() {
		List<Titulo> todosTitulos = titulosRepository.findAll();
		ModelAndView mv = new ModelAndView("pesquisa-titulos");
		mv.addObject("titulos", todosTitulos);
		return mv;
	}
	
	/*
	 * 
	 * 
	 * 
	 */

	@ModelAttribute("todosStatusTitulo")
	public List<StatusTitulo> todosStatusTitulo() {
		return Arrays.asList(StatusTitulo.values());
	}

}
