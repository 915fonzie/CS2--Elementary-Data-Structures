/*
Alfonso Pruneda MW 4:30-5:50 PM
Package class that is used by the Runner class
*/


public class Package{

	private double length;
	private double width;
	private double height;

	public Package(double[] v){
		length = v[0];
		width = v[1];
		height = v[2];
	}
	public double getVolume(){ // Used to return the volume of package
		return length*width*height;
	}
	public boolean isCube(){ // Used to find out if package is a cube
		if(length==width && length == height)
			return true;
		return false;
	}

	// Setters and Getters if needed

	public void setLength(double l){
		length = l;
	}
	public void setWidth(double w){
		width = w;
	}
	public void setHeight(double h){
		height = h;
	}
	public double getLength(){
		return length;
	}
	public double getWidth(){
		return width;
	}
	public double getHeight(){
		return height;
	}
}