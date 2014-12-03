package hu.bme.mit.trainbenchmark.pojo;

public class SwitchPosition {

	private Switch theSwitch;
	
	private SwitchStateKind switchState;
	
	private Route route;
	
	public Switch getSwitch() {
		return theSwitch;
	}

	public void setSwitch(Switch theSwitch) {
		this.theSwitch = theSwitch;
	}

	public SwitchStateKind getSwitchState() {
		return switchState;
	}

	public void setSwitchState(SwitchStateKind switchState) {
		this.switchState = switchState;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}
}
