package tr.com.cdtakip.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
	
	private static final SessionFactory session_factory;
	
	static{
		Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        ServiceRegistryBuilder serviceRegistryBuilder = new ServiceRegistryBuilder().applySettings(configuration.getProperties());
		session_factory= new Configuration().configure().buildSessionFactory(serviceRegistryBuilder.buildServiceRegistry());
		
	}

	// this method used to access singleton
	public static SessionFactory getSessionFactory(){
		return session_factory;
	}
}