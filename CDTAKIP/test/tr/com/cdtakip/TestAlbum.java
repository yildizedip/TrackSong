package tr.com.cdtakip;

import java.util.ArrayList;

import org.hibernate.Session;
import org.junit.Test;

import tr.com.cdtakip.dao.HibernateUtil;
import tr.com.cdtakip.entity.Album;
import tr.com.cdtakip.entity.Signer;
import tr.com.cdtakip.entity.Song;



public class TestAlbum {

	@Test
	public void test() {
		
		
		Session session= HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		
		Song song= new Song("Nisan Yaðmuru1","10","A");
		Song song1= new Song("Nisan Yaðmuru2","10","A");
		Song song2= new Song("Nisan Yaðmuru3","10","A");
		Song song3= new Song("Nisan Yaðmuru4","10","A");
		
		ArrayList<Song> songs= new ArrayList<Song>();
		songs.add(song1);
		songs.add(song2);
		songs.add(song3);
		
		
		Album album= new Album();
		album.setName("ALbum 1");
		album.setSongList(songs);
		album.setState("A");
		album.setYear("2011");
		
		song.setOwnerAlbum(album);
		song1.setOwnerAlbum(album);
		song2.setOwnerAlbum(album);
		song3.setOwnerAlbum(album);
	
		
		
		Album album2= new Album();
		album2.setName("ALbum 2");
		album2.setSongList(songs);
		album2.setState("A");
		album2.setYear("2012");
		
		song.setOwnerAlbum(album2);
		song1.setOwnerAlbum(album2);
		song2.setOwnerAlbum(album2);
		song3.setOwnerAlbum(album2);
	
		
		Signer signer= new Signer();
		signer.setName("Ferdi");
		signer.setSurname("Tayfur");
		signer.setState("A");
		
		album.setOwnerSigner(signer);
		album2.setOwnerSigner(signer);
		
		ArrayList<Album> albums= new ArrayList<Album>();
		albums.add(album);
		albums.add(album2);
		
		signer.setAlbumList(albums);
	
		
		session.persist(signer);
	
		
		
		session.getTransaction().commit();	
		session.flush();
		session.close();
		
	}

}
