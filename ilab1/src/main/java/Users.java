import java.util.*;
public class Users
{
LinkedHashMap<String,User> userlist = new LinkedHashMap<String,User>();
public void addUser(User user)
{
    if(!userlist.containsValue(user))
    userlist.put(user.get(),user);
}
//get this user
public User getUser(String name)
{
    return userlist.get(name);
}

public void remove(String n)
{
    userlist.remove(n);
}
public int size()
{
    return userlist.size();
}
//get a new user name in the rest of user list
public String getnewUser()
{
    String key=new String();
    for(String keys: userlist.keySet())
        {
            if(keys!=null)
            {
            if(!keys.equals(""))
            key=keys;
            }
        }
    return key;
}
//get all user
public ArrayList getUser()
{
    ArrayList<String> user= new ArrayList<>();
    for (String key : userlist.keySet()) {
         if(!key.equals(""))user.add(key);
    }
    return user;
}

}
