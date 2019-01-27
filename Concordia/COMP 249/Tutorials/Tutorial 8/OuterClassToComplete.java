class OuterClassToComplete {
	private int xValue = 10;
	public void createInnerClass() {
		// TO DO: create aninstance of an InnerClass
		inClass.accessOuter();
	}
	class InnerClass {
		public void accessOuter() {
			System.out.println("The outer class's privInt is " + xValue);
		}
	}

	// TO DO: Write a main method to print xValue
}
