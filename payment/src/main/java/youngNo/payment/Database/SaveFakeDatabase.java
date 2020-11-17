package youngNo.payment.Database;


import java.io.*;

public class SaveFakeDatabase {

	
	public static FakeDatabase loadDatabase() {
		FakeDatabase database = new FakeDatabase();
		File file = new File("database.dat");
		 if(file.exists()){
	            try{
	                FileInputStream fin = new FileInputStream("database.dat");
	                ObjectInputStream oin = new ObjectInputStream(fin);
	                database = (FakeDatabase)oin.readObject();
	                oin.close();
	                fin.close();
	                return database;
	            }
	            catch(IOException | ClassNotFoundException e){}
	        }
	        else{
	            return database;
	            
	        }
	        return database;
	}
	
	public static void saveData(FakeDatabase database) {
		 try{
	            FileOutputStream fot = new FileOutputStream("save.dat");
	            ObjectOutputStream oot = new ObjectOutputStream(fot);
	            oot.writeObject(database);
	            oot.close();
	            fot.close();
	            
	        }
	        catch(IOException x){}
	}
}
