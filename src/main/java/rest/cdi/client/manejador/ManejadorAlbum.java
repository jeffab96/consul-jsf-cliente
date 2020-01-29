package rest.cdi.client.manejador;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import rest.cdi.client.consumidor.ClienteAlbumHttp;
import rest.cdi.client.entidad.Album;

@Named("manejadorAlbum")
@SessionScoped
public class ManejadorAlbum implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<Album> albumList;
	@Inject private ClienteAlbumHttp cliente;
	@Inject private Album album;
	private int idAlbum;
	
	
	public void buscarAlbum() {
		System.out.println("prueba album"+cliente.buscarId(idAlbum) );
		album=cliente.buscarId(idAlbum);
	}
	public Album getAlbum() {
		return album;
	}
	public void setAlbum(Album album) {
		this.album = album;
	}
	public List<Album> getAlbumList() {
		return cliente.listar();
	}
	public void setAlbumList(List<Album> albumList) {
		this.albumList = albumList;
	}
	public int getIdAlbum() {
		return idAlbum;
	}
	public void setIdAlbum(int idAlbum) {
		this.idAlbum = idAlbum;
	}
}

