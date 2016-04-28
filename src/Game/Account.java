package Game;

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
}
