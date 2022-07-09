package com.resource.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

public class KeycloakRolesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

	@Override
	public Collection<GrantedAuthority> convert(Jwt jwt) {

		Map<String, Object> realmAccess = (Map<String, Object>) jwt.getClaims().get("realm_access");
		
		if(Objects.isNull(realmAccess)) {
			return new ArrayList<>();
		}

		List<String> roleList = (List<String>) realmAccess.get("roles");

		return roleList.stream().map(roleName -> "ROLE_" + roleName).map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());

	}

}
