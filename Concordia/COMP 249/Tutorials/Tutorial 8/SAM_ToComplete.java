public class SAM_ToComplete {

	public static void main(String[] args) {

		SAM s1 = new SAM();
		SAM s2 = new SAM();
		SAM s3 = new SAM();
		s1.new Missile().Launch(150);
		s1.new Missile().Launch();
		s1.new Missile().Launch();
		s2.new Missile().Launch(72);
		s2.new Missile().Launch(150);
		s3.new Missile().Launch(0);
		System.out.println("s1 fired missiles at an average velocity of " + s1.getAverageMissileFireVelocity());
		System.out.println("s2 fired missiles at an average velocity of " + s2.getAverageMissileFireVelocity());
		System.out.println("s3 fired missiles at an average velocity of " + s3.getAverageMissileFireVelocity());
		System.out.println(SAM.fired_count + " Missiles were successfully fired.");
	}
}