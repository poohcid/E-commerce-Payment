package youngNo.payment;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OrderService {
	private final String url="https://ordermodule.herokuapp.com/";
	private HttpHeaders headers;
	private HttpEntity <String> entityHeader;
	private RestTemplate restTemplate;
	
	public OrderService() {
		 headers = new HttpHeaders();
		 headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		 entityHeader = new HttpEntity<String>(headers);
		 restTemplate = new RestTemplate();
	}
	
	@SuppressWarnings("finally")
	private JsonNode getLoad(String path) {
		String responseText = restTemplate.exchange(
				url+path, HttpMethod.GET, entityHeader, String.class).getBody();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode actualObj = null;
		try {
			actualObj = mapper.readTree(responseText);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			return actualObj;
		}
	}
	
	public JsonNode getOrdersByUserId(int user_id) {
		JsonNode orders = this.getLoad("getUserOrder/"+user_id);
		return orders;
	}
	
	public JsonNode getOrderDetails(int order_id) {
		JsonNode order = this.getLoad("getOrderDetails/"+order_id);
		return order;
	}
}
