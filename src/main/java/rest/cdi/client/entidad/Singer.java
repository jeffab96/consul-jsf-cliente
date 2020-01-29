package rest.cdi.client.entidad;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;


@Data
@XmlRootElement
public class Singer  implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private Integer version;
	//@OneToMany(mappedBy = "idSinger")
	//private List<AlbumDto> albums = new ArrayList<>();

	public Singer() {
	}

	public Singer(String firstName, String lastName, Date  birthDate, Integer version) {
		this.firstName = firstName;
		this.lastName =  lastName;
		this.birthDate =  birthDate;
		this.version = version;
		
	}
}
