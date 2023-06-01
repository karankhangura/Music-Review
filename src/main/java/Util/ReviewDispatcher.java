package Util;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

import java.io.IOException;

import com.google.gson.*;

import com.google.gson.reflect.TypeToken;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import Util.*;
import java.sql.*;
import java.io.*;
/**
 * Servlet implementation class ReviewDispatcher
 */
@WebServlet("/ReviewDispatcher")
public class ReviewDispatcher extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewDispatcher() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String db = "jdbc:mysql://localhost/finalproj";
            conn = DriverManager.getConnection(db, Constant.DBUserName, Constant.DBPassword);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        Random random = new Random();
    	int rand_id = random.nextInt(1000000);
        String review = (String) request.getParameter("review");
        String username = (String) request.getParameter("username");
        String id = (String) (request.getParameter("id"));
        if (username.compareToIgnoreCase("guest")==0){
        	//request.setAttribute("guesterror", "true");
        	HttpSession session = request.getSession();
        	session.setAttribute("getAlert", "guest");//Just initialize a random variable.
        	//request.getRequestDispatcher("details.jsp?albumid"+id).forward(request, response);
        	response.sendRedirect("details.jsp?albumid="+id);
        }
        else {
        	HttpSession session = request.getSession();
        	session.setAttribute("getAlert", "user");
	        int rating = Integer.parseInt(request.getParameter("rating"));
			String reviewSql = "INSERT INTO ALBUM_REVIEWS (album_reviewid, album_id, review, rating,user_id) VALUES (?, ?, ?, ?, ?)";
	    			PreparedStatement ps;
					try {
						ps = conn.prepareStatement(reviewSql);
						{
			    			ps.setInt(1, rand_id);
			    			ps.setString(2, id);
			    			ps.setString(3, review);
			    			ps.setInt(4, rating);
			    			ps.setInt(5, Helper.getUserID(username));
			    			int row = ps.executeUpdate();}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			//recompute average rating and fix in sql
			try {
				Helper.update(id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect("details.jsp?albumid="+id);
			//request.getRequestDispatcher("details.jsp?="+Helper.getName(id)).forward(request, response);
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
