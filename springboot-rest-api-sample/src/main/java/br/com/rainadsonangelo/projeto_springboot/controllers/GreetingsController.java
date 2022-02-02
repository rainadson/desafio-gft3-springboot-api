package br.com.rainadsonangelo.projeto_springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.rainadsonangelo.projeto_springboot.model.Usuario;
import br.com.rainadsonangelo.projeto_springboot.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {

	@Autowired /* IC/CD ou CDI - Injeção de dependencia */
	private UsuarioRepository usuarioRepository;

	/**
	 *
	 * @param name the name to greet
	 * @return greeting text
	 */
	@RequestMapping(value = "/mostrarnome/{name}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String greetingText(@PathVariable String name) {
		return "Testando: " + name + "!";
	}

	@RequestMapping(value = "/olamundo/{nome}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String retornaOlaMundo(@PathVariable String nome) {
		Usuario usuario = new Usuario();
		usuario.setNome(nome);

		usuarioRepository.save(usuario);

		return "Ola Mundo:  " + nome;
	}

	@GetMapping(value = "listatodos")
	@ResponseBody /* Retorna os dados para o copor da resposta */
	public ResponseEntity<List<Usuario>> listaUsuario() {

		List<Usuario> usuarios = usuarioRepository.findAll();/* executa consulta no BD */

		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK); /* retorna lista em Json */

	}

	@PostMapping(value = "salvar") /* mapeia a url */
	@ResponseBody /* Descrição da resposta */
	public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) {/* recebe dados para salvar */
		Usuario user = usuarioRepository.save(usuario);

		return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);

	}

	@DeleteMapping(value = "delete") /* mapeia a url */
	@ResponseBody /* Descrição da resposta */
	public ResponseEntity<String> delete(@RequestParam long iduser) {/* recebe dados para deletar */
		usuarioRepository.deleteById(iduser);

		return new ResponseEntity<String>("User deletado com sucesso", HttpStatus.OK);

	}
	
	@GetMapping(value = "buscaruserid") /* mapeia a url */
	@ResponseBody /* Descrição da resposta */
	public ResponseEntity<Usuario> buscaruserid(@RequestParam(name = "iduser") long iduser) {/* recebe dados para buscar */
		Usuario usuario = usuarioRepository.findById(iduser).get();

		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);

	}
	
	@PutMapping(value = "atualizar") /* mapeia a url */
	@ResponseBody /* Descrição da resposta */
	public ResponseEntity<?> atualizar(@RequestBody Usuario usuario) {/* recebe dados para salvar */
	
		Usuario user = usuarioRepository.saveAndFlush(usuario);

		return new ResponseEntity<Usuario>(user, HttpStatus.OK);

	}
	
	@GetMapping(value = "buscarPorNome") /* mapeia a url */
	@ResponseBody /* Descrição da resposta */
	public ResponseEntity<List<Usuario>> buscarPorNome( @RequestParam(name = "name") String name) {/* recebe dados para buscar */
		List<Usuario> usuario = usuarioRepository.buscarPorNome(name.toUpperCase());
		
		return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);

	}

}
