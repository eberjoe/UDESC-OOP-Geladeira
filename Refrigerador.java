package geladeira;

public class Refrigerador {
	
	private float selecaoTemperatura;
	
	Sensor sensor = new Sensor();
	Ventilador ventilador = new Ventilador();
	
	public float getSelecaoTemperatura() {
		return selecaoTemperatura;
	}
	public void setSelecaoTemperatura(float selecaoTemperatura) {
		this.selecaoTemperatura = selecaoTemperatura;
	}
	
}
