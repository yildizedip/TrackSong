package tr.com.cdtakip.dao;

import java.util.List;

import org.hibernate.Query;

import tr.com.cdtakip.entity.Album;
import tr.com.cdtakip.entity.Signer;
import tr.com.cdtakip.entity.Song;

public class GenelDao extends DaoBase {

	public Signer addSigner(Signer signer){		

		try {
			openSession();
			session.beginTransaction();
					
			session.save(signer);
					
			session.getTransaction().commit();

		} catch (Exception e) {

			e.printStackTrace();
			
			return null;

		} finally {
			closeSession();
		}

		return signer;
		
	}
	public Album addAlbum(Album album){		
		
		try {
			openSession();
			session.beginTransaction();
			
			session.save(album);
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return null;
			
		} finally {
			closeSession();
		}
		
		return album;
		
	}
	public Song addSong(Song song){		
		
		try {
			openSession();
			session.beginTransaction();
			
			session.save(song);
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return null;
			
		} finally {
			closeSession();
		}
		
		return song;
		
	}
	public int deleteSigner(Signer signer){		
		
		try {
			openSession();
			session.beginTransaction();
			
			session.delete(signer);
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return -1;
			
		} finally {
			closeSession();
		}
		
		return 1;
	}
	
	
	public int deleteAlbum(Album album){		
		
		try {
			openSession();
			session.beginTransaction();
			
			session.delete(album);
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return -1;
			
		} finally {
			closeSession();
		}
		
		return 1;
	}
	
	
	public int deleteSong(Song song){		
		
		try {
			openSession();
			session.beginTransaction();
			
			session.delete(song);
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return -1;
			
		} finally {
			closeSession();
		}
		
		return 1;
	}
	
	
	public Signer updateSigner(Signer signer){		
		
		try {
			openSession();
			session.beginTransaction();
			
			session.update(signer);
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return null;
			
		} finally {
			closeSession();
		}
		
		return signer;
	}
	
	public List<Signer> getSignerList(){


		List<Signer> list=null;

		try {
			openSession();
			session.beginTransaction();
			
			String queryString="from Signer ss where ss.state='A' ";
			
			
			Query query= session.createQuery(queryString);
		
			list= query.list();
			
			session.getTransaction().commit();

		} catch (Exception e) {

			e.printStackTrace();
			return null;

		} finally {
			closeSession();
		}

		return list;
	
	}
	public List<Album> getAlbumList(){


		List<Album> list=null;

		try {
			openSession();
			session.beginTransaction();
			
			String queryString="from Album ss where ss.state='A' ";
			
			
			Query query= session.createQuery(queryString);
		
			list= query.list();
			
			session.getTransaction().commit();

		} catch (Exception e) {

			e.printStackTrace();
			return null;

		} finally {
			closeSession();
		}

		return list;
	
	}
	public List<Album> getAlbumList(Long signerId){
		
		
		List<Album> list=null;
		
		try {
			openSession();
			session.beginTransaction();
			
			String queryString="from Album where state='A' and ownerSigner.id=? ";
			
			
			
			Query query= session.createQuery(queryString);
			query.setLong(0, signerId.longValue());
			
			list= query.list();
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
			
		} finally {
			closeSession();
		}
		
		return list;
		
	}
	public List<Song> getSongList(){
		
		
		List<Song> list=null;
		
		try {
			openSession();
			session.beginTransaction();
			
			String queryString="from Song ss where ss.state='A' ";
			
			
			Query query= session.createQuery(queryString);
			
			list= query.list();
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
			
		} finally {
			closeSession();
		}
		
		return list;
		
	}
}
