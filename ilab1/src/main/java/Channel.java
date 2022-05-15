import java.util.*;
public class Channel
{
LinkedHashMap<Integer, Chat> chatmes = new LinkedHashMap<Integer, Chat>();//chat message
int lc=0;//line counter
String cname=new String();//channel name
public Channel(String cname)
{
    this.cname=cname;
}

public void addChat(Chat chat)
{
    lc++;
    chatmes.put(lc,chat);
}

public String getName()
{
    return cname;
}
//get all chat message
public ArrayList getChat()
{
    ArrayList<Chat> mes= new ArrayList<>();
    for (Chat v : chatmes.values()) {
		mes.add(v);
		}
    return mes;
}
//get one chat message at that line
public Chat getChat(int num)
{
    return chatmes.get(num);
}

}
