package Util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//import com.google.gson.*;
//import com.google.gson.reflect.TypeToken;

import java.util.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import Util.*;
import java.sql.*;

public class Helper {
    /**
     * check if name is valid
     *
     * @param name the name user provides
     * @return valid or not valid
     */
    public static boolean validName(String name) {
        return Constant.namePattern.matcher(name).matches();
    }

    /**
     * check if email is valid
     *
     * @param email the email user provides
     * @return valid or not valid
     */
    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        return Constant.emailPattern.matcher(email).matches();
    }
    
    //returns albumname given search query name
    public static String albumName(String name) {
    	Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String db = "jdbc:mysql://localhost/finalproj";
            conn = DriverManager.getConnection(db, Constant.DBUserName, Constant.DBPassword);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        String album = "";
        //Select cover_url FROM finalproj.albums where album_name LIKE '%Sour%';
		String sql = "SELECT * "
				+ "FROM albums "
				+ "WHERE album_name LIKE '%"+name+"%';";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql);) {
        	while(rs.next()) {
        		album = rs.getString("album_name");
        	}
 			
  		} catch (SQLException sqle) {
  			
  		}
        return album;
    }
    
    //returns cover link from album id
    public static String getCover(String ID) {
    	Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String db = "jdbc:mysql://localhost/finalproj";
            conn = DriverManager.getConnection(db, Constant.DBUserName, Constant.DBPassword);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        String cover = "";
        //Select cover_url FROM finalproj.albums where album_name LIKE '%Sour%';
		String sql = "SELECT * "
				+ "FROM albums "
				+ "WHERE album_id = '"+ID+"';";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql);) {
        	while(rs.next()) {
        		cover = rs.getString("cover_url");
        	}
 			
  		} catch (SQLException sqle) {
  			
  		}
        System.out.println("cover"+cover);
        return cover;
    }
    
    //returns cover link from int id OUTDATED
    public static String getCover(int id) {
    	Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String db = "jdbc:mysql://localhost/finalproj";
            conn = DriverManager.getConnection(db, Constant.DBUserName, Constant.DBPassword);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        String cover = "";
        //Select cover_url FROM finalproj.albums where album_name LIKE '%Sour%';
		String sql = "SELECT * "
				+ "FROM albums "
				+ "WHERE album_id LIKE '%"+id+"%';";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql);) {
        	while(rs.next()) {
        		cover = rs.getString("cover_url");
        	}
 			
  		} catch (SQLException sqle) {
  			
  		}
        System.out.println("cover"+cover);
        return cover;
    }
    
    //returns artist name from album ID
    public static String getArtist(String ID) {
    	Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String db = "jdbc:mysql://localhost/finalproj";
            conn = DriverManager.getConnection(db, Constant.DBUserName, Constant.DBPassword);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        String artist = "";
        //Select cover_url FROM finalproj.albums where album_name LIKE '%Sour%';
		String sql = "SELECT * "
				+ "FROM finalproj.ARTISTS as a "
				+ "INNER JOIN finalproj.ALBUMS AS al on a.artist_id = al.artist_id "
				+ "WHERE al.album_id = '"+ID+"' "
				+"ORDER BY al.releasedate DESC;";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql);) {
        	while(rs.next()) {
        		artist = rs.getString("artist_name");
        	}
 			
  		} catch (SQLException sqle) {
  			
  		}
        System.out.println("name "+artist);
        return artist;
    }
    
    //returns release date from album ID
    public static String getDate(String ID) {
    	Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String db = "jdbc:mysql://localhost/finalproj";
            conn = DriverManager.getConnection(db, Constant.DBUserName, Constant.DBPassword);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        String date = "";
        //Select cover_url FROM finalproj.albums where album_name LIKE '%Sour%';
		String sql = "SELECT * "
				+ "FROM albums "
				+ "WHERE album_id = '"+ID+"';";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql);) {
        	while(rs.next()) {
        		date = rs.getString("releasedate");
        	}
 			
  		} catch (SQLException sqle) {
  			
  		}
        System.out.println("date "+date);
        return date;
    }
    
    //returns id from exact album name
    public static String getID(String album) {
    	Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String db = "jdbc:mysql://localhost/finalproj";
            conn = DriverManager.getConnection(db, Constant.DBUserName, Constant.DBPassword);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        String albID = "";
        //Select cover_url FROM finalproj.albums where album_name LIKE '%Sour%';
		String sql = "SELECT * "
				+ "FROM albums "
				+ "WHERE album_name = '"+album+"';";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql);) {
        	while(rs.next()) {
        		albID = rs.getString("album_id");
        	}
 			
  		} catch (SQLException sqle) {
  			
  		}
        System.out.println("id "+albID);
        if (albID.isEmpty()) {
        	albID = "null";
        }
        return albID;
    }
    
    //returns name from exact id
    public static String getName(String ID) {
    	Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String db = "jdbc:mysql://localhost/finalproj";
            conn = DriverManager.getConnection(db, Constant.DBUserName, Constant.DBPassword);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        String name = "";
        //Select cover_url FROM finalproj.albums where album_name LIKE '%Sour%';
		String sql = "SELECT * "
				+ "FROM albums "
				+ "WHERE album_id = '"+ID+"';";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql);) {
        	while(rs.next()) {
        		name = rs.getString("album_name");
        	}
 			
  		} catch (SQLException sqle) {
  			
  		}
        System.out.println("name "+name);
        return name;
    }
    
    //returns userid from search username
    public static int getUserID(String user) {
    	Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String db = "jdbc:mysql://localhost/finalproj";
            conn = DriverManager.getConnection(db, Constant.DBUserName, Constant.DBPassword);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        int userID = 0;
        //Select cover_url FROM finalproj.albums where album_name LIKE '%Sour%';
		String sql = "SELECT * "
				+ "FROM USERS "
				+ "WHERE username LIKE '%"+user+"%';";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql);) {
        	while(rs.next()) {
        		userID = rs.getInt("user_id");
        	}
 			
  		} catch (SQLException sqle) {
  			
  		}
        System.out.println("user "+userID);
        return userID;
    }
    
    //returns avg rating from album id
    public static double getRating(String ID) {
    	try {
			update(ID);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String db = "jdbc:mysql://localhost/finalproj";
            conn = DriverManager.getConnection(db, Constant.DBUserName, Constant.DBPassword);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        double rating = 0;
        //Select cover_url FROM finalproj.albums where album_name LIKE '%Sour%';
		String sql = "SELECT * "
				+ "FROM albums "
				+ "WHERE album_id LIKE '"+ID+"';";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql);) {
        	while(rs.next()) {
        		rating = rs.getDouble("rating");
        	}
 			
  		} catch (SQLException sqle) {
  			
  		}
        System.out.println("rating "+rating);
        return rating;
    }
    
    //broken dont use
    public static Map<String,String> getReviews(String album) {
    	Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String db = "jdbc:mysql://localhost/finalproj";
            conn = DriverManager.getConnection(db, Constant.DBUserName, Constant.DBPassword);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String,String> revs = new HashMap<>();;
        //Select cover_url FROM finalproj.albums where album_name LIKE '%Sour%';
		String sql = "SELECT * "
				+ "FROM album_reviews as rev "
				+ "INNER JOIN USERS as us on rev.user_id = us.user_id "
				+ "WHERE rev.album_id LIKE '%"+String.valueOf(Helper.getID(album))+"%';";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql);) {
        	while(rs.next()) {
        		String review = rs.getString("review");
        		String username = rs.getString("username");
        		revs.put(username,review);
        		System.out.println("rev"+revs.get(username));
        	}
 			
  		} catch (SQLException sqle) {
  			
  		}
        return revs;
    }
    
    //returns reviews list (list of reviews, with review, username, rating) from album id
    public static ArrayList<ArrayList<String>> getRevs(String ID) {
    	Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String db = "jdbc:mysql://localhost/finalproj";
            conn = DriverManager.getConnection(db, Constant.DBUserName, Constant.DBPassword);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<ArrayList<String>> reviews = new ArrayList<>();
        
        //Select cover_url FROM finalproj.albums where album_name LIKE '%Sour%';
		String sql = "SELECT * "
				+ "FROM album_reviews as rev "
				+ "INNER JOIN USERS as us on rev.user_id = us.user_id "
				+ "WHERE rev.album_id = '"+ID+"';";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql);) {
        	while(rs.next()) {
        		String reviewText = rs.getString("review");
        		String username = rs.getString("username");
        		String rating = Integer.toString(rs.getInt("rating"));
        		ArrayList<String> review = new ArrayList<>();
        		review.add(reviewText);
        		review.add(username);
        		review.add(rating);
        		reviews.add(review);
        	}
 			
  		} catch (SQLException sqle) {
  			
  		}
        for (int i=0; i<reviews.size(); i++) {
        	System.out.println("Rev: "+reviews.get(i).get(0));
        }
        return reviews;
    }
    
    //returns reviews list from exact username
    public static ArrayList<ArrayList<String>> getRevsUsername(String name) {
    	Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String db = "jdbc:mysql://localhost/finalproj";
            conn = DriverManager.getConnection(db, Constant.DBUserName, Constant.DBPassword);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<ArrayList<String>> reviews = new ArrayList<>();
        
        //Select cover_url FROM finalproj.albums where album_name LIKE '%Sour%';
		String sql = "SELECT * "
				+ "FROM album_reviews as rev "
				+ "INNER JOIN USERS as us on rev.user_id = us.user_id "
				+ "WHERE us.username = '"+name+"' order by rating desc;";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql);) {
        	while(rs.next()) {
        		String reviewText = rs.getString("review");
        		String albumID = rs.getString("album_id");
        		String rating = Integer.toString(rs.getInt("rating"));
        		ArrayList<String> review = new ArrayList<>();
        		review.add(reviewText);
        		review.add(albumID);
        		review.add(rating);
        		reviews.add(review);
        	}
 			
  		} catch (SQLException sqle) {
  			
  		}
        for (int i=0; i<reviews.size(); i++) {
        	System.out.println("Rev: "+reviews.get(i).get(0));
        }
        return reviews;
    }
    
    //returns reviews list from exact userID
    public static ArrayList<ArrayList<String>> getRevsUserID(String ID) {
    	Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String db = "jdbc:mysql://localhost/finalproj";
            conn = DriverManager.getConnection(db, Constant.DBUserName, Constant.DBPassword);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<ArrayList<String>> reviews = new ArrayList<>();
        
        //Select cover_url FROM finalproj.albums where album_name LIKE '%Sour%';
		String sql = "SELECT * "
				+ "FROM album_reviews as rev "
				+ "INNER JOIN USERS as us on rev.user_id = us.user_id "
				+ "WHERE rev.user_id = '"+ID+"' order by rev.rating desc;";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql);) {
        	while(rs.next()) {
        		String reviewText = rs.getString("review");
        		String username = rs.getString("username");
        		String rating = Integer.toString(rs.getInt("rating"));
        		ArrayList<String> review = new ArrayList<>();
        		review.add(reviewText);
        		review.add(username);
        		review.add(rating);
        		reviews.add(review);
        	}
 			
  		} catch (SQLException sqle) {
  			
  		}
        for (int i=0; i<reviews.size(); i++) {
        	System.out.println("Rev: "+reviews.get(i).get(0));
        }
        return reviews;
    }
    
    //returns album duration from albumid
    public static String getAlbumDuration(String ID) {
    	Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String db = "jdbc:mysql://localhost/finalproj";
            conn = DriverManager.getConnection(db, Constant.DBUserName, Constant.DBPassword);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        int duration = 0;
        //Select cover_url FROM finalproj.albums where album_name LIKE '%Sour%';
		String sql = "SELECT * "
				+ "FROM albums "
				+ "WHERE album_id = '"+ID+"'"
				+ " ORDER BY releasedate DESC;";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql);) {
        	while(rs.next()) {
        		duration = rs.getInt("duration");
        	}
 			
  		} catch (SQLException sqle) {
  			
  		}
        System.out.println("len "+ duration);
        int secs = duration % 60;
        int mins = duration / 60;
        String dur = String.valueOf(mins) + " mins " + String.valueOf(secs) + " secs";
        if (mins > 60) {
        	int mins_new = mins % 60;
        	int hours = mins / 60;
        	dur = String.valueOf(hours) + " hours " + String.valueOf(mins_new) + " minutes";
        }
        return dur;
    }
    
    // returns album release date from id
    public static String getReleaseDate(String ID) {
    	Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String db = "jdbc:mysql://localhost/finalproj";
            conn = DriverManager.getConnection(db, Constant.DBUserName, Constant.DBPassword);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        String date = "";
        
		String sql = "SELECT * "
				+ "FROM albums "
				+ "WHERE album_id = '"+ID+"'"
				+ " ORDER BY releasedate DESC;";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql);) {
        	while(rs.next()) {
        		date = rs.getString("releasedate");
        	}
 			
  		} catch (SQLException sqle) {
  			
  		}
        return date;
    }
    
    //returns username from exact email
    public static String getUserName(String email) throws SQLException {
    	Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String db = "jdbc:mysql://localhost/finalproj";
            conn = DriverManager.getConnection(db, Constant.DBUserName, Constant.DBPassword);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        String pp = "";
		String sql = "SELECT r.username "
				+ "FROM USERS r "
				+ "WHERE r.email ='"+email+"';";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql);) {
        	while(rs.next()) {
        		pp = rs.getString("username");
        	}
 			
  		} catch (SQLException sqle) {
  			
  		}
        return pp;
    }

    //returns songs list from exact album id
    public static ArrayList<String> getSongs(String id) {
    	Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String db = "jdbc:mysql://localhost/finalproj";
            conn = DriverManager.getConnection(db, Constant.DBUserName, Constant.DBPassword);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        String artist = "";
        //Select cover_url FROM finalproj.albums where album_name LIKE '%Sour%';
		String sql = "SELECT * "
				+ "FROM finalproj.SONGS as s "
				+ "INNER JOIN finalproj.ALBUMS AS al on s.album_id = al.album_id "
				+ "WHERE al.album_id = '"+id+"' "
				+"ORDER BY s.position ASC;";
		
		ArrayList<String> songs = new ArrayList<String>();
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql);) {
        	while(rs.next()) {
        		songs.add(rs.getString("song_name"));
        		System.out.println("song: "+rs.getString("song_name"));
        	}
 			
  		} catch (SQLException sqle) {
  			
  		}
        
        return songs;
    }

    /**
     * check if the email and password matches
     *
     * @param email
     * @param password
     */
    public static boolean checkPassword(String email, String password) throws SQLException {
    	Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String db = "jdbc:mysql://localhost/finalproj";
            conn = DriverManager.getConnection(db, Constant.DBUserName, Constant.DBPassword);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        String pp = null;
		String sql = "SELECT r.pass "
				+ "FROM USERS r "
				+ "WHERE r.email ='"+email+"';";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql);) {
        	rs.next();
 			pp = rs.getString("pass");

  					
  			
  		} catch (SQLException sqle) {
  			
  		}
        if(password.equals(pp)) {
        	return true;
        }
        return false;
    }
    
    public static boolean checkUsername(String username) {
    	Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String db = "jdbc:mysql://localhost/finalproj";
            conn = DriverManager.getConnection(db, Constant.DBUserName, Constant.DBPassword);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		String sql = "SELECT * "
				+ "FROM users u "
				+ "WHERE u.username ='"+username+"';";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql);) {
        	if (rs.next()) {
//        		System.out.println("Already registered");
        		return true;
        	}

  		} catch (SQLException sqle) {
  			
  		}
        return false;
    }


    public static void addUser(String email, String password, String name) throws SQLException {
    	Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String db = "jdbc:mysql://localhost/finalproj";
            conn = DriverManager.getConnection(db, Constant.DBUserName, Constant.DBPassword);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
        	Random random = new Random();
        	int rand_id = random.nextInt(1000000);
        	String sql = "INSERT INTO USERS (user_id, username, email, pass, song_url) VALUES (?, ?, ?, ?, ?)";
        	PreparedStatement ps = conn.prepareStatement(sql);
        	 ps.setInt(1, rand_id);
        	 ps.setString(2, name);
        	 ps.setString(3, email);
        	 ps.setString(4, password);
        	 ps.setString(5, "");
        	 int row = ps.executeUpdate();   
        }
        catch (Exception e) {
        	 e.printStackTrace();
        }
       
    }
    
    public static boolean checkAlbum(String albumid) {
    	Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String db = "jdbc:mysql://localhost/finalproj";
            conn = DriverManager.getConnection(db, Constant.DBUserName, Constant.DBPassword);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		String sql = "SELECT * "
				+ "FROM albums a "
				+ "WHERE a.album_id ='"+albumid+"';";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql);) {
        	if (rs.next()) {
        		return true;
        	}

  		} catch (SQLException sqle) {
  			
  		}
        return false;
    }
    
    public static boolean checkArtist(String artistid) {
    	Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String db = "jdbc:mysql://localhost/finalproj";
            conn = DriverManager.getConnection(db, Constant.DBUserName, Constant.DBPassword);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		String sql = "SELECT * "
				+ "FROM artists a "
				+ "WHERE a.artist_id ='"+artistid+"';";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql);) {
        	if (rs.next()) {
        		return true;
        	}

  		} catch (SQLException sqle) {
  			
  		}
        return false;
    }
    
    public static boolean checkSong(String songid) {
    	Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String db = "jdbc:mysql://localhost/finalproj";
            conn = DriverManager.getConnection(db, Constant.DBUserName, Constant.DBPassword);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		String sql = "SELECT * "
				+ "FROM songs s "
				+ "WHERE s.song_id ='"+songid+"';";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql);) {
        	if (rs.next()) {
        		return true;
        	}

  		} catch (SQLException sqle) {
  			
  		}
        return false;
    }
    
    public static void addSong(String songid, String name, int duration, int position, String artistid, String albumid) throws SQLException {

    	if (!checkSong(songid)) {
	    	Connection conn = null;
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            String db = "jdbc:mysql://localhost/finalproj";
	            conn = DriverManager.getConnection(db, Constant.DBUserName, Constant.DBPassword);
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        try {
	        	String sql = "INSERT INTO SONGS (song_id, song_name, duration, position, artist_id, album_id) VALUES (?, ?, ?, ?, ?, ?)";
	        	PreparedStatement ps = conn.prepareStatement(sql);
	        	 ps.setString(1, songid);
	        	 ps.setString(2, name);
	        	 ps.setInt(3, duration);
	        	 ps.setInt(4, position);
	        	 ps.setString(5, artistid);
	        	 ps.setString(6,albumid);
	        	 int row = ps.executeUpdate();   
	        }
	        catch (Exception e) {
	        	 e.printStackTrace();
	        }
    	}
    }
    
    public static void addAlbum(String albumid, String name, String cover, String artistid, int runtime, String date) throws SQLException {

    	if (!checkAlbum(albumid)) {
	    	Connection conn = null;
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            String db = "jdbc:mysql://localhost/finalproj";
	            conn = DriverManager.getConnection(db, Constant.DBUserName, Constant.DBPassword);
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        try {
	        	Random random = new Random();
	        	int rand_id = random.nextInt(1000000);
	        	String sql = "INSERT INTO ALBUMS (album_id, album_name, cover_url, artist_id, duration, releasedate) VALUES (?, ?, ?, ?, ?, ?)";
	        	PreparedStatement ps = conn.prepareStatement(sql);
	        	 ps.setString(1, albumid);
	        	 ps.setString(2, name);
	        	 ps.setString(3, cover);
	        	 ps.setString(4, artistid);
	        	 ps.setInt(5, runtime);
	        	 ps.setString(6,date);
	        	 int row = ps.executeUpdate();   
	        }
	        catch (Exception e) {
	        	 e.printStackTrace();
	        }
    	}
    }
    
    public static void addArtist(String artistid, String name) throws SQLException {

    	if (!checkArtist(artistid)) {
	    	Connection conn = null;
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            String db = "jdbc:mysql://localhost/finalproj";
	            conn = DriverManager.getConnection(db, Constant.DBUserName, Constant.DBPassword);
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        try {
	        	String sql = "INSERT INTO ARTISTS (artist_id, artist_name) VALUES (?, ?)";
	        	PreparedStatement ps = conn.prepareStatement(sql);
	        	ps.setString(1, artistid);
	        	ps.setString(2, name);
	        	int row = ps.executeUpdate();   
	        }
	        catch (Exception e) {
	        	 e.printStackTrace();
	        }
    	}
    }
    
    public static void update(String ID) throws SQLException {
    	Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String db = "jdbc:mysql://localhost/finalproj";
            conn = DriverManager.getConnection(db, Constant.DBUserName, Constant.DBPassword);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
        	String sql = "UPDATE albums SET rating = ("
        			+ "SELECT AVG(rating) "
        			+ "FROM album_reviews "
        			+ "WHERE album_id ='" + ID + "') "
        			+ "WHERE (album_id ='" + ID + "')";
        	PreparedStatement ps = conn.prepareStatement(sql);
        	 int row = ps.executeUpdate();   
        }
        catch (Exception e) {
        	 e.printStackTrace();
        }
       
    }

    /**
     * Check if email is already registered
     *
     * @param email
     * @param request
     * @param response
     * @return email registered or not
     * @throws ServletException
     * @throws IOException
     */
    public static boolean emailAlreadyRegistered(String email)
            throws ServletException {
    	Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String db = "jdbc:mysql://localhost/finalproj";
            conn = DriverManager.getConnection(db, Constant.DBUserName, Constant.DBPassword);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        String pp = null;
		String sql = "SELECT * "
				+ "FROM USERS r "
				+ "WHERE r.email ='"+email+"';";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql);) {
        	while(rs.next()) {
        		pp = rs.getString("email");
        	}

  		} catch (SQLException sqle) {
  			
  		}
        if(email.equals(pp)) {
        	return true;
        }
        return false;
    }

}
