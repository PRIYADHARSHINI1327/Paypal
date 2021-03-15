package com.paypal.bfs.test.employeeserv.impl;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;


public class EmployeeFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		
		 if (request instanceof HttpServletRequest) {
	            final String requestId = ((HttpServletRequest) request).getHeader("X-Request-ID");
	            if (requestId != null) {
	            	 filterChain.doFilter(request, response);
	            }
	        }		
	}

}
