package Util;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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
 * Servlet implementation class RegisterDispatcher
 */
@WebServlet("/RegisterDispatcher")
public class RegisterDispatcher extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    private static final String url = "jdbc:mysql://localhost/finalproj";

    /**
     * Default constructor.
     */
    public RegisterDispatcher() {
    }


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
    	
        String email = request.getParameter("email-signup");
        String name = request.getParameter("username-signup");
        String pass = request.getParameter("password-signup");
        String pass_c = request.getParameter("confirm-password-signup");
        

        
        if(email==null || email.equals("") || name==null || name.equals("") || pass ==null || pass.equals("") || pass_c ==null || pass_c.equals("")) {
        	error+="error";
        }
        
        if(pass.equals(pass_c)==false){
        	error+="error";
        }
        if(Helper.isValidEmail(email)==false) {
        	error+="error";
        }
        if(Helper.emailAlreadyRegistered(email)==true) {
        	error+="error";
        }
        if (Helper.checkUsername(name)) {
        	error += "error";
        }
        if (error.equals("")) {
        	try {
        		Helper.addUser(email, pass_c, name);
        	}
        	catch (Exception e) {
        		e.printStackTrace();
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
        	request.getRequestDispatcher("signup.jsp").forward(request, 
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
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
