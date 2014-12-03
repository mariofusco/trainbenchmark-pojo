package hu.bme.mit.trainbenchmark.pojo;

import java.util.ArrayList;
import java.util.List;

public class Route {
	
	private Signal entry; 
	private Signal exit; 
	
	private List<SwitchPosition> switchPositions;
	
	private List<Sensor> routeDefinition;
	
	public Signal getEntry() {
		return entry;
	}
	
	public void setEntry(Signal entry) {
		this.entry = entry;
	}

	public Signal getExit() {
		return exit;
	}
	
	public void setExit(Signal exit) {
		this.exit = exit;
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

	public void addRouteDefinition(Sensor sensor) {
		if (routeDefinition == null) {
			routeDefinition = new ArrayList<>();
		}
		routeDefinition.add(sensor);
	}
}
