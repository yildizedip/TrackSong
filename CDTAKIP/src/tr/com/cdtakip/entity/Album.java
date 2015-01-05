package tr.com.cdtakip.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="T_ALBUM")
public class Album implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String name;
	private String year;
	
	@ManyToOne
	private Signer ownerSigner;
		
	@OneToMany(mappedBy="ownerAlbum", fetch= FetchType.EAGER, cascade=CascadeType.PERSIST)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Song> songList;
	
	private String state;
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

	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public Signer getOwnerSigner() {
		if(ownerSigner==null) ownerSigner= new Signer();
		return ownerSigner;
	}
	public void setOwnerSigner(Signer ownerSigner) {
		this.ownerSigner = ownerSigner;
	}
	public List<Song> getSongList() {
		return songList;
	}
	public void setSongList(List<Song> songList) {
		this.songList = songList;
	}
	

}
