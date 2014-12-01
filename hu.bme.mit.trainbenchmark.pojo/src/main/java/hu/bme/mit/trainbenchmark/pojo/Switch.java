package hu.bme.mit.trainbenchmark.pojo;

import java.util.List;

public class Switch extends TrackElement {
	
	private static final SwitchStateKind SWITCH_ACTUAL_STATE_EDEFAULT = SwitchStateKind.POINT_STATE_KIND_FAILURE;
	
	private SwitchStateKind actualState = SWITCH_ACTUAL_STATE_EDEFAULT;
	
	private List<SwitchPosition> switchPositions;

	public SwitchStateKind getActualState() {
		return actualState;
	}

	public void setActualState(SwitchStateKind actualState) {
		this.actualState = actualState;
	}

	public List<SwitchPosition> getSwitchPositions() {
		return switchPositions;
	}

	public void setSwitchPositions(List<SwitchPosition> switchPositions) {
		this.switchPositions = switchPositions;
	}
}
