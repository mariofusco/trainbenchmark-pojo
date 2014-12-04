package hu.bme.mit.trainbenchmark.pojo;

public class Signal extends ListenableObject {

	private static final SignalStateKind SIGNAL_ACTUAL_STATE_EDEFAULT = SignalStateKind.SIGNALSTATEKIND_STOP;

	private SignalStateKind actualState = SIGNAL_ACTUAL_STATE_EDEFAULT;

	public SignalStateKind getActualState() {
		return actualState;
	}

	public void setActualState(SignalStateKind actualState) {
		this.actualState = actualState;
	}
}
