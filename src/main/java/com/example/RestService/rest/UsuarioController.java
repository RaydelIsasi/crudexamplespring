package com.example.RestService.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;

import javax.xml.ws.Response;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.RestService.Entity.UsuarioEntity;
import com.example.RestService.dto.UsuarioDto;
import com.example.RestService.dto.UsuarioStatus;
import com.example.RestService.service.IUsuario;
import com.example.RestService.service.UsuarioServiceImpl;
import com.google.gson.Gson;

@RestController
public class UsuarioController {

	@Value("${config.name}")
	String name;
	@Autowired
	IUsuario userv;

	@RequestMapping(value = "/usuario/", method = RequestMethod.POST)
	public ResponseEntity<Object> insertarUsuario(@RequestBody UsuarioDto usuario) {

		String s = userv.insertarUsuario(usuario);
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", s);

		return new ResponseEntity<Object>(map, HttpStatus.OK);

	}

	@RequestMapping(value = "/usuario/{id}", method = RequestMethod.DELETE, produces = {
			"application/json" }, consumes = { "application/json" })
	public ResponseEntity<Object> eliminarUsuario(@PathVariable("id") int id) {
		String prueba1 = userv.eliminarUsuario(id);

		Map<String, String> map = new HashMap<String, String>();
		map.put("message", prueba1);

		return new ResponseEntity<Object>(map, HttpStatus.OK);

	}

	@RequestMapping("/")
	public String home() {
		return "testing " + name;
	}

	@RequestMapping(value = "/usuario/", method = RequestMethod.PUT)
	public ResponseEntity<Object> actualizarUsuario(@RequestBody UsuarioDto usuario) {

		String s = userv.actualizarUsuario(usuario);
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", s);
		return new ResponseEntity<Object>(map, HttpStatus.OK);

	}

	@RequestMapping(value = "/usuario/{id}", method = RequestMethod.GET)
	public Optional<UsuarioEntity> buscarUsuario(@PathVariable("id") int id) {

		return userv.buscarUsuario(id);
	}

	@RequestMapping(value = "/usuario/", method = RequestMethod.GET)
	public List<UsuarioEntity> listarUsuarios() {

		List<UsuarioEntity> lista = userv.listarUsuarios();
		// return new ResponseEntity<Object>(HttpStatus.ACCEPTED);
		return lista;
	}

	@RequestMapping(value = "/usuario/login/", method = RequestMethod.POST)
	public ResponseEntity<Object> validarUsuario(@RequestBody Map<String, String> req) {

		String s = "Login Failure";
		String username = req.get("username");
		String pass = req.get("password");

		boolean validate = userv.validarUsuario(username, pass);

		if (validate) {
			s = "success";
		}

		JSONObject a = null;
		try {
			a = new JSONObject(s);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Map<String, String> map = new HashMap<String, String>();
		map.put("message", s);
		return new ResponseEntity<Object>(map, HttpStatus.OK);

	}

}
