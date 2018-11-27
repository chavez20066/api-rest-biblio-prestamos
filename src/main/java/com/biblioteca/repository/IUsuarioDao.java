package com.biblioteca.repository;

import org.springframework.data.repository.CrudRepository;

import com.biblioteca.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long>{

	public Usuario findByUsername(String username);
}
