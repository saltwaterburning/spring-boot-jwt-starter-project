/*
 * Copyright 2000-2017 Holon TDCN.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.arj.jaxrs.springboot.auth.jwt;

import java.util.stream.Collectors;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import org.springframework.stereotype.Component;

import com.holonplatform.auth.Authentication;
import com.holonplatform.auth.annotations.Authenticate;
import com.holonplatform.http.HttpHeaders;

@Authenticate(schemes = HttpHeaders.SCHEME_BEARER)
@Component
@Path("/api")
public class ProtectedEndpoint {

	@PermitAll
	@GET
	@Path("/roles")
	@Produces(MediaType.TEXT_PLAIN)
	public String getAccountRoles(@Context SecurityContext securityContext) {
		// get Authentication
		Authentication authc = (Authentication) securityContext.getUserPrincipal();
		// get roles
		return authc.getPermissions().stream().map(p -> p.getPermission().orElse(null))
				.collect(Collectors.joining(","));
	}

	@RolesAllowed("ROLE1")
	@GET
	@Path("/role1")
	@Produces(MediaType.TEXT_PLAIN)
	public String getRole1() {
		return "role1";
	}

	@RolesAllowed("ROLE2")
	@GET
	@Path("/role2")
	@Produces(MediaType.TEXT_PLAIN)
	public String getRole2() {
		return "role2";
	}

	@RolesAllowed("ROLE3")
	@GET
	@Path("/role3")
	@Produces(MediaType.TEXT_PLAIN)
	public String getRole3() {
		return "role3";
	}
}
