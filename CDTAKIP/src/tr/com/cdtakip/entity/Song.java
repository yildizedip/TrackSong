package tr.com.cdtakip.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="T_SONG")

public class Song  implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private String duration;
	private String state;
	
	public Song() {
	}
	
	public Song(String name, String duration, String state) {
		super();
		this.name = name;
		this.duration = duration;
		this.state = state;
	}
	
	@ManyToOne
	private Album ownerAlbum;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

	public Album getOwnerAlbum() {
		if(ownerAlbum==null) ownerAlbum=new Album();
		return ownerAlbum;
	}

	public void setOwnerAlbum(Album ownerAlbum) {
		this.ownerAlbum = ownerAlbum;
	}
	
	
	
	
}
