package youngNo.payment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import youngNo.payment.Model.*;
import youngNo.payment.ModelForm.*;

import java.util.ArrayList;
import java.util.HashMap;
import youngNo.payment.Database.*;

@RestController
public class Controller {
	private SaveFakeDatabase fakeSave;
	private FakeDatabase fakeDatabase;
	
	public Controller() {
		fakeSave= new SaveFakeDatabase();
		fakeDatabase = fakeSave.loadDatabase();
	}
	
	private int getUserId(String token) {
		
		//fake
		return Integer.parseInt(token);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/")
	public String sayHello(@RequestHeader("Authorization") String user_id) {
		return user_id;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/wallet")
	public ResponseEntity<Wallet> getWallet(
			@RequestHeader("Authorization") String token
			){
		int user_id = this.getUserId(token);
		Wallet wallet = Wallet.findOne(user_id);
		return new ResponseEntity<Wallet>(wallet, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/wallet/addBalance/")
	public ResponseEntity<Wallet> addBalanceWallet(
			@RequestHeader("Authorization") String token,
			@RequestBody WalletForm walletFrom
			){
		int user_id = this.getUserId(token);
		Wallet updateWallet = Wallet.findOne(user_id);
		updateWallet.addBalance(walletFrom.getBalance());
		return new ResponseEntity<Wallet>(updateWallet, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/refunding/{index}")
	public ResponseEntity<Refunding> getRefunding(@PathVariable("index") String index){
		Refunding w = new Refunding("1", 1);
		return new ResponseEntity<Refunding>(w, HttpStatus.OK);
	}
	@RequestMapping(method = RequestMethod.POST, value = "/refunding/create/")
	public ResponseEntity<Refunding> postRefunding(@RequestBody Refunding refunding){
		return new ResponseEntity<Refunding>(refunding, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/paymentLog/{user_id}")
	public ResponseEntity<ArrayList<PaymentLog>> getPaymentLog(@PathVariable("user_id") int user_id){
		Wallet wallet = Wallet.findOne(user_id);
		ArrayList<PaymentLog> paymentLogs = PaymentLog.findAll(wallet.getId());
		return new ResponseEntity<ArrayList<PaymentLog>>(paymentLogs, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/receive")
	public ResponseEntity<ArrayList<Receive>> getReceiveAll(){
		ArrayList<Integer> order_id = new ArrayList<Integer>();
		order_id.add(1); order_id.add(4); order_id.add(5);
		ArrayList<Receive> receives = Receive.findAllByOrderId(order_id);
		return new ResponseEntity<ArrayList<Receive>>(receives, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/receive/{orderId}")
	public ResponseEntity<Receive> getReceiveByOrderId(@PathVariable("orderId") int orderId){
		Receive receive = Receive.findByOrderId(orderId);
		return new ResponseEntity<Receive>(receive, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/promotion/add")
	public ResponseEntity<Receive> addPromotion(@RequestBody PromotionForm promotionForm){
		Receive receive = Receive.findByOrderId(promotionForm.getOrder_id());
		//Promotion.addPromotion(promotionForm.getPromotion_id(), receive.getId());
		receive.addPromotion(promotionForm.getPromotion_id());
		receive = Receive.findByOrderId(promotionForm.getOrder_id());
		return new ResponseEntity<Receive>(receive, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/promotion/remove")
	public ResponseEntity<Receive> removePromotion(@RequestBody PromotionForm promotionForm){
		Receive receive = Receive.findByOrderId(promotionForm.getOrder_id());
		receive.removePromotion(promotionForm.getPromotion_id());
		receive = Receive.findByOrderId(promotionForm.getOrder_id());
		return new ResponseEntity<Receive>(receive, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/pay")
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
