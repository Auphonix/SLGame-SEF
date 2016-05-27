package Game;



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


/**
 * Created by Danyon on 28/04/2016.
 */
public class Account {
	

    String username;
    String password;
    int index;

    public Account(String username, String password, int index){
        this.username = username;
        this.password = password;
        this.index = index;
    }

    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public int getIndex(){
        return index;
    }
    

    public static void findUser(String username, String password){
    	File file = new File("src\\Game\\loginList.txt");
    	boolean valid;
    	try{
    		Scanner scanner = new Scanner(file);
    		while (scanner.hasNextLine()){
    			
    			String line = scanner.nextLine();
    			if (line == username +", " + password ){
    				valid =true;
    			}
    		}
    	}
    	catch(FileNotFoundException e){
    		e.printStackTrace();
    	}
		System.out.println(username + ", " + password);
		return;
    		
    	
    }
    
    
    public static void addUser(String username, String password) throws IOException{
    	//int i = 0;
    	//PrintWriter out = new PrintWriter("src\\Game\\accountData.txt");
    	//if(i ==0){
    		//try(PrintWriter out = new PrintWriter("src\\Game\\accountData.txt") ){
    	//out.append(username + ", " + password + '\n');
    		
    		//}
    		    
    		//catch (IOException e) {
    			//System.out.println("exception occoured"+ e);
    		//}
    		//i++;
    	//}
    	//else{
    		//PrintWriter out.append(username + ", " + password + '\n');
    	BufferedWriter out = new BufferedWriter(new FileWriter("src\\Game\\accountData.txt"));
    	out.write(username + ", " + password);
    	out.newLine();
    	out.flush();
    	
    	
    	
  }
}
