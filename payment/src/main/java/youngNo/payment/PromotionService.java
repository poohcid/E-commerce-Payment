package youngNo.payment;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import youngNo.payment.Model.*;

public class PromotionService {
	private final String url="localhost";
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
	public double getDiscount(Receive receive) {
		//fake
		return receive.getPromotions().size()*100;
	}
}
