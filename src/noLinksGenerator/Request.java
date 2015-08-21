package noLinksGenerator;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Request {

	public Request() {
		
	}
	
	public ArrayList<String> getRelatedDrugs(String geneSymbol) throws MalformedURLException, IOException {
		ArrayList<String> relatedDrugs = new ArrayList<String>();
		URLConnection connection = new URL("http://cassandra.kevindalleau.fr:8000/api/v1/interactions.json?genes="+geneSymbol).openConnection();
		InputStream response = connection.getInputStream();
		ObjectMapper mapper = new ObjectMapper();
		JsonFactory jsonFactory = mapper.getJsonFactory();
		JsonParser jp = jsonFactory.createJsonParser(response);
		JsonToken token;
		while ((token = jp.nextToken()) != null) {
		    switch (token) {
		        case START_OBJECT:
		            JsonNode node = jp.readValueAsTree();
		            JsonNode matchedNode = node.get("matchedTerms").get(0);
		            JsonNode interactionsNode;
		            if(matchedNode != null) {
		            	Iterator<JsonNode> iterator = matchedNode.get("interactions").iterator();
		            	while(iterator.hasNext()) {
				            String drugName = iterator.next().get("drugName").toString();
				            relatedDrugs.add(drugName);
		            	}
		            }
		            break;
		    }
		}
		return relatedDrugs;
	}
} 
