package trainBooking.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class RequestFilter implements Filter {
	private FilterConfig filterConfig = null;

	@Override
	public void destroy() {
		this.filterConfig = null;
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		chain.doFilter(request, response);
		response.setContentType("text/html; charset=UTF-8");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		this.filterConfig = filterConfig;
		
	}


}
