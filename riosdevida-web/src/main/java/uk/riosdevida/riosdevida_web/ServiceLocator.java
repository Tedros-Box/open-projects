package uk.riosdevida.riosdevida_web;

import java.text.MessageFormat;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.tedros.util.TResourceUtil;

public class ServiceLocator implements AutoCloseable{
	
	private static ServiceLocator locator;
	
	private InitialContext ctx;
	
	private static String URL = "http://{0}:8081/tomee/ejb";
	private static String IP = "localhost";
	
	private static Properties getProp(){
		
		  Properties PROP = TResourceUtil.getPropertiesFromConfFolder("remote-config.properties");
		  if(PROP!=null){ 
			URL = PROP.getProperty("url"); IP =
			PROP.getProperty("server_ip"); 
		  }
		 
		
		String serviceURL = MessageFormat.format(URL, IP);
		
		Properties P = new Properties();
		P.put("java.naming.factory.initial", "org.apache.openejb.client.RemoteInitialContextFactory");
		P.put("java.naming.provider.url", serviceURL); 
		return P;
	}
	
	private ServiceLocator(){
	}
	
	public static ServiceLocator getInstance(){
		if(locator ==null)
			locator = new ServiceLocator();
		return locator;
	}
	
	@SuppressWarnings("unchecked")
	public <E> E lookup(String jndi) throws NamingException{
		ctx = new InitialContext(getProp());
		return (E) ctx.lookup(jndi);
	}
	
	public void close() throws Exception{
		ctx.close();
	}

}

