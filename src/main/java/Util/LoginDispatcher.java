package Util;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.*;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class LoginDispatcher
 */
@WebServlet("/LoginDispatcher")
public class LoginDispatcher extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	  response.setContentType("text/html;charset=UTF-8");
          PrintWriter out = response.getWriter();
          String error = "";
      	
          String email = request.getParameter("email-login");
          String pass = request.getParameter("password-login");
          
          if(email==null || email.equals("") || pass ==null || pass.equals("")) {
          	error+="error";
       
          }
          if(Helper.isValidEmail(email)==false) {
          	error+="error";
          }
          try {
	          if(Helper.checkPassword(email,pass)==false) {
	        	  error+="error";
	        	  
	          }
          }
          catch (Exception E) {
        	 
          }

          if (error.equals("")) {
        	  String name = "";
        	  try {
        		  name = Helper.getUserName(email);
        		 
        	  }
        	  catch (Exception e) {
        		  
        	  } 
        	  String temp = name.replaceAll(" ", "=");
              Cookie em = new Cookie("em", temp);
              em.setMaxAge(60*60);
              response.addCookie(em);
        	  response.sendRedirect("home.jsp");

          }

          else {
          	error = "Invalid email or password. Please Try again.";
          	request.setAttribute("error", error);
          	request.getRequestDispatcher("login.jsp").forward(request, 
          			response);
          }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
