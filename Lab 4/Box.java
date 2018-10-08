/*
Alfonso Pruneda
CS 2401 MW 4:30 - 5:50PM
This Box class is used in association with the Runner class.
*/
public class Box{
	private double length;
	private double width;
	private double height;
	private int position;
	public Box next;

	public Box(){ // default constructor in case it's needed
	}

	public Box(double w, double h, double l, int p){ // contstructor used to set the variables
		length = l;
		width = w;
		height = h;
		position = p;
	}
	//used to get the volume of box
	public double getVolume(){
		return (length * width * height);
	}
	//used to check if box is cubic
	public boolean isCube(){
		if(length == height && length == width){
			return true;
		}
		return false;
	}
	//setters and getters (only needed a getter for position)
	public int getPosition(){
		return position;
	}
	public double getWidth(){
		return width;
	}
	public double getLength(){
		return length;
	}
	public double getHeight(){
		return height;
	}
	public void setWidth(double w){
		width = w;
	}
	public void setHeight(double h){
		height = h;
	}
	public void setLength(double l){
		length = l;
	}

}