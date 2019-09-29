package com.example.RestService.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.RestService.Entity.UsuarioEntity;
import com.example.RestService.dto.UsuarioDto;
import com.example.RestService.repository.UsuarioRepository;
import com.google.gson.Gson;

@Service
public class UsuarioServiceImpl implements IUsuario {
	@Autowired
	private UsuarioRepository usuarioR;

	@Override
	public String insertarUsuario(UsuarioDto u) {

		String s = "success";

		UsuarioEntity ue = new UsuarioEntity();
		ue.setPassword(u.getPassword());
		ue.setUsername(u.getUsername());
		if (u.getPassword().equals(u.getPassword2())) {
			try {
				usuarioR.save(ue);

			} catch (DataIntegrityViolationException e) {
				e.getMessage();
				s = "El usuario ya existe!";
			}
		} else {
			s = "Las contraseñas no coinciden. Intente nuevamente.";
		}

		return s;

	}

	@Override
	public String actualizarUsuario(UsuarioDto u) {

		String s = "success";
		UsuarioEntity ue = new UsuarioEntity();
		ue.setId(u.getId());
		ue.setPassword(u.getPassword());
		ue.setUsername(u.getUsername());

		try {
			usuarioR.save(ue);

		} catch (DataIntegrityViolationException e) {
			s = "El usuario ya existe!";
		}

		return s;

	}

	@Override
	public String eliminarUsuario(int idusuario) {

		Gson json = new Gson();

		String r = "";
		json.toJson(r);

		try {
			 usuarioR.deleteById(idusuario);
			r = "Se ha eliminado satisfactoriamente";

		} catch (Exception e) {

			r = "Falla al eliminar el usuario";
		}

		return r;

	}

	@Override
	public Optional<UsuarioEntity> buscarUsuario(int idusuario) {

		Optional<UsuarioEntity> u = usuarioR.findById(idusuario);
		
		return u;
	}

	@Override
	public boolean validarUsuario(String username, String password) {
		boolean flag = false;

		List<UsuarioEntity> us = usuarioR.findByUsernameAndPassword(username, password);

		if (us != null) {
			flag = true;
		}

		return flag;
	}

	@Override
	public List<UsuarioEntity> listarUsuarios() {
		List<UsuarioEntity> usuarios = (List<UsuarioEntity>) usuarioR.findAll();
		return usuarios;
	}

}
