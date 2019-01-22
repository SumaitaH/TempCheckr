package com.amazonaws.codesamples;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.rds.auth.GetIamAuthTokenRequest;
import com.amazonaws.services.rds.auth.RdsIamAuthTokenGenerator;

public class GenerateRDSAuthToken {

    public static void main(String[] args) {

	    String region = "us-east-1a";
	    String hostname = "weatherapp.cozvmlu5fx03.us-east-1.rds.amazonaws.com";
	    String port = "3306";
	    String username = "admin";
	
	    System.out.println(generateAuthToken(region, hostname, port, username));
    }

    static String generateAuthToken(String region, String hostName, String port, String username) {

	    RdsIamAuthTokenGenerator generator = RdsIamAuthTokenGenerator.builder()
		    .credentials(new DefaultAWSCredentialsProviderChain())
		    .region(region)
		    .build();

	    String authToken = generator.getAuthToken(
		    GetIamAuthTokenRequest.builder()
		    .hostname(hostName)
		    .port(Integer.parseInt(port))
		    .userName(username)
		    .build());
	    
	    return authToken;
    }

}

