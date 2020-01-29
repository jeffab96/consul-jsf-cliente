
package rest.cdi.client.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.agent.model.Service;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import rest.cdi.client.consumidor.ClienteSingerHttp;
import rest.cdi.client.entidad.Singer;

@ApplicationScoped
public class ClienteSingerHttpImpl implements ClienteSingerHttp {
	public static final String NAME = "singer-conf";

	ConsulClient client = new ConsulClient("127.0.0.1");

	Response<Map<String, Service>> ss = client.getAgentServices();

	Map<String, Service> services = ss.getValue();

	List<Service> lista = services.values().stream().filter(s -> NAME.equals(s.getService()))
			.collect(Collectors.toList());

	//final static String SINGER_URL = "http://localhost:8080/examen-masache-c1/servSinger";
	CloseableHttpClient httpClient = HttpClients.createDefault();
	@Override
	public List<Singer> listar() {
		
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
		String url = String.format("http://%s:%d/singers", servidor.getAddress(), servidor.getPort());
		System.out.println(url);

		
		
		HttpGet get = new HttpGet(url);
		get.addHeader("Accept", "application/json");
		List<Singer> dto = new ArrayList();
		String d = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			dto = httpClient.execute(get, response -> {
				int status = response.getStatusLine().getStatusCode();
				if (status == 200) {
					return new ObjectMapper().readValue(response.getEntity().getContent(),
							new TypeReference<List<Singer>>() {
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
	public Singer buscarId(Integer idSinger) {
		
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
		String url = String.format("http://%s:%d/singers", servidor.getAddress(), servidor.getPort());
		System.out.println(url);
		
		
		HttpGet get = new HttpGet(url+"/"+idSinger.toString());
		get.addHeader("Accept", "application/json");
		Singer dto = new Singer();
		String d = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			dto = httpClient.execute(get, response -> {
				int status = response.getStatusLine().getStatusCode();
				if (status == 200) {
					return new ObjectMapper().readValue(response.getEntity().getContent(), new TypeReference<Singer>() {
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
