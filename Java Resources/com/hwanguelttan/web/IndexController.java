package com.hwanguelttan.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/index")
public class IndexController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");
		String eId = request.getParameter("eId");
		String ePf = request.getParameter("ePf");
		String phone = request.getParameter("phone");
		String gen = request.getParameter("inlineRadioOptions");
		String address = request.getParameter("address");
		
		String url = "jdbc:oracle:thin:@211.204.34.28:1521/xepdb1";
		String sql = "INSERT INTO MEMBER(NAME,EMAIL_ID,EMAIL_PLATFORM,PHONE,GENDER,ADDRESS) VALUES(?,?,?,?,?,?)";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "HWANGUELTTAN", "tntorwndeo1!");
			PreparedStatement pst = con.prepareStatement(sql);
			
			pst.setString(1, name);
			pst.setString(2, eId);
			pst.setString(3, ePf);
			pst.setString(4, phone);
			pst.setString(5, gen);
			pst.setString(6, address);
			
			ResultSet rs = pst.executeQuery();
			
			response.sendRedirect("index");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
