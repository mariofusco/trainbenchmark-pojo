package hu.bme.mit.trainbenchmark.pojo;

public class SwitchPosition {

	private Switch theSwitch;
	
	private SwitchStateKind switchStateKind;
	
	private Route route;
	
	public Switch getSwitch() {
		return theSwitch;
	}

	public void setSwitch(Switch theSwitch) {
		this.theSwitch = theSwitch;
	}

	public SwitchStateKind getSwitchStateKind() {
		return switchStateKind;
	}

	public void setSwitchStateKind(SwitchStateKind switchStateKind) {
		this.switchStateKind = switchStateKind;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}
}
