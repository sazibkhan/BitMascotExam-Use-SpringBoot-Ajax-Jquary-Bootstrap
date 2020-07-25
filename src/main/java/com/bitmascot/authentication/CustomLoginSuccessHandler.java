package com.bitmascot.authentication;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@Configuration
public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {
		String torgetUtl = determineTargetUrl(authentication);
		if (response.isCommitted()) {
			return;
		}
		RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
		redirectStrategy.sendRedirect(request, response, torgetUtl);
	}


	protected String determineTargetUrl(final Authentication authentication) {
        boolean isUser = false;
        boolean isAdmin = false;
        final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (final GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("USER")) {
                isUser = true;
                break;
            } else if (grantedAuthority.getAuthority().equals("ADMIN")) {
                isAdmin = true;
                break;
            }
        }

        if (isUser) {
            return "/user.html";
        } else if (isAdmin) {
            return "/admin/home";
        } else {
            throw new IllegalStateException();
        }
    }
	/*	protected String determineTargetUrl(Authentication authentication) {
	String url = "/login?error=true";
	Collection<? extends GrantedAuthority> authorities;
	authorities = authentication.getAuthorities();
	List<String> roles = new ArrayList<>();
	for (GrantedAuthority a : authorities) {
		roles.add(a.getAuthority());
	}
	if (roles.contains("ADMIN_USER")) {
		url = "/admin";
	} else if (roles.contains("USER")) {
		url = "/homepage";
	}
	return url;
}*/

}
