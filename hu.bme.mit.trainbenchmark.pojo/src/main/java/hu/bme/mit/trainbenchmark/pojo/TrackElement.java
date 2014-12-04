package hu.bme.mit.trainbenchmark.pojo;

import java.util.ArrayList;
import java.util.List;

public class TrackElement extends ListenableObject {
	
	private long id = -1;

	private List<Sensor> sensors;
	private List<TrackElement> connectsTo;
	
	public List<Sensor> getSensors() {
		return sensors;
	}
	public void setSensors(List<Sensor> sensors) {
		this.sensors = sensors;
	}
	public void addSensor(Sensor sensor) {
		if (sensors == null) {
			sensors = new ArrayList<>();
		}
		sensors.add(sensor);
	}
	
	public long getId() {
		if (id < 0) {
			id = IdGenerator.next();
		}
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public List<TrackElement> getConnectsTo() {
		return connectsTo;
	}
	public void setConnectsTo(List<TrackElement> connectsTo) {
		this.connectsTo = connectsTo;
	}
	public void addConnectsTo(TrackElement element) {
		if (connectsTo == null) {
			connectsTo = new ArrayList<>();
		}
		connectsTo.add(element);
	}
}
