package hu.bme.mit.trainbenchmark.pojo;

import java.util.ArrayList;
import java.util.List;

public class Sensor extends ListenableObject {

	private List<TrackElement> trackElements;

	public List<TrackElement> getTrackElements() {
		return trackElements;
	}
	public void setTrackElements(List<TrackElement> trackElements) {
		this.trackElements = trackElements;
	}

	public void addTrackElement(TrackElement trackElement) {
		if (trackElements == null) {
			trackElements = new ArrayList<>();
		}
		trackElements.add(trackElement);
	}
	public void removeTrackElement(TrackElement trackElement) {
		trackElements.remove(trackElement);
	}

	public void clearTrackElements() {
		for (TrackElement trackElement : trackElements) {
			trackElement.removeSensor(this);
			firePropertyChange(trackElement, "sensors", trackElement.getSensors(), trackElement.getSensors());
		}
		trackElements.clear();
		firePropertyChange("trackElements", trackElements, trackElements);
	}
}
