package youngNo.payment.Database;

import java.io.Serializable;
import java.util.HashMap;

import youngNo.payment.Model.*;

public class FakeDatabase implements Serializable{
	private HashMap<String, Wallet> wallets;
	private HashMap<String, Promotion> promotions;
	private HashMap<String, PaymentLog> paymentlogs;
	private HashMap<String, Receive> receives;
	private HashMap<String, HashMap> database;
	
	public FakeDatabase() {
		wallets = new HashMap<String, Wallet>();
		promotions = new HashMap<String, Promotion>();
		paymentlogs = new HashMap<String, PaymentLog>();
		receives = new HashMap<String, Receive>();
		database = new HashMap<String, HashMap>();
		database.put("Wallets", wallets);
		database.put("Promotion", promotions);
		database.put("PaymentLog", paymentlogs);
		database.put("Receive", receives);
	}
	
	public void addWallet(Wallet w) {
		database.get("Wallets").put(w.getIdWallet(), w);
	}
	public Wallet getWallet(String id) {
		return (Wallet) database.get("Wallets").get(id);
	}
	

}
