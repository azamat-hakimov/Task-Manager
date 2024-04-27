package org.task;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*") // Filter all requests
public class SessionExpirationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        if (session != null) {
            Long loginTime = (Long) session.getAttribute("loginTime");
            if (loginTime != null && System.currentTimeMillis() - loginTime > 24 * 60 * 60 * 1000) {
                // Session has expired, remove login details
                session.removeAttribute("username");
                session.removeAttribute("loginTime");
                session.invalidate(); // Invalidate the session
                // Redirect to the login page or any other page
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/logout.jsp");
                return;
            }
        }

        chain.doFilter(request, response);
    }
}