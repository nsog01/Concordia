
import java.util.*;

public class SimpleIterator
{

   public static void main(String[] args)
   {

   	Collection<String> sixtiesGroups = new LinkedList<String>();
	sixtiesGroups.add("Vandellas");
	sixtiesGroups.add("Chiffons");
	sixtiesGroups.add("Crystals");

	Iterator<String> iter = sixtiesGroups.iterator();
	while (iter.hasNext()) {
  		String grp = iter.next();
		if (grp.equals("Chiffons"))
			iter.remove();
		System.out.println(grp);
	}
   }
}