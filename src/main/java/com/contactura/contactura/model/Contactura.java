package com.contactura.contactura.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor //Cria um construtor com todos os atributos da classe como arg
@NoArgsConstructor //Cria automaticamente o construtor vazio (padrão)
@Data //Cria os getters e setters 
@Entity //Informa que é uma entidade
public class Contactura {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String email;
	private String phone;
}
