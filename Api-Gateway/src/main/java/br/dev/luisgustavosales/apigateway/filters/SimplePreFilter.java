package br.dev.luisgustavosales.apigateway.filters;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class SimplePreFilter extends ZuulFilter{

	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		// TODO Auto-generated method stub
		RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        System.out.println("Hostname: " + request.getRemoteHost());
        
        var headers = request.getHeaderNames();
        
        while (headers.asIterator().hasNext()) {
        	var header = headers.nextElement();
        	System.out.println(header +": " + request.getHeader(header));
        }
        
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        String username;
        
        if (principal instanceof UserDetails) {
          username = ((UserDetails)principal).getUsername();
          System.out.println("Username"+ username);
        } else {
          username = principal.toString();
          System.out.println("Username"+ username);
        }
        
        ctx.addZuulRequestHeader("username", username);
        
        
		return null;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "pre";
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

}
