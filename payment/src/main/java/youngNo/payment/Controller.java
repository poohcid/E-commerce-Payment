package youngNo.payment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import youngNo.payment.Model.*;
import java.util.HashMap;

@RestController
public class Controller {
	@RequestMapping(method = RequestMethod.GET, value = "/")
	public String sayHello() {
		return "Payment";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/wallet/{index}")
	public ResponseEntity<Wallet> getWallet(@PathVariable("index") String index){
		Wallet w = new Wallet(20000.99999, 1);
		return new ResponseEntity<Wallet>(w, HttpStatus.OK);
	}
}
