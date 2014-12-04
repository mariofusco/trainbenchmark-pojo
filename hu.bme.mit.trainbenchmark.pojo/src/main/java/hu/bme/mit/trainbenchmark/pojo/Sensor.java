package hu.bme.mit.trainbenchmark.pojo;

import java.util.List;

public class Sensor extends ListenableObject {

	private List<TrackElement> trackElements;

	public List<TrackElement> getTrackElements() {
		return trackElements;
	}

	public void setTrackElements(List<TrackElement> trackElements) {
		this.trackElements = trackElements;
	}
}
