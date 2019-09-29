package com.example.RestService.service;

import java.util.List;
import java.util.Optional;

import org.json.JSONObject;

import com.example.RestService.Entity.UsuarioEntity;
import com.example.RestService.dto.UsuarioDto;
import com.google.gson.Gson;

public interface IUsuario {

	String insertarUsuario(UsuarioDto u);

	String actualizarUsuario(UsuarioDto u);

	String eliminarUsuario(int idusuario);

	Optional<UsuarioEntity> buscarUsuario(int idusuario);

	boolean validarUsuario(String username, String password);

	List<UsuarioEntity> listarUsuarios();

}
