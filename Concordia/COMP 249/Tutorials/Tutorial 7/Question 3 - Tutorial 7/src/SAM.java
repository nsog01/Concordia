
public class SAM {
	
	public static int fired_count = 0;
	private int my_fired_count = 0;
	private int my_fired_velocity = 0;
	
	/*
	 * Inner Class
	 */
	private class Missile implements RocketLike {
		
		private static final int DEFAULT_VELOCITY = 200;
		public int Launch(int velocity) {
			if(velocity < 80) {
				System.out.println("Slow missiles crash nearby " + "to devastating effects, pick it up a notch!");
				return 0;
			}
			fired_count++;
			my_fired_count++;
			my_fired_velocity+=velocity;
			System.out.println("Missile Launched");
	
			return 0;
		}
	
		public void Launch() {
			Launch(DEFAULT_VELOCITY);
		}
		
	}
		public double getAverageMissileFireVelocity() {
			
			if(my_fired_count == 0) return 0;
			
			return (double)my_fired_velocity/(double)my_fired_count;
		}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
