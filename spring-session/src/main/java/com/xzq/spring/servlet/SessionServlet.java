package com.xzq.spring.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/session")
public class SessionServlet extends HttpServlet {

	private static final long serialVersionUID = 6338100458281021471L;

	@Override
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		String sesssionID = request.getSession().getId();
		System.out.println("sesssionID-------" + sesssionID);

		String email = (String) request.getSession().getAttribute("email");
		System.out.println("email-------" + email);

		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.append("sesssionID : " + sesssionID + "\n");
			out.append("{\"name\":\"xzq\"}" + "\n");
			out.append("email : " + email);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}

	}

}
