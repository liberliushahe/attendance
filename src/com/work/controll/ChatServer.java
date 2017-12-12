package com.work.controll;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

/*
 * 此方法用于长时间监听web连接请求
 * websocket 服务器端
 * author:jijiiuxue
 * date 2017-9-9
 * version :v0.0.0
 * desc :实现实时通讯
 */
@ServerEndpoint("/server")
public class ChatServer {
	//记录当前连接数
	private static int onlinecount=0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
	private static CopyOnWriteArraySet<ChatServer> websocketset=new CopyOnWriteArraySet<ChatServer>();
	//与某个客户端的连接会话，需要通过它来给客户端发送数据
	private Session session;
	//获取连接
	@OnOpen
    public void OnOpen(Session session){
    	this.session=session;
    	websocketset.add(this);
    	//新连接加入 数据增1
    	addOnlineCount();
    	System.out.println("有新人加入,welcome 当前在线总人数为"+getOnlineCount());
    }
    //连接关闭
	@OnClose
	public void Close(){
		websocketset.remove(this);
		subOnlineCount();
		System.out.println("一人处于离线状态,当前在线总人数为"+getOnlineCount());
	}
	//获取客户端消息
	@OnMessage
	public void OnMessage(String message,Session session){
		System.out.println("客户端发送消息"+message);
		//群发消息
		for(ChatServer item: websocketset){
			try {
				item.sendMessage(message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				continue;
			}
		}
		//开始解析html标签获取数据
		//Document doc=Jsoup.parse(message);
		//Elements s= doc.getElementsByClass("msg own");
		//String content=s.text();
		//结束解析html标签
//		if(content.contains("@小创")){
//			System.out.println(message);
//			System.out.println(message.substring(message.indexOf("创")).trim());
//			message=GetMessageFromInterface.getMessageTuLing(content);
//			message="rebot"+message;
//			//群发消息
//			for(ChatServer item: websocketset){
//				try {
//					item.sendMessage(message);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//					continue;
//				}
//			}
//		}

	}
	//发生错误
	 @OnError
	    public void onError(Session session, Throwable error){
	        System.out.println("发生错误");
	        error.printStackTrace();
	    }
	//服务器发送消息
	/**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException{
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }
	
   //获取连接数
	public static synchronized int getOnlineCount() {
        return onlinecount;
    }
  //连接数加1
    public static synchronized void addOnlineCount() {
        ChatServer.onlinecount++;
    }
  //连接数减1
    public static synchronized void subOnlineCount() {
        ChatServer.onlinecount--;
    }
}
