package com.example.transitplanner;

import android.os.AsyncTask;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

public class QueryServer extends AsyncTask<String, Void, String>{
    @Override
    protected String doInBackground(String... urls) {
    	HttpClient httpclient = new DefaultHttpClient();
        try {
            HttpResponse response = httpclient.execute(new HttpGet(urls[0]));
            StatusLine statusLine = response.getStatusLine();
            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
		            ByteArrayOutputStream out = new ByteArrayOutputStream();
		            response.getEntity().writeTo(out);
		            String responseString = out.toString();
		            System.out.println(responseString);
		            out.close();
		            return responseString;
		        }
        	 else{
		            //Closes the connection.
		            response.getEntity().getContent().close();
		            throw new IOException(statusLine.getReasonPhrase());
		        }		    		        	
        }
        catch (IOException e) {
            Log.e("Tag", "Could not fetch response: " + e.getMessage());
        }
        return "";
    }
    
/*    @Override
    protected void onPostExecute(String result) {
    	System.out.println("I'm here..");
    }*/
}
