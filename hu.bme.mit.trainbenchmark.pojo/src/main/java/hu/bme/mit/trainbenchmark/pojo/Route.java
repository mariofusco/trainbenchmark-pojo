package hu.bme.mit.trainbenchmark.pojo;

import java.util.ArrayList;
import java.util.List;

public class Route extends ListenableObject {
	
	private Signal entry; 
	private Signal exit; 
	
	private List<SwitchPosition> switchPositions;
	
	private List<Sensor> routeDefinition;
	
	public Signal getEntry() {
		return entry;
	}
	public void setEntry(Signal entry) {
		Signal oldEntry = entry;
		this.entry = entry;
		firePropertyChange(this, "entry", oldEntry, entry);
	}

	public Signal getExit() {
		return exit;
	}
	public void setExit(Signal exit) {
		Signal oldExit = exit;
		this.exit = exit;
		firePropertyChange(this, "exit", oldExit, exit);
	}

	public List<SwitchPosition> getSwitchPositions() {
		return switchPositions;
	}
	public void setSwitchPositions(List<SwitchPosition> switchPositions) {
		this.switchPositions = switchPositions;
	}
	public void addSwitchPosition(SwitchPosition switchPosition) {
		if (switchPositions == null) {
			switchPositions = new ArrayList<>();
		}
		switchPositions.add(switchPosition);
	}

	public List<Sensor> getRouteDefinition() {
		return routeDefinition;
	}
	public void setRouteDefinition(List<Sensor> routeDefinition) {
		this.routeDefinition = routeDefinition;
	}
	public void removeRouteDefinition(int index) {
		routeDefinition.remove(index);
		firePropertyChange(this, "routeDefinition", routeDefinition, routeDefinition);
	}

	public void addRouteDefinition(Sensor sensor) {
		if (routeDefinition == null) {
			routeDefinition = new ArrayList<>();
		}
		routeDefinition.add(sensor);
	}
}
