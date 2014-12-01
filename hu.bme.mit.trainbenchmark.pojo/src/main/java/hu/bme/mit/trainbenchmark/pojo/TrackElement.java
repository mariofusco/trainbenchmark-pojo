package hu.bme.mit.trainbenchmark.pojo;

import java.util.List;

public class TrackElement {

	private List<Sensor> sensors;
	private List<TrackElement> connectsTo;
	
	public List<Sensor> getSensors() {
		return sensors;
	}
	public void setSensors(List<Sensor> sensors) {
		this.sensors = sensors;
	}
	
	public List<TrackElement> getConnectsTo() {
		return connectsTo;
	}
	public void setConnectsTo(List<TrackElement> connectsTo) {
		this.connectsTo = connectsTo;
	}
}
