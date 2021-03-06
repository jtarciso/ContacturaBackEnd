package com.contactura.contactura.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contactura.contactura.model.ContacturaUser;
import com.contactura.contactura.repository.ContacturaUserRepository;

@RestController //Torna um bin gerenciavel. Contém as anotações @Controller @ResponseBody
@RequestMapping({"/contacturauser"}) //Definir o mepeamento da requisição http://kocalhost:8095/contacturauser
public class ContacturaUserController {

	@Autowired //Informa que a instância ContacturaUserRepository quer usar na propriedade repository. Não precisa criar o new
	private ContacturaUserRepository repository;
	
	
	//Método findAll lista todos os usuários. Faz um select * from contacturauser.
	@GetMapping  
	public List findAll() {
		return repository.findAll();
	}
	
	
	//Obtendo um usuário específico pelo id pelo método findById. Faz um select * from contacturauser where id = ?
	@GetMapping(value = "{id}") 
	public ResponseEntity findById(@PathVariable long id) {
		return repository.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	//Método Create
	@PostMapping
	public ContacturaUser create(@RequestBody ContacturaUser contacturauser) {
		return repository.save(contacturauser);
	}
	
	
	//Método Update
	@PutMapping
	public ResponseEntity update(@PathVariable long id,
			@RequestBody ContacturaUser contacturauser) {
		return repository.findById(id)
				.map(record -> {
					record.setUsername(contacturauser.getUsername());
					record.setPassword(contacturauser.getPassword());
					record.setName(contacturauser.getName());
					record.setAdmin(false); //verificar
					ContacturaUser update = repository.save(record);
					return ResponseEntity.ok().body(update);
					}).orElse(ResponseEntity.notFound().build());
				
				
	}
	
	//Método Delete
	@DeleteMapping(path = {"/{id}"})
	public ResponseEntity <?> delete(@PathVariable long id) {
		return repository.findById(id) 
			.map(record -> {
				repository.deleteById(id);
				return ResponseEntity.ok().build();
			 }).orElse(ResponseEntity.notFound().build());
	
		}
}
