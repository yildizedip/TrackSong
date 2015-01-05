package tr.com.cdtakip.dao;

import java.io.Serializable;

import org.hibernate.Session;




public abstract class DaoBase implements Serializable{
	
	Session session;
	
	public Session openSession(){
		
		try {			
			session= (Session) HibernateUtil.getSessionFactory().openSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return session;
	}
	
	public void closeSession(){
		
		if(session!=null){
		session.close();
		session=null;
		}
	}
	
}
