package burung.loginfilter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/jsp")
public class LoginFilter implements Filter {

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		String spage = req.getParameter("spage");
		switch(spage) {
		case "Board/boardinsert.jsp":
		//case "Board/boardinsert.jsp":
			//원하는 경로 case 추가
			loginCheck(req, resp, chain);
			break;
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	private void loginCheck (ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		boolean login = false;

		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = request.getSession();

		if (session != null) {
			if (session.getAttribute("id") != null) {
				login = true;
			}
		}

		if (login) {
			chain.doFilter(req, resp);
		} else {
			HttpServletResponse response = (HttpServletResponse) resp;
			response.sendRedirect(request.getContextPath() + "/jsp/layout.jsp?spage=members/login.jsp");
		}
	}
}
