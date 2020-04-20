package com.lucciani.cobranca.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lucciani.cobranca.model.StatusTitulo;
import com.lucciani.cobranca.model.Titulo;
import com.lucciani.cobranca.repository.TitulosRepository;
import com.lucciani.cobranca.services.CadastroTituloService;

@Controller
@RequestMapping("/titulos")
public class TituloController {

	private static final String CADASTRO_VIEW = "cadastro-titulo";

	/*
	 * @titulosRepository: Injeta a dependencia do Objeto repository
	 * 
	 */

	@Autowired
	private TitulosRepository titulosRepository;

	@Autowired
	private CadastroTituloService cadastroTitulosService;

	/*
	 * @mv: retorna um objeto ModelAndView cadastro-titulo
	 * 
	 */

	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Titulo());
		return mv;
	}

	/*
	 * @titulo: Valida o objeto na entidade
	 * 
	 * @erros: Trata o erro relacionado a validações
	 * 
	 * @attributes: Envia a mensagem de sucesso na sessão e faz o redirect para a
	 * nova pagina
	 * 
	 * 
	 */

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Titulo titulo, Errors erros, RedirectAttributes attributes) {
		if (erros.hasErrors()) {
			return CADASTRO_VIEW;
		}

		try {
			cadastroTitulosService.salvar(titulo);
			attributes.addFlashAttribute("mensagem", "Titulo salvo com sucesso!");
			return "redirect:/titulos/novo";
		} catch (IllegalArgumentException e) {
			erros.rejectValue("dataVencimento", null, e.getMessage());
			return CADASTRO_VIEW;
		}
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

	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Titulo titulo) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(titulo);
		return mv;
	}

	/*
	 * 
	 * @value: passagem de parametro para executar a query delete
	 * 
	 * 
	 */
	@RequestMapping(value = "/delete/{codigo}", method = RequestMethod.POST)
	public String excluir(@PathVariable("codigo") Long codigo, RedirectAttributes attributes) {
		cadastroTitulosService.excluir(codigo);

		attributes.addFlashAttribute("mensagem", "Título excluído com sucesso!");
		return "redirect:/titulos";
	}

	@RequestMapping(value = "/{codigo}/receber", method = RequestMethod.PUT)
	public @ResponseBody String receber(@PathVariable Long codigo) {

		return cadastroTitulosService.receber(codigo);

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
