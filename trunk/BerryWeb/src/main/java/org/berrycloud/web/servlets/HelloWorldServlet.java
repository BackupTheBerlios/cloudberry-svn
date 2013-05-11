package org.berrycloud.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.berrycloud.model.entity.contact.User;
import org.berrycloud.service.contact.UserService;

@WebServlet(urlPatterns={"/helloWorld"})
public class HelloWorldServlet extends HttpServlet {
	
	private static final long serialVersionUID = 4645041976884046854L;

	@Inject
	private Logger log;
	
	@Inject
	private UserService userService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setContentType("text/html");
		resp.setHeader("X-Powered-By", "Servlet/3.0 (Jetty v9 JRE/1.8)");
		PrintWriter out = resp.getWriter();
				
        User user = new User();
        user.setFirstName("Ivan");
        user.setLastName("Katsarov");
        user.setTitle("Mr.");
        
        userService.save(user);
        
		
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 "
				+ "Transitional//EN\">\n" + "<HTML>\n"
				+ "<HEAD><TITLE>Hello WWW</TITLE></HEAD>\n" + "<BODY>\n"
				+ "<H1>HELLO WORLD SERVLET </H1>\n" + user.getId() + "</BODY></HTML>");
	}
}
