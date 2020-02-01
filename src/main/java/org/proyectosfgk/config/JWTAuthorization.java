package org.proyectosfgk.config;

import java.io.IOException;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.proyectosfgk.utils.SimpleGrantedAuthoritiesMixin;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

public class JWTAuthorization extends BasicAuthenticationFilter {

	private final Key SECRET_KEY;

	public JWTAuthorization(AuthenticationManager authenticationManager, Key secret) {
		super(authenticationManager);
		this.SECRET_KEY = secret;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String token = request.getHeader("Authorization");

		if (token == null || !token.startsWith("Bearer ")) {
			logger.info("EL USUARIO AUN NO HA INICIADO SESION.");
			chain.doFilter(request, response);
			return;
		}

		UsernamePasswordAuthenticationToken authenticationToken = null;
		Claims claim = null;
		try {
			claim = Jwts.parser().setSigningKey(SECRET_KEY)
			.parseClaimsJws(token.replace("Bearer ", "")).getBody();
		} catch (JwtException | IllegalArgumentException e) {
			logger.error("ERROR VERIFICANDO EL TOKEN: " + e.getMessage());
			Map<String, Object> body = new HashMap<String, Object>();
			body.put("mensaje", "Error de autenticación");
			body.put("error", e.getMessage());

			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(new ObjectMapper().writeValueAsString(body));
			response.setStatus(401);
			response.setContentType("application/json");
		}

		if (claim != null) {
			logger.info("BIENVENIDO: " + claim.getSubject());
			Object authorities = claim.get("Authorities");
			
			Collection<? extends GrantedAuthority> roles = Arrays.asList(
					new ObjectMapper().addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthoritiesMixin.class)
							.readValue(authorities.toString().getBytes(), SimpleGrantedAuthority[].class));

			authenticationToken = new UsernamePasswordAuthenticationToken(claim.getSubject(), null, roles);

			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			chain.doFilter(request, response);
		}
	}

}
