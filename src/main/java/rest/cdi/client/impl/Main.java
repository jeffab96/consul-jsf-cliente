package rest.cdi.client.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.fasterxml.jackson.databind.ObjectMapper;

import rest.cdi.client.consumidor.ClienteAlbumHttp;
import rest.cdi.client.consumidor.ClienteSingerHttp;
import rest.cdi.client.entidad.Album;
import rest.cdi.client.entidad.Singer;
//ESTA CLASE ES SOLO PARA PRUEBAS, NO ES NECESARIA
public class Main {
public static final String SINGER_URL = "http://localhost:8080/examen-masache-c1/servSinger/1";
	
	public static void main(String[] args) throws Exception {
		
		CloseableHttpClient httpClient = HttpClients.createDefault( );

		HttpGet get = new HttpGet( SINGER_URL );
		
		get.addHeader( "Accept", "application/json" );
		
		Singer dto =  httpClient.execute( get, response->{
		
			int status = response.getStatusLine().getStatusCode();
			if( status==200 ) {
				
				return new ObjectMapper().readValue( response.getEntity().getContent(), Singer.class );
			}
			
			return null;
		} );
		
		System.out.println( "id        : " + dto.getId( ) );
		System.out.println( "first-name: " + dto.getFirstName( ) );
		System.out.println( "last-name : " + dto.getLastName( ) );
		System.out.println( "birth-date: " + dto.getBirthDate( ) );
		System.out.println( "version: " + dto.getVersion() );
		
	}


}
