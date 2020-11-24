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

import youngNo.payment.Model.*;

public class PromotionService {
	private final String url="https://2139426969a7.ngrok.io/";
	private HttpHeaders headers;
	private HttpEntity<String> entityHeader;
	private RestTemplate restTemplate;
	
	public PromotionService() {
		headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		entityHeader = new HttpEntity<String>(headers);
		restTemplate = new RestTemplate();
	}
	
	//************************
	@SuppressWarnings("finally")
	public double getDiscount(Receive receive) {
		double totalPrice = receive.getTaltalPrice();
		double totalDiscount = 0.0;
		ObjectMapper mapper;
		JsonNode actualObj;
		String responseText;
		
		try {
			for (Promotion promotion: receive.getPromotions()) {
				responseText = restTemplate.exchange(
						url+"promotion/apply/"+promotion.getPromotion_id(), HttpMethod.GET, entityHeader, String.class).getBody();
				mapper = new ObjectMapper();
				actualObj = mapper.readTree(responseText);
				System.out.println(responseText);
				
				if (actualObj.get("method").asText().equals("percent")) {
					totalDiscount += totalPrice*(actualObj.get("amount").asDouble()/100);
				}
				else if (actualObj.get("method").asText().equals("direct")) {
					totalDiscount += actualObj.get("amount").asDouble();
				}
			}
			return totalDiscount;
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			return totalDiscount;
			//return receive.getPromotions().size()*100;
		}
	}
}
