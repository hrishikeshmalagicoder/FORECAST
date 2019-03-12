package com.forecast;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.mysql.jdbc.Connection;

public class DBUtils {

	
	
	private static final Logger logger = LoggerFactory.getLogger(DBUtils.class);
	  public static Connection getConnection(){
		 
		 // logger.info("1");
		  
	        Connection con=null;  
	        try{  
	        	
	            Class.forName("com.mysql.jdbc.Driver");  
	          //  logger.info("2");
	            con= (Connection) DriverManager.getConnection("jdbc:mysql://prozeal.c4zmwip5h5pg.us-east-2.rds.amazonaws.com:3306/prozeal","bhanusimha","ProZeal123!");  
	          //  logger.info("3");
	        }catch(Exception e){System.out.println(e);}  
	        return con;  
	    }
	  
	  
	  
	
	  
	  public static boolean ValidateUser(String username,String password) {
			
		boolean flag=false;	
		
		  final String SQL="select * from validate_user where username=? and password=?";
		  
        
        try{  
            Connection con=DBUtils.getConnection();  
            logger.info("connection established");
            PreparedStatement ps=con.prepareStatement(SQL);
            ps.setString(1, username);
            ps.setString(2,password);
            
          
            ResultSet rs=ps.executeQuery();  
            logger.info("got result");
           if(rs.next()) {
        	   flag=true;
           }
            
            
            con.close();  
        }catch(Exception ex){ex.printStackTrace();}  
          
         return flag;
		
	}
	  
	  
	  
	  public static int getMaxTemperature() {
			
			int temp=0;
			
			  final String SQL="select max(temperature) as MAX from temperature";
			  
	        
	        try{  
	            Connection con=DBUtils.getConnection();  
	            logger.info("connection established");
	            PreparedStatement ps=con.prepareStatement(SQL);
	           
	            
	          
	            ResultSet rs=ps.executeQuery();  
	            logger.info("got result");
	           if(rs.next()) {
	        	  temp=rs.getInt("MAX");
	           }
	            
	            
	            con.close();  
	        }catch(Exception ex){ex.printStackTrace();}  
	          
	         return temp;
			
		}
	  
	  
	  
	  
	  public static int getMaxTemperatureFiltered(int lb,int ub) {
			
			int temp=0;
			
			  final String SQL="select max(temperature) as MAX from temperature where time>=? and time<=?";
			  
	        
	        try{  
	            Connection con=DBUtils.getConnection();  
	            logger.info("connection established");
	            PreparedStatement ps=con.prepareStatement(SQL);
	            ps.setInt(1, lb);
	            ps.setInt(2, ub);
	          
	            ResultSet rs=ps.executeQuery();  
	            logger.info("got result");
	           if(rs.next()) {
	        	  temp=rs.getInt("MAX");
	           }
	            
	            
	            con.close();  
	        }catch(Exception ex){ex.printStackTrace();}  
	          
	         return temp;
			
		}
	  
	  
	  
	  public static int getLastTransaction() {
			
			int temp=0;
			
			  final String SQL="select max(id) as MAX from api_tracker";
			  
	        
	        try{  
	            Connection con=DBUtils.getConnection();  
	            logger.info("connection established");
	            PreparedStatement ps=con.prepareStatement(SQL);
	           
	            
	          
	            ResultSet rs=ps.executeQuery();  
	            logger.info("got result");
	           if(rs.next()) {
	        	  temp=rs.getInt("MAX");
	        	  if(temp==0) {
	        		  temp=100;
	        	  	}
	        	}
	            
	            
	            con.close();  
	        }catch(Exception ex){ex.printStackTrace();}  
	          
	         return temp;
			
		}
	  
	  
	  
	  public static int insertRequest(int id,String name,String type,int lb,int ub) {
			
			int temp=0;
			
			  final String SQL="insert into api_tracker values(?,?,?,?,?,?)";
			  
	        
	        try{  
	            Connection con=DBUtils.getConnection();  
	            logger.info("connection established");
	            PreparedStatement ps=con.prepareStatement(SQL);
	            ps.setInt(1,id);
	            ps.setString(2,name);
	            ps.setString(3, type);
	            ps.setInt(4,lb);
	            ps.setInt(5,ub);
	            ps.setString(6,"");
	           
	            
	          
	            temp=ps.executeUpdate(); 
	            logger.info("got result");
	           
	            
	            
	            con.close();  
	        }catch(Exception ex){ex.printStackTrace();}  
	          
	         return temp;
			
		}
	  
	  
	  public static int insertResponse(int id,String resp) {
			
			int temp=0;
			
			  final String SQL="update api_tracker set response=? where id=?";
			  
	        
	        try{  
	            Connection con=DBUtils.getConnection();  
	            logger.info("connection established");
	            PreparedStatement ps=con.prepareStatement(SQL);
	            ps.setString(1,resp);
	            ps.setInt(2, id);
	            
	            
	          
	            temp=ps.executeUpdate(); 
	            logger.info("got result");
	           
	            
	            
	            con.close();  
	        }catch(Exception ex){ex.printStackTrace();}  
	          
	         return temp;
			
		}
	  
	  
	  
	  public static List<TemperaturePOJO> getRecords(int tempp) {
			
			List<TemperaturePOJO> list=new ArrayList<TemperaturePOJO>();
			
			  final String SQL="select *  from temperature where temperature=?";
			  
	        
	        try{  
	            Connection con=DBUtils.getConnection();  
	            logger.info("connection established");
	            PreparedStatement ps=con.prepareStatement(SQL);
	           ps.setInt(1,tempp);
	            
	          
	            ResultSet rs=ps.executeQuery();  
	            logger.info("got result");
	            while(rs.next()) {
	            	
	            	TemperaturePOJO temp=new TemperaturePOJO();
	            	temp.setTime(rs.getInt(1));
	            	temp.setCity(rs.getString(2));
	            	temp.setTemperature(rs.getInt(3));
	            	
	            	list.add(temp);
	            }
	            
	            
	            con.close();  
	        }catch(Exception ex){ex.printStackTrace();}  
	          
	         return list;
			
		}
	  	
	  
	  public static List<TemperaturePOJO> getRecordsFiltered(int tempp,int lb,int ub) {
			
			List<TemperaturePOJO> list=new ArrayList<TemperaturePOJO>();
			
			  final String SQL="select *  from temperature where temperature=? and time>=? and time<=?";
			  
	        
	        try{  
	            Connection con=DBUtils.getConnection();  
	            logger.info("connection established");
	            PreparedStatement ps=con.prepareStatement(SQL);
	           ps.setInt(1,tempp);
	           ps.setInt(2, lb);
	           ps.setInt(3, ub);
	           
	            
	          
	            ResultSet rs=ps.executeQuery();  
	            logger.info("got result");
	            while(rs.next()) {
	            	
	            	TemperaturePOJO temp=new TemperaturePOJO();
	            	temp.setTime(rs.getInt(1));
	            	temp.setCity(rs.getString(2));
	            	temp.setTemperature(rs.getInt(3));
	            	
	            	list.add(temp);
	            }
	            
	            
	            con.close();  
	        }catch(Exception ex){ex.printStackTrace();}  
	          
	         return list;
			
		}





	
}
