import java.util.*;
public class Channellist
{
LinkedHashMap<String, Channel> clist = new LinkedHashMap<String, Channel>();
public Channellist()
{
    Channel general=new Channel("general");
    clist.put(general.getName(),general);
}

public Channel getChannel(String cname)
{
    return clist.get(cname);

}

public void addChannel(Channel ch)
{
    clist.put(ch.getName(),ch);
}
//get all channel name
public ArrayList getCname()
{
    ArrayList<String> cname= new ArrayList<>();
    for (String key : clist.keySet()) {
		cname.add(key);
		}
    return cname;
}

}
