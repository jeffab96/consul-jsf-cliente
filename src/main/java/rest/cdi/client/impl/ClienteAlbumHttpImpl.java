package rest.cdi.client.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.ClientBuilder;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.agent.model.Service;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import rest.cdi.client.consumidor.ClienteAlbumHttp;
import rest.cdi.client.entidad.Album;
import rest.cdi.client.entidad.Singer;

@ApplicationScoped
public class ClienteAlbumHttpImpl implements ClienteAlbumHttp {

	public static final String NAME = "album-conf";

	ConsulClient client = new ConsulClient("127.0.0.1");

	Response<Map<String, Service>> ss = client.getAgentServices();

	Map<String, Service> services = ss.getValue();

	List<Service> lista = services.values().stream().filter(s -> NAME.equals(s.getService()))
			.collect(Collectors.toList());

	// final static String ALBUM_URL =
	// "http://localhost:8080/examen-masache-c1/servAlbum";
	CloseableHttpClient httpClient = HttpClients.createDefault();

	@Override
	public List<Album> listar() {

		if (lista.isEmpty()) {
			System.err.println("No existe ningun servicio registrado con el nombre " + NAME);
			;
		}
		System.out.println("------------------------------------");
		System.out.println("-- Invocar a un servidor");
		int size = lista.size();
		Random rd = new Random();
		int index = Math.abs(rd.nextInt() % size);
		System.out.println("size: " + size);
		System.out.println("index: " + index);
		Service servidor = lista.get(index);
		String url = String.format("http://%s:%d/albums", servidor.getAddress(), servidor.getPort());
		System.out.println(url);

		HttpGet get = new HttpGet(url);
		get.addHeader("Accept", "application/json");
		List<Album> dto = new ArrayList();
		String d = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			dto = httpClient.execute(get, response -> {
				int status = response.getStatusLine().getStatusCode();
				if (status == 200) {
					return new ObjectMapper().readValue(response.getEntity().getContent(),
							new TypeReference<List<Album>>() {
							});
				}
				return null;
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("lista: " + dto);
		return dto;
	}

	@Override
	public Album buscarId(Integer idAlbum) {

		if (lista.isEmpty()) {
			System.err.println("No existe ningun servicio registrado con el nombre " + NAME);
			;
		}
		System.out.println("------------------------------------");
		System.out.println("-- Invocar a un servidor");
		int size = lista.size();
		Random rd = new Random();
		int index = Math.abs(rd.nextInt() % size);
		System.out.println("size 2: " + size);
		System.out.println("index 2: " + index);
		Service servidor = lista.get(index);
		String url = String.format("http://%s:%d/albums", servidor.getAddress(), servidor.getPort());
		System.out.println(url);
		
		

		HttpGet get = new HttpGet(url + "/" + idAlbum.toString());
		get.addHeader("Accept", "application/json");
		Album dto = new Album();
		String d = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			dto = httpClient.execute(get, response -> {
				int status = response.getStatusLine().getStatusCode();
				if (status == 200) {
					return new ObjectMapper().readValue(response.getEntity().getContent(), new TypeReference<Album>() {
					});
				}
				return null;
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("lista: " + dto);
		return dto;
	}
}
