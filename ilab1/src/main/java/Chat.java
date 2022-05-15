import java.util.*;
public class Chat
{
    ArrayList<String> chatlist=new ArrayList<String>();
    String chatmes=new String();
Channel reply = new Channel(null);
public Chat(User user,String mes)
{
    chatmes=(user.get()+":"+mes);
    chatlist.add(chatmes);
}

public ArrayList get()
{
    return chatlist;
}

public void addReply(User user,String mes)
{
    chatmes=("&nbsp;&nbsp;&nbsp;&nbsp;"+user.get()+":"+mes);
    chatlist.add(chatmes);
}

}
