package nl.hu.v1wac.firstapp.servlet;

import nl.hu.v1wac.firstapp.model.Country;
import nl.hu.v1wac.firstapp.persistence.CountryPostgresDaoImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet(urlPatterns = "/CalculatorServlet.do")
public class CalculatorServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Double firstNumber = 0.0;
		Double secondNumber = 0.0;
		String chosenOperator = "add";
		Double result = 0.0;	
		
		try {
			firstNumber = Double.parseDouble((req.getParameter("firstNumber")).replace(",", "."));
			secondNumber = Double.parseDouble((req.getParameter("secondNumber")).replace(",", "."));
			chosenOperator = req.getParameter("chosenOperator");
		}catch (Exception e) {
			System.out.println("Error: \n" + e);
		}	
		
		switch (chosenOperator) {
		case "add":
			result = firstNumber + secondNumber;
			break;
		case "substract":
			result = firstNumber - secondNumber;
			break;
		case "multiply":
			result = firstNumber * secondNumber;
			break;
		case "divide":
			result = firstNumber / secondNumber;
			break;		
		}
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println(" <title>Dynamic Example</title>");
		out.println(" <body>");
		out.println("  <h2>Result:</h2>");
		out.println("  <h2>" + result + "</h2>");
		out.println("  <a href='http://localhost:8080/firstapp' class='btn btn-default btn-custom'>Terug</a>");
		out.println(" </body>");
		out.println("</html>");		
	}
}
