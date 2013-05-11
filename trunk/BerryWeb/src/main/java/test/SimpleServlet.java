package test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entity.UserTest;

import org.apache.log4j.Logger;

import service.UserServiceImpl;

@WebServlet(urlPatterns={"/simpleServlet"})
public class SimpleServlet extends HttpServlet {

	
	@Inject
	private TestService testService;
	
	@Inject
	private UserServiceImpl userService;
	
	@Inject
	private Logger log;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2234587598851780160L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setContentType("text/html");
		resp.setHeader("X-Powered-By", "Servlet/3.0 JSP/2.2 (Jetty v9 JRE/1.8)");
		PrintWriter out = resp.getWriter();
		
		
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
        String str = String.valueOf(loader.getResource("org.slf4j.spi.LocationAwareLogger.class"));
		//testService.testCall();
		str = testService == null? "IT IS NULL" : "IT is NOT NULL";
		log.info("LLLOOOGGG");
		UserTest user = new UserTest();
	//	user.setId(2L);
		user.setName("Ivan");
		user.setAge(30);
		userService.save(user );
		
	//	UserTest userLoad= userService.load(1L);
		
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 "
				+ "Transitional//EN\">\n" + "<HTML>\n"
				+ "<HEAD><TITLE>Hello WWW</TITLE></HEAD>\n" + "<BODY>\n"
				+ "<H1>TEST SIMPLE SERVLET WWW</H1>\n" + user.getId() + "</BODY></HTML>");
	}

}
