//**************************************************************************************
//Assignment #4
//Written by: Jordan Hubscher, ID: 7019696
//For Comp 248 Section EE -Fall 2013
//**************************************************************************************

//This program will define a class for complex numbers with accessor, mutator, comparison, 
//addition, and multiplication methods as well.

public class Complex  {
	//set coefficients of real and imaginary parts respectively as instance variables to the Complex class objects.
	private double a;
	private double b;
	
	//no-argument constructor.
	public Complex() {
		a = 0;
		b = 0;
	}
	
	//user-initialized constructor.
	public Complex(double a, double b) {	
		this.a = a;
		this.b = b;
	}
	
	//accessor method for instance variable a.
	public double geta() {
		return a;
	}
	
	//accessor method for instance variable b.
	public double getb() {
		return b;
	}	
	
	//mutator method for instance variable a.
	public double seta(double a) {
		return this.a = a;
	}

	//mutator method for instance variable b.
	public double setb(double b) {
		return this.b = b;
	}
	
	//formats real and imaginary parts respectively into a string displaying the appropriate complex number.
	public String toString() {
		return a + " + " + this.b + "i";
	}
	
	//adds the real and imaginary parts respectively of two complex numbers into a new complex number.
	public Complex addComplex(Complex other) {
		Complex newAdd = new Complex();
		double newa = this.a + other.geta();
		double newb = this.b + other.getb();
		newAdd.seta(newa);
		newAdd.setb(newb);
		
		return newAdd;
	}
	
	//multiplies two complex numbers appropriately into a new complex number.
	public Complex productComplex(Complex other) {
		Complex newProd = new Complex();
		double newa = (this.a*other.geta()) - (this.b*other.getb());
		double newb = (this.a*other.getb()) + (this.b*other.geta());
		newProd.seta(newa);
		newProd.setb(newb);
		
		return newProd;
	}
	
	//compares two objects for equality.
	public boolean equals(Complex other) {
		return (this.a == other.geta() && this.b == other.getb());
	}
}
