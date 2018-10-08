/*
Alfonso Pruneda
CS 2401 MW 4:30-5:50PM
This Runner class is used to import an input file with a list of variables for boxes.
Those variables, or dimensions, are put into a linked list to be used for various methods
that check for Volume, which box is the smallest, which box is the largest, how many cubic
boxes there are, average volume for all boxes and average volume for cubic boxes.
*/


import java.io.*;
import java.util.Scanner;

public class Runner{
	//main method used to read file and call other methods
	public static void main(String[] args) throws FileNotFoundException{
		File inFile = new File("input.txt");
		Scanner input = new Scanner(inFile);
		Box head = new Box();
		Box temp = head;
		int position = 0;
		while(input.hasNextLine()){
			String temp2 = input.nextLine(); // Used to create a string of the next line of the file
			String[] temp3 = temp2.split(" "); // split the String into a String array to seperate variables
			if(temp3.length != 3){ //checks if the String array has more or less variables than it needs
				System.out.printf("On line %d there is either more or less than three measurements\n", position+1);
				System.exit(0);
			}
			if(position == 0){
				head = new Box(Double.parseDouble(temp3[0]), Double.parseDouble(temp3[1]),Double.parseDouble(temp3[2]), position); //initiallizing the head of the linked list
				temp = head;
			}
			if(position > 0){
				temp.next = new Box(Double.parseDouble(temp3[0]), Double.parseDouble(temp3[1]),Double.parseDouble(temp3[2]), position);// as the String array variables are being passed, they are also being parsed into double variables
				temp = temp.next;
			}
			position++;
		}
		System.out.println();
		smallestBox(head, 0); // calling all the methods
		System.out.println();
		largestBox(head, 0);
		System.out.println();
		averageVolume(numberOfCubicBoxes(head),1); //numberOfCubicBoxes is set to do its thing and then return the cubic linked list for averageVolume to be used for cubic boxes
		averageVolume(head,0);
		System.out.println();
	}
	// method used to search for the smallest box in a linked list
	public static void smallestBox(Box h, int t){//int t is used for when method is being called for all boxes or cubic boxes
		int position = h.getPosition();
		int counter = 1;
		Box smallest = new Box(h.getWidth(), h.getHeight(), h.getLength(), position);//initiallize the first Box
		if(t == 0){
			System.out.println("Smallest Box:"); 
		}
		if(h.next == null){
			System.out.printf(" Position: %d \n Dimensions: %,.2f, %,.2f, %,.2f\n Volume: %,.2f\n", position, smallest.getWidth(),smallest.getHeight(),smallest.getLength(), smallest.getVolume());
		}
		h = h.next;
		while(h != null){
			if(smallest.getVolume() > h.getVolume()){ 
				smallest.setWidth(h.getWidth());
				smallest.setHeight(h.getHeight());
				smallest.setLength(h.getLength());
				position = h.getPosition();
			}
			h = h.next;
			counter++;
		}
		if(counter > 1){
			System.out.printf(" Position: %d\n Dimensions: %,.2f, %,.2f, %,.2f\n Volume: %,.2f\n", position, smallest.getWidth(),smallest.getHeight(),smallest.getLength(), smallest.getVolume());
		}
	}
	//method used to search for the largest box in a linked list provided
	public static void largestBox(Box h, int t){ // int t is used again to check whether it is used for all boxes or only cubic boxes
		int position = h.getPosition();
		int counter = 1;
		Box largest = new Box(h.getWidth(), h.getHeight(), h.getLength(), position);
		if(t == 0){
			System.out.println("Largest Box:");			
		}
		if(h.next == null){
			System.out.printf(" Position: %d\n Dimensions: %,.2f, %,.2f, %,.2f\n Volume: %,.2f\n", position, h.getWidth(),h.getHeight(),h.getLength(), h.getVolume());
		}
		h = h.next;
		while(h != null){
			if(largest.getVolume() < h.getVolume()){
				largest.setWidth(h.getWidth());
				largest.setHeight(h.getHeight());
				largest.setLength(h.getLength());
				position = h.getPosition();
			}
			h = h.next;
			counter++;
		}
		if(counter > 1){
			System.out.printf(" Position: %d\n Dimensions: %,.2f, %,.2f, %,.2f\n Volume: %,.2f\n", position, largest.getWidth(),largest.getHeight(),largest.getLength(), largest.getVolume());
		}
	}
	//method used to look for how many cubic boxes are in the linked list provided
	public static Box numberOfCubicBoxes(Box h){
		Box cubic = new Box();
		Box temp = cubic;
		int counter = 0;
		while(h != null){
			if(h.isCube()){
				if(counter == 0){
					cubic = new Box(h.getWidth(), h.getHeight(), h.getLength(), h.getPosition());
					temp = cubic;
				}
				if(counter > 0){
					temp.next = new Box(h.getWidth(), h.getHeight(), h.getLength(), h.getPosition());
					temp = temp.next;
				}
				counter++;
			}
			h = h.next;
		}
		System.out.printf("Number of Cubic Boxes: %d\n",counter);
		System.out.println("Smallest Cubic Box: ");	
		smallestBox(cubic, 1); // Use the created linked list with only cubic boxes to check for smallest cubic box
		System.out.println("Largest Cubic Box: ");
		largestBox(cubic, 1);// Use the created linked list with only cubic boxes to check for the largest cubic box
		System.out.println();
		return cubic; // Return the cubic linked list to use for the average volume of all cubic boxes
	}
	//method used to find the overall average volume for the boxes in the linked list provided
	public static void averageVolume(Box h, int t){ // int t is used to determine whether all boxes or only cubic boxes are used
		double average = 0.0;
		int counter = 0;
		while(h != null){
			average += h.getVolume(); // adds all the volumes together from each box from the provided linked list
			h=h.next;
			counter++;
		}
		average = average / counter;
		if(t == 0){
			System.out.printf("The Overall Average Volume: %.2f\n", average);
		}
		else
			System.out.printf("The Average Volume for Cubic Boxes: %.2f\n", average);
	}
}