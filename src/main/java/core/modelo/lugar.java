package core.modelo;


public class lugar {
	private String nombreCompleto;
	private Integer aforo;//
	private Integer maximoPersonas;
	private String cambio;
	private Integer ID;
	private String tiempo;
	private long Capi;//Luego se convertirá en una clase
	
	public lugar(String nombreCompleto, Integer aforo, Integer ID, Integer maximoPersonas)
	{
		this.nombreCompleto=nombreCompleto;
		this.aforo=aforo;
		this.ID=ID;
		this.maximoPersonas = maximoPersonas;
	}
	
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public Integer getAforo() {
		return aforo;
	}
	public void setAforo(Integer aforo) {
		this.aforo = aforo;
	}

	public String getCambio() {
		return cambio;
	}

	public void setCambio(String cambio) {
		this.cambio = cambio;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getTiempo() {
		return tiempo;
	}

	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}

	public long getCapi() {
		return Capi;
	}

	public void setCapi(long capi) {
		Capi = capi;
	}

	public Integer getMaximoPersonas() {
		return maximoPersonas;
	}

	public void setMaximoPersonas(Integer maximoPersonas) {
		this.maximoPersonas = maximoPersonas;
	}
}
