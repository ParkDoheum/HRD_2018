package kr.co.hk;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Utils {	
	public static void dispatcher(String view, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TODO sflasjdflasjflasf
		request.setAttribute("view", view + ".jsp");
		request.getRequestDispatcher("temp.jsp").forward(request, response);
	}
}
