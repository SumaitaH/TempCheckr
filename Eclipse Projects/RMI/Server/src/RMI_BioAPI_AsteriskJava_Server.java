import java.io.BufferedReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.rmi.RemoteException;

import java.net.*;

import java.rmi.server.*;
import java.rmi.Naming;
 

/**
 * 
 */

/**
 * @author bon
 *
 */
public class RMI_BioAPI_AsteriskJava_Server extends UnicastRemoteObject
implements RMI_BioAPI_AsteriskJava_Interface {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public RMI_BioAPI_AsteriskJava_Server(int port) throws RemoteException
    {
        super(port);
    }

	
	/* (non-Javadoc)
	 * @see RMI_BioAPI_AsteriskJava_Interface#RPC_FileRead(java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unused")
	@Override
	public void RPC_FileRead(String Service_UID, String srcFileName, String socket_ip, int socket_port, String remote_fileName)
			throws RemoteException {
		// TODO Auto-generated method stub
		Socket soc;
    	PrintWriter pw=null;
    	// BufferedReader br = null;
		
		File fileName = new File(srcFileName);
		BufferedReader brf=null;
    	try {
    		brf = new BufferedReader(new InputStreamReader(
    				new FileInputStream(fileName.getAbsolutePath())));
    	}catch ( IOException ioe ) {throw new RuntimeException(ioe);}
    	
    	try {
    		if ( brf == null )
    			throw new RuntimeException("Cannot read from closed file "
					                       + fileName.getAbsolutePath() + ".");
    		
     		try {
//        			System.out.println("Server address connected to is "+addr+" and port is "+port);
        			soc = new Socket(socket_ip, socket_port);
        			pw=new PrintWriter(soc.getOutputStream(), true);

            		pw.println("StartXfer"); //Signaling message to start xfer to the remote socket server     		
            		pw.println(remote_fileName); //Signaling message about remote file name

            		String line = brf.readLine();
            		int counter=0;

            		while ( line != null){
            			System.out.println(line);
            			pw.println(line);
            			counter++;
            			line = brf.readLine();
            		}

            		pw.println("Done"); //Signaling message to terminate the remote socket server

            		brf.close();
            		soc.close();
     		} catch (UnknownHostException e) {
        			System.err.println("Don't know about host.");
                   System.exit(1);
        			e.printStackTrace();
        		} catch (IOException e) {
        			System.err.println("Couldn't get I/O for the connection to server.");
        			System.exit(1);
        			e.printStackTrace();
        		}    		
    	}catch (Exception e) {throw new RuntimeException(e);}
	}  // method RPC_FileRead()


	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
	    if (args.length != 1)
	    {
	            System.out.println
	                ("Syntax - java RMI_BioAPI_AsteriskJava_Server_Package/RMI_BioAPI_AsteriskJava_Server_Impl host_port");
	            System.exit(1);
	    }
			
		
        // Create an instance of our service server ...
		
	    RMI_BioAPI_AsteriskJava_Server svr = new RMI_BioAPI_AsteriskJava_Server(Integer.parseInt(args[0]));
	    //
            System.out.println("RmiRegistry listens at port 2099 ");
            System.out.println("AsteriskJava BSP Server is ready to listen on " + args[0]);
 //            System.out.println(InetAddress.getLocalHost().getHostName());
	    Naming.bind("RMI_BioAPI_AsteriskJava", svr);
            System.out.println("BioAPI AsteriskJava RMI server starts ... ");
	}

}