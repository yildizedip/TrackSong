package tr.com.cdtakip.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.event.FlowEvent;
import org.primefaces.event.RowEditEvent;

import tr.com.cdtakip.dao.GenelDao;
import tr.com.cdtakip.entity.Album;
import tr.com.cdtakip.entity.Signer;
import tr.com.cdtakip.entity.Song;

@ManagedBean
@SessionScoped
public class GeneralAddBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean skip;

	private Album album;
	private Signer signer;
	private Song song;

	private List<Signer> signerList;
	private List<Album> albumList;
	private List<Album> signerAlbumList;
	private List<Song> songList;
	private List<Song> albumSongList;

	GenelDao dao = new GenelDao();

	public GeneralAddBean() {
	}

	@PostConstruct
	public void init() {
		getSignerListesi();

	}

	private void getSignerListesi() {
		GenelDao dao = new GenelDao();
		signerList = dao.getSignerList();
		albumList = dao.getAlbumList();
		songList = dao.getSongList();
		System.out.println("signer list :" + signerList.size());
		System.out.println("album list :" + albumList.size());
		System.out.println("song list :" + songList.size());

	}

	public String onFlowProcess(FlowEvent event) {
		if (skip) {
			skip = false;
			return "confirm";
		} else {
			return event.getNewStep();
		}
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public Album getAlbum() {
		if (album == null)
			album = new Album();
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public Signer getSigner() {
		if (signer == null)
			signer = new Signer();
		return signer;
	}

	public void setSigner(Signer signer) {
		this.signer = signer;
	}

	public Song getSong() {
		if (song == null)
			song = new Song();
		return song;
	}

	public void setSong(Song song) {
		this.song = song;
	}

	public String addSigner() {

		signer.setState("A");
		dao.addSigner(signer);
		signerList = dao.getSignerList();
		return "add.jsf";
	}

	public String addAlbum() {

		album.setState("A");
		Album album2 = dao.addAlbum(album);
		if (album2 != null) {
			setPageMassage("Ekleme Baþarýlý");
			albumList = dao.getAlbumList();
		} else {
			setPageMassage("hata oluþtu");
		}

		return "add";
	}

	public String addSong() {

		song.setState("A");

		Song song2 = dao.addSong(song);

		if (song2 != null) {
			setPageMassage("Ýþlem Baþarýlý");
			songList = dao.getSongList();
		} else
			setPageMassage("Hata oluþtu.");

		return "add";
	}

	public void setSignerList(List<Signer> signerList) {
		this.signerList = signerList;
	}

	public List<Signer> getSignerList() {
		return signerList;
	}

	public void onRowEdit(RowEditEvent event) {

		Signer signer = (Signer) event.getObject();
		signer = dao.updateSigner(signer);

		if (signer == null)
			setPageMassage("Hata oluþtu.");
		if (signer == null)
			setPageMassage("Ýþlem Baþarý ile gerçekleþti.");
	}

	public String deleteSigner(Signer signer) {

		int result = dao.deleteSigner(signer);
		String resMes;
		if (result == -1) {
			resMes = "Hata Oluþtu.";
		} else {
			resMes = "Ýþlem Baþarý ile gerçekleþti.";
			signerList = dao.getSignerList();

		}

		setPageMassage(resMes);
		return "add";
	}

	public String deleteAlbum(Album album) {

		int result = dao.deleteAlbum(album);
		albumList = dao.getAlbumList();

		String resMes = "";
		if (result != -1) {
			resMes = "Silme iþlemi Baþarýlý";

		} else
			resMes = "Ýþlemde Sorun oluþtu.";
		setPageMassage(resMes);
		return "add";
	}

	public String deleteSong(Song song) {

		int result = dao.deleteSong(song);
		songList = dao.getSongList();

		String resMes = "";
		if (result != -1) {
			resMes = "Silme iþlemi Baþarýlý";

		} else
			resMes = "Ýþlemde Sorun oluþtu.";
		setPageMassage(resMes);
		return "add";
	}

	public void onRowCancel(RowEditEvent event) {

		Signer job = (Signer) event.getObject();
		System.out.println(job);
		setPageMassage("Vaçgeçildi");
	}

	public List<Album> getAlbumList() {
		return albumList;
	}

	public void setAlbumList(List<Album> albumList) {
		this.albumList = albumList;
	}

	public List<Song> getSongList() {
		return songList;
	}

	public void setSongList(List<Song> songList) {
		this.songList = songList;
	}

	public void valueChanged() {
		Long signerId=new Long(album.getOwnerSigner().getId());

		signerAlbumList = dao.getAlbumList(signerId);
		if (signerAlbumList == null) {
			setPageMassage("Veri tabaný iþleminde hata oluþtu.");
		}

		System.out.println("dd");
	}

	public List<Song> getAlbumSongList() {
		return albumSongList;
	}

	public void setAlbumSongList(List<Song> albumSongList) {
		this.albumSongList = albumSongList;
	}

	public List<Album> getSignerAlbumList() {
		return signerAlbumList;
	}

	public void setSignerAlbumList(List<Album> signerAlbumList) {
		this.signerAlbumList = signerAlbumList;
	}

	private void setPageMassage(String message) {

		FacesMessage msg = new FacesMessage(message, "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}
