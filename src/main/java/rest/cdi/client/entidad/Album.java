package rest.cdi.client.entidad;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement
public class Album implements Serializable{
	private static final long serialVersionUID = 1L;
    private Integer id;
    private String title;
    private Date releaseDate;
    private Integer idSinger;
    private Integer version;
    
    public Album() {
	}

	public Album(String title, Date releaseDate, Integer idSinger, Integer version) {
		this.title = title;
		this.releaseDate =  releaseDate;
		this.idSinger = idSinger;	
		this.version = version;
	}
}
