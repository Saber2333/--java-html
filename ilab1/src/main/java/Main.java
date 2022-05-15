import java.util.*;
import spark.*;
import spark.template.velocity.*;
import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
    Users userlist = new Users();
    Channellist cl=new Channellist();

//init redirect
    get("/", (req, res) ->{
    res.redirect("/userlist");
    return"redirect to userlist";
    });

//add or remove user
    get("/userlist", (req, res) ->{

//login
    String addeduser =req.queryParams("name");
    if(addeduser!=null)
    {
         if(!addeduser.equals("")||!addeduser.equals("$user"))
         {
            User user=new User(addeduser);
            userlist.addUser(user);
            req.session().attribute("user",user.get());//add or switch user
         }
    }
//

//logout
    String removeuser =req.queryParams("re");
    if(removeuser!=null)
    {
        if(!removeuser.equals(""))
            userlist.remove(removeuser);
            if(userlist.size()==0)//if there are no user in user list
            {
                req.session().removeAttribute(req.session().attribute(removeuser));
                req.session().attribute("user",null);
            }
            else
            {
                if(removeuser.equals(req.session().attribute("user")))//if try to remove current user
            {

                req.session().attribute("user",userlist.getnewUser());
            }

            }
    }
//

    Map<String, Object> map= new HashMap<>();
    map.put("user", req.session().attribute("user"));
    return new VelocityTemplateEngine().render(new ModelAndView(map, "index_ul.vm"));
    });

//create a user list
    get("/userlists", (req, res) ->{
    Map<String, Object> map= new HashMap<>();
    map.put("userlist", userlist.getUser());
    return new VelocityTemplateEngine().render(new ModelAndView(map, "index_uls.vm"));
    });


//add new channel
	get("/ch", (req, res) ->{
    String addedchannel =req.queryParams("channel");
    if(addedchannel!=null)
    {
         if(!addedchannel.equals("")||!addedchannel.equals("$channel"))
         {
            Channel c=new Channel(addedchannel);
            cl.addChannel(c);
         }
    }


     Map<String, Object> map= new HashMap<>();
     map.put("user", req.session().attribute("user"));
     return new VelocityTemplateEngine().render(new ModelAndView(map, "index_ch.vm"));
     });

//reload channel list
    get("/chs", (req, res) ->{
     Map<String, Object> map= new HashMap<>();
     map.put("channellist", cl.getCname());
     return new VelocityTemplateEngine().render(new ModelAndView(map, "index_chs.vm"));
     });

//access channel and add chat massage
	get("/ch/:channel", (req, res) ->{
     Channel channel= cl.getChannel(req.params(":channel"));//current channel
     User cuser=userlist.getUser(req.session().attribute("user"));//current user
     String cmes=req.queryParams("chat");//added chat massage
     String number=req.queryParams("num");//state number
     Chat newchat= new Chat(cuser,cmes);

     //get state number
     int num=0;
     if(number!=null)
     {
         if(!number.equals(""))
         {
         if(checkStrIsNum(number))
         num=Integer.parseInt(number);
         }
     }
    //check state
     if(num==0)
     {
    //add new chat massage
     if(cmes!= null)channel.addChat(newchat);
     }
    else
    {
    //add reply
     if(cmes!= null)
     {
         Chat schat=channel.getChat(num);
         if(schat!=null)
         schat.addReply(cuser,cmes);
     }
    }

     Map<String, Object> map= new HashMap<>();
     map.put("channel", channel.getName());
     map.put("user", cuser.get());
     return new VelocityTemplateEngine().render(new ModelAndView(map, "index_c.vm"));
     });

//reload chat message
	get("/ch/:channel/s", (req, res) ->{
     Channel channel= cl.getChannel(req.params(":channel"));
     ArrayList<Chat> current = channel.getChat();//get all chat message from this channel

     Map<String, Object> map= new HashMap<>();
     map.put("channel", channel.getName());
     map.put("chats", current);
     return new VelocityTemplateEngine().render(new ModelAndView(map, "index_cs.vm"));
     });

    }
//check string is number only
    public static boolean checkStrIsNum(String str) {
            for (int i = 0; i < str.length(); i++) {
                if (!Character.isDigit(str.charAt(i))) {
                    return false;
                }
            }
            return true;
        }

}
