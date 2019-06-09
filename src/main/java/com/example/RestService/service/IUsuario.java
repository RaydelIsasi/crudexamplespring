package com.example.RestService.service;

import java.util.List;

import org.json.JSONObject;

import com.example.RestService.Entity.UsuarioEntity;
import com.example.RestService.dto.UsuarioDto;
import com.google.gson.Gson;

public interface IUsuario {

	String insertarUsuario(UsuarioDto u);

	String actualizarUsuario(UsuarioDto u);

	String eliminarUsuario(int idusuario);

	UsuarioEntity buscarUsuario(int idusuario);

	boolean validarUsuario(String username, String password);

	List<UsuarioEntity> listarUsuarios();

}
