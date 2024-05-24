package web_app_listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.app.utils.HibernateUtils;

@WebListener
public class HibernateSFManager implements ServletContextListener {

	public HibernateSFManager() {
		// TODO Auto-generated constructor stub
	}

	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("in ctx destroyed");
		HibernateUtils.getFactory().close();// SF : close , DBCP cleaned up !
	}

	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("in ctx inited");
		HibernateUtils.getFactory();// static init block : hib up n running
	}

}
