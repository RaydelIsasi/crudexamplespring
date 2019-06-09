package com.example.RestService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.RestService.Entity.UsuarioEntity;

public interface UsuarioRepository extends CrudRepository<UsuarioEntity, Integer> {

	public List<UsuarioEntity> findByUsernameAndPassword(String username, String password);

}
