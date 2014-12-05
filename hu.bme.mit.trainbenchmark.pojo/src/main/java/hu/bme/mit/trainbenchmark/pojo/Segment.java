package hu.bme.mit.trainbenchmark.pojo;

public class Segment extends TrackElement {

	private int length;

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		int oldLength = this.length;
		this.length = length;
		firePropertyChange("length", oldLength, length);
	}
}
