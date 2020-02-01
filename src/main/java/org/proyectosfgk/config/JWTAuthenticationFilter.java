package org.proyectosfgk.config;

import java.io.IOException;
import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.proyectosfgk.entity.Usuario;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final Key SECRET_KEY;

	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, Key secret) {
		this.authenticationManager = authenticationManager;
		setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/usuarios/login", "POST"));
		this.SECRET_KEY = secret;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		// Obtener datos a traves de form-data
		// String username = obtainUsername(request);
		// String password = obtainPassword(request);
		// logger.info("PASSWORD: " + password + " USERNAME: " + username);
		// if(username != null && password != null)
		// logger.info("PASSWORD: " + password + " USERNAME: " + username);
		Usuario user = new Usuario();
		try {
			user = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
			// logger.info("PASSWORD: " + user.getClave() + " USERNAME: " +
			// user.getCorreo());
		} catch (IOException e) {
			logger.error("ERROR CONVIRTIENDO VALORES DE USUARIO: " + e.getMessage());
		}

		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user.getCorreo(),
				user.getClave());
		return authenticationManager.authenticate(authToken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		// Obtenemos el usuario que esta en sesion.
		String principal = ((User) authResult.getPrincipal()).getUsername();

		// Insertamos los roles del usuario
		Claims claims = Jwts.claims();
		claims.put("Authorities", new ObjectMapper().writeValueAsString(authResult.getAuthorities()));

		// Tiempo de expiracion de media hora
		Date timeExpiration = new Date(System.currentTimeMillis() + 1800000L);

		String token = Jwts.builder().setClaims(claims).setSubject(principal).signWith(SECRET_KEY)
				.setIssuedAt(new Date()).setExpiration(timeExpiration).compact();

		response.addHeader("Authorization", "Bearer " + token);

		Map<String, Object> body = new HashMap<String, Object>();
		body.put("token", token);
		body.put("user", (User) authResult.getPrincipal());
		body.put("mensaje", String.format("Hola %s, has iniciado sesión con éxito!",
				((User) authResult.getPrincipal()).getUsername()));
		body.put("Token Expiration Date", new SimpleDateFormat("dd-MMM-yyyy hh:MM:ss").format(timeExpiration));

		response.setCharacterEncoding("UTF-8");
		response.setLocale(new Locale("es", "ES"));
		response.getWriter().write(new ObjectMapper().writeValueAsString(body));
		response.setStatus(200);
		response.setContentType("application/json");
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {

		Map<String, Object> body = new HashMap<String, Object>();
		body.put("mensaje", "Error de autenticación: username o password incorrecto!");
		body.put("error", failed.getMessage());

		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(new ObjectMapper().writeValueAsString(body));
		response.setStatus(401);
		response.setContentType("application/json");
	}

}
