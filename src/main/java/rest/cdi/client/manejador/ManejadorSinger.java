package rest.cdi.client.manejador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import rest.cdi.client.consumidor.ClienteSingerHttp;
import rest.cdi.client.entidad.Singer;
import rest.cdi.client.impl.ClienteSingerHttpImpl;

@Named("manejadorSinger")
@SessionScoped
public class ManejadorSinger implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<Singer> singerList;
	@Inject private ClienteSingerHttp cliente;
	@Inject private Singer singer;
	private int idSinger;
	
	
	
	public void buscarSinger() {
		singer=cliente.buscarId(idSinger);
	}
	
	public Singer getSinger() {
		return singer;
	}
	public void setSinger(Singer singer) {
		this.singer = singer;
	}
	public List<Singer> getSingerList() {
		return cliente.listar();
	}
	public void setSingerList(List<Singer> singerList) {
		this.singerList = singerList;
	}
	public int getIdSinger() {
		return idSinger;
	}
	public void setIdSinger(int idSinger) {
		this.idSinger = idSinger;
	}

	


}
