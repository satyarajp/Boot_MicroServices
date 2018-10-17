package com.apalya.mmc.scheduleIngest.utilities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.*;

public class RestUtililty {
	
	static Logger log = LoggerFactory.getLogger(RestUtililty.class);
	
	public static void sendPost(String url, List<NameValuePair> postParams) 
	        throws Exception {

		log.info("\nSending 'POST' request to URL : " + url);
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);

		// add header
		post.setHeader("Content-Type", "application/x-www-form-urlencoded");

		post.setEntity(new UrlEncodedFormEntity(postParams));

		HttpResponse response = client.execute(post);

		int responseCode = response.getStatusLine().getStatusCode();

		
		log.info("Post parameters : " + postParams);
		log.info("Response Code : " + responseCode);

		BufferedReader rd = new BufferedReader(
	                new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		// System.out.println(result.toString());

	  }


}
