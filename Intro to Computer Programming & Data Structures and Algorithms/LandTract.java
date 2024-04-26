
import java.util.Scanner;

public class LandTract {
	
	public int length, width, area;
	
	public LandTract() {
		length = 0;
		width = 0;
		area= 0;
	}
	
	public LandTract(int length, int width) {
        this.length = length;
        this.width = width;
    }
	
	public void setArea (int area){
		this.area = area;
	}
	
	// Area
	public static int area(int length, int width) {
		return length * width;
	}
	
	// Equals
	public boolean equals(LandTract lt) {
		boolean equals;
		if (length == (lt.length) && width == (lt.width) || length == (lt.width) && width == (lt.length))
			equals = true;
		else
			equals = false;
		return equals;
	}
	
	// ToString
	public String toString() {
		String str = ("LandTract" + " with length " + length + ", width " 
						   				+ width + ", and area " + area);
		return str;
	}
	
	public static void main(String[] args) {
		int length1 = 0;
		int width1 = 0;
		int area1 = 0;
		
		int length2 = 0;
		int width2 = 0;
		int area2 = 0;
		
		Scanner keyboard = new Scanner(System.in);
		
		// first tract
		System.out.print("Enter length of first land tract:");
		length1 = keyboard.nextInt();
		
		System.out.print("Enter width of first land tract:");
		width1 = keyboard.nextInt();
		
		LandTract land1 = new LandTract(length1,width1);
		
		area1 = LandTract.area(length1,width1);
		
		land1.setArea(area1);
		
		// second tract
		System.out.print("Enter length of second land tract:");
		length2 = keyboard.nextInt();
		
		System.out.print("Enter width of second land tract:");
		width2 = keyboard.nextInt();
		
		LandTract land2 = new LandTract(length2,width2);
		
		area2 = LandTract.area(length2,width2);
		
		land2.setArea(area2);
		
		// display
		System.out.println(land1.toString());
		System.out.println(land2.toString());
		
		if (land1.equals(land2))
			System.out.println("The two tracts have the same size.");
		else
			System.out.println("The two tracts do not have the same size.");
		
		
	}
}