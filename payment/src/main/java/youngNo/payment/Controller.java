package youngNo.payment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	private WalletDB walletDB;
	private ReceiveDB receiveDB;
	
	public Controller() {
		fakeSave= new SaveFakeDatabase();
		fakeDatabase = fakeSave.loadDatabase();
		walletDB = new WalletDB();
		receiveDB = new ReceiveDB();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/")
	public String sayHello() {
		return "Payment";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/wallet/{index}")
	public ResponseEntity<Wallet> getWallet(@PathVariable("index") int userId){
		Wallet wallet = walletDB.findOne(userId);
		return new ResponseEntity<Wallet>(wallet, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/wallet/addBalance/")
	public ResponseEntity<Wallet> addBalanceWallet(@RequestBody Wallet wallet){
		Wallet updateWallet = walletDB.findOne(wallet.getUser_id());
		updateWallet.addBalance(wallet.getBalance());
		updateWallet.save();
		return new ResponseEntity<Wallet>(updateWallet, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/wallet/create/")
	public ResponseEntity<Wallet> postWallet(@RequestBody Wallet wallet){
		fakeDatabase.addWallet(wallet);
		fakeSave.saveData(fakeDatabase);
		return new ResponseEntity<Wallet>(wallet, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/promotion/create/")
	public ResponseEntity<Promotion> postPromotion(@RequestBody Promotion promotion){
		return new ResponseEntity<Promotion>(promotion, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/receive/create/")
	public ResponseEntity<Receive> postReceive(@RequestBody Receive receive){
		return new ResponseEntity<Receive>(receive, HttpStatus.OK);
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
	
	@RequestMapping(method = RequestMethod.GET, value = "/paymentLog/{index}")
	public ResponseEntity<PaymentLog> getPaymentLog(@PathVariable("index") String index){
		PaymentLog w = new PaymentLog(1, "1", 1, 1);
		return new ResponseEntity<PaymentLog>(w, HttpStatus.OK);
	}
	@RequestMapping(method = RequestMethod.POST, value = "/paymentLog/create/")
	public ResponseEntity<PaymentLog> postPaymentLog(@RequestBody PaymentLog paymentLog){
		return new ResponseEntity<PaymentLog>(paymentLog, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/receive")
	public ResponseEntity<HashMap<Integer, Receive>> getReceiveAll(){
		HashMap<Integer, Receive> receives = receiveDB.findAll();
		ArrayList<Integer> order_id = new ArrayList<Integer>();
		order_id.add(1); order_id.add(4); order_id.add(4);
		System.out.println(order_id);
		return new ResponseEntity<HashMap<Integer, Receive>>(receives, HttpStatus.OK);
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
	public ResponseEntity<Receive> paymentOrder(@RequestBody PaymentForm paymentForm){
		Receive receive = Receive.findByOrderId(paymentForm.getOrder_id());
		receive.confirm();
		return new ResponseEntity<Receive>(receive, HttpStatus.OK);
	}
}
