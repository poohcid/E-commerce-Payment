package youngNo.payment;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import youngNo.payment.Model.*;
import youngNo.payment.ModelForm.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import youngNo.payment.Database.*;

@RestController
public class Controller {
	private SaveFakeDatabase fakeSave;
	private FakeDatabase fakeDatabase;
	private OrderService orderService;
	
	public Controller() {
		fakeSave= new SaveFakeDatabase();
		fakeDatabase = fakeSave.loadDatabase();
		this.orderService = new OrderService();
	}
	
	private int getUserId(String token) {
		
		//fake
		return Integer.parseInt(token);
	}
	
	private ArrayList<Integer> getOrdersIdByUser(int user_id){
		//fake
		ArrayList<Integer> orders = new ArrayList<Integer>();
		/*
		if (user_id == 1) {
			orders.add(1); orders.add(2); orders.add(3); 
		}
		if (user_id == 2) {
			orders.add(11); orders.add(21); orders.add(13);
			orders.add(5); orders.add(22); orders.add(7);
		}
		if (user_id == 3) {
			orders.add(10); 
		}
		if (user_id == 4) {
			orders.add(1); orders.add(28); 
		}
		*/
		
		JsonNode jNode = orderService.getOrdersByUserId(user_id);
		for (JsonNode item: jNode) {
			orders.add(item.get("id").asInt());
		}
		
		return orders;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/")
	@CrossOrigin(origins = "http://localhost:3000")
	public String sayHello(@RequestHeader("Authorization") String user_id) {
		return user_id;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/wallet")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Wallet> getWallet(
			@RequestHeader("Authorization") String token
			){
		int user_id = this.getUserId(token);
		Wallet wallet = Wallet.findOne(user_id);
		return new ResponseEntity<Wallet>(wallet, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/wallet/addBalance/")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Wallet> addBalanceWallet(
			@RequestHeader("Authorization") String token,
			@RequestBody WalletForm walletFrom
			){
		int user_id = this.getUserId(token);
		Wallet updateWallet = Wallet.findOne(user_id);
		updateWallet.addBalance(walletFrom.getBalance());
		return new ResponseEntity<Wallet>(updateWallet, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/refunding")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<ArrayList<Refunding>> getRefunding(
			@RequestHeader("Authorization") String token
			){
		int user_id = this.getUserId(token);
		ArrayList<Integer> orders_id = this.getOrdersIdByUser(user_id);
		ArrayList<Receive> receives = Receive.findAllByOrderId(orders_id);
		ArrayList<Refunding> refunding = Refunding.getRefundingByReceive(receives);
		return new ResponseEntity<ArrayList<Refunding>>(refunding, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/refunding/create/")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Refunding> postRefunding(@RequestBody PaymentForm paymentForm){
		Receive receive = Receive.findByOrderId(paymentForm.getOrder_id());
		Refunding refunding = new Refunding(receive);
		refunding.save();
		return new ResponseEntity<Refunding>(refunding, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/paymentLog")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<ArrayList<PaymentLog>> getPaymentLog(
			@RequestHeader("Authorization") String token
			){
		int user_id =  this.getUserId(token);
		Wallet wallet = Wallet.findOne(user_id);
		ArrayList<PaymentLog> paymentLogs = PaymentLog.findAll(wallet.getId());
		return new ResponseEntity<ArrayList<PaymentLog>>(paymentLogs, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/receive")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<ArrayList<Receive>> getReceiveAll(
			@RequestHeader("Authorization") String token
			){
		int user_id = this.getUserId(token);
		ArrayList<Integer> order_id = this.getOrdersIdByUser(user_id);
		ArrayList<Receive> receives = Receive.findAllByOrderId(order_id);
		return new ResponseEntity<ArrayList<Receive>>(receives, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/receive/{orderId}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Receive> getReceiveByOrderId(@PathVariable("orderId") int orderId){
		Receive receive = Receive.findByOrderId(orderId);
		return new ResponseEntity<Receive>(receive, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/promotion/add")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Receive> addPromotion(@RequestBody PromotionForm promotionForm){
		Receive receive = Receive.findByOrderId(promotionForm.getOrder_id());
		//Promotion.addPromotion(promotionForm.getPromotion_id(), receive.getId());
		receive.addPromotion(promotionForm.getPromotion_id());
		receive = Receive.findByOrderId(promotionForm.getOrder_id());
		return new ResponseEntity<Receive>(receive, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/promotion/remove")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Receive> removePromotion(@RequestBody PromotionForm promotionForm){
		Receive receive = Receive.findByOrderId(promotionForm.getOrder_id());
		receive.removePromotion(promotionForm.getPromotion_id());
		receive = Receive.findByOrderId(promotionForm.getOrder_id());
		return new ResponseEntity<Receive>(receive, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/pay")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Receive> paymentOrder(
			@RequestHeader("Authorization") String token,
			@RequestBody PaymentForm paymentForm
			){
		int user_id = this.getUserId(token);
		Wallet wallet = Wallet.findOne(user_id);
		Receive receive = Receive.findByOrderId(paymentForm.getOrder_id());
		receive.confirm(wallet);
		return new ResponseEntity<Receive>(receive, HttpStatus.OK);
	}
}
