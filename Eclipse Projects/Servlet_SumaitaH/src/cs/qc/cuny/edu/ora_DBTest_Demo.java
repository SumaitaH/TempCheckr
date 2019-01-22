package cs.qc.cuny.edu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class ora_DBTest_Demo {
    public ora_DBTest_Demo (){
    }
    public String testconnection_mysql (int hr_offset) {
        Connection connect = null;
        PreparedStatement preparedStatement = null;
        String dbTime = "";

        try {
            // This will load the MySQL driver, each DB has its own driver
            //
        	Class.forName("com.mysql.cj.jdbc.Driver");
            // Setup the connection with the DB
            //String jdbcUrl = "jdbc:mysql://" + "test-instance.cklrfhk17snj.us-east-2.rds.amazonaws.com" + ":" + "3306" + "/" + "sample" + "?user=" + "testuser" + "&password=" + "password";
            //connect = DriverManager
            //        .getConnection("jdbc:mysql://weatherapp.cozvmlu5fx03.us-east-1.rds.amazonaws.com:3306/weatherhistory", "admin", "password");
            //connect = DriverManager.getConnection(jdbcUrl);
        	connect = DriverManager
  		          .getConnection("jdbc:mysql://localhost:3306/demodb", "demouser", "demopw");
            System.out.println("connected");
            String qry1a = "SELECT * FROM HISTORY";  
            System.out.println(qry1a);
            preparedStatement = connect.prepareStatement(qry1a);
            // "id, uid, create_time, token for id_management.id_logtime";
            // Parameters start with 1
            ResultSet r1=preparedStatement.executeQuery();
            if (r1.next())
            {
                String nt = r1.getString(2);
                dbTime = nt;
                System.out.println(hr_offset + " hour(s) ahead of the system clock of MySQL is: " + nt);
            }
            r1.close();
            preparedStatement.close();
        } catch (Exception e) {
            try {
                throw e;
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (connect != null) {
                try {
                    connect.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return dbTime;
    }
    public int testConnection (int hr_offset) {
        //String jdbcDriver = "oracle.jdbc.driver.OracleDriver";
        String dbURL1 = "jdbc:oracle:thin:@venus.cs.qc.cuny.edu:1521:venus";
        String userName1 = "ec";
        String userPassword1 = "cs370cs381";
        String oracle_driver = "oracle.jdbc.driver.OracleDriver";
        Connection conn;
        PreparedStatement pstmt;
        ResultSet rs;
        int flag = 0;
        String newTime;

        try
        {
            Class.forName(oracle_driver);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        try
        {
            conn = DriverManager.getConnection(dbURL1, userName1, userPassword1);
            String stmtQuery = "select sysdate + " + hr_offset + " from dual";
            pstmt = conn.prepareStatement(stmtQuery);
            // pstmt.setString(1,usrname);
            rs = pstmt.executeQuery();
            if (rs.next())
            {
                newTime = rs.getString(1);
                System.out.println(hr_offset + " hour(s) ahead of the system clock of Oracle at bonnet19 is:" + newTime);
            }
            rs.close();
            pstmt.close();
            try
            {
                conn.close();
            }
            catch (SQLException e) {};
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            flag = -1;
        }
        return flag;
    }
    public void addToHistory(String date, String location) {
    	
        Connection connect = null;
        PreparedStatement preparedStatement = null;
        //String dbTime = "";
    	try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/demodb", "demouser", "demopw");
			String sqlQuery = "INSERT INTO HISTORY (search_string, count, date_searched) VALUES('" + location + "', 0, '"+ date + "')";
			System.out.println("The query:" + sqlQuery);
			preparedStatement = connect.prepareStatement(sqlQuery);
			preparedStatement.executeUpdate();
			preparedStatement.close();
	    	connect.close(); 
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
          //System.out.println("connected");
 
    }
    
    public ArrayList<String> generateResultSet (String sqlQuery) {
    	ArrayList<String> searchHistory = new ArrayList<String>();
    	ResultSet rs = null;
    	PreparedStatement preparedStatement = null; 
    	Connection connect = null; 
    	try {
    		//Class.forName("com.cj.mysql.jdbc.Driver");
	    	connect = DriverManager
			          .getConnection("jdbc:mysql://localhost:3306/demodb", "demouser", "demopw");

				preparedStatement = connect.prepareStatement(sqlQuery);
				//rs = preparedStatement.executeQuery();
	            ResultSet r1=preparedStatement.executeQuery();
	            while (r1.next())
	            {
	            	String location = r1.getString(2);
	                String date = r1.getString(4);
	                searchHistory.add(location + " " + date);
	                System.out.println(location + " " + date);
	            }
				//System.out.println("resultset: " + r1.getFetchSize());
	            r1.close();
	            preparedStatement.close();

			connect.close();
		} 
    	//catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
		//	e1.printStackTrace();
		//}			 
    catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		//} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		//e.printStackTrace();
	//}

    	
    }
    	return searchHistory;}
    public static void main(String[] args)
    {
        try
        {
            if (args.length != 1) {
                System.out.println("Usage: java -jar Ora_DBTest.jar <number_of_hr_offset>");
                System.out.println("Success returns errorlevel 0. Error return greater than zero.");
                System.exit(1);
            }
            /* Print a copyright. */
            System.out.println("Example for Oracle DB connection via Java");
            System.out.println("Copyright: Bon Sy");
            System.out.println("Free to use this at your own risk!");
            ora_DBTest_Demo DBConnect_instance = new ora_DBTest_Demo();
//
//            if (DBConnect_instance.testConnection(Integer.parseInt(args[0])) == 0) {
//                System.out.println("Successful Completion");
//            } else {
//                System.out.println("Oracle DB connection fail");
//            }
            System.out.println("\n\nThis is connecting to mysql\n\n");
            DBConnect_instance.testconnection_mysql(Integer.parseInt(args[0]));
            DBConnect_instance.generateResultSet("SELECT SEARCH_STRING,DATE_SEARCHED FROM HISTORY");
        }
        catch (Exception e){
            // probably error in input
            System.out.println("Hmmm... Looks like input error ....");
        }
    }
}