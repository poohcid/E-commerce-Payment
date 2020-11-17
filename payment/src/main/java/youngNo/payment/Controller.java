package youngNo.payment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import youngNo.payment.Model.*;
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
	
	@RequestMapping(method = RequestMethod.GET, value = "/")
	public String sayHello() {
		return "Payment";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/wallet/{index}")
	public ResponseEntity<Wallet> getWallet(@PathVariable("index") String index){
		fakeDatabase = fakeSave.loadDatabase();
		Wallet w = fakeDatabase.getWallet(index);
		return new ResponseEntity<Wallet>(w, HttpStatus.OK);
	}
	@RequestMapping(method = RequestMethod.POST, value = "/wallet/create/")
	public ResponseEntity<Wallet> postWallet(@RequestBody Wallet wallet){
		fakeDatabase.addWallet(wallet);
		fakeSave.saveData(fakeDatabase);
		return new ResponseEntity<Wallet>(wallet, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/promotion/{index}")
	public ResponseEntity<Promotion> getPromotion(@PathVariable("index") String index){
		Promotion w = new Promotion(1, 1);
		return new ResponseEntity<Promotion>(w, HttpStatus.OK);
	}
	@RequestMapping(method = RequestMethod.POST, value = "/promotion/create/")
	public ResponseEntity<Promotion> postPromotion(@RequestBody Promotion promotion){
		return new ResponseEntity<Promotion>(promotion, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/receive/{index}")
	public ResponseEntity<Receive> getReceive(@PathVariable("index") String index){
		Receive w = new Receive("1", 1);
		return new ResponseEntity<Receive>(w, HttpStatus.OK);
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
		PaymentLog w = new PaymentLog("1", 1, 1);
		return new ResponseEntity<PaymentLog>(w, HttpStatus.OK);
	}
	@RequestMapping(method = RequestMethod.POST, value = "/paymentLog/create/")
	public ResponseEntity<PaymentLog> postPaymentLog(@RequestBody PaymentLog paymentLog){
		return new ResponseEntity<PaymentLog>(paymentLog, HttpStatus.OK);
	}
}
