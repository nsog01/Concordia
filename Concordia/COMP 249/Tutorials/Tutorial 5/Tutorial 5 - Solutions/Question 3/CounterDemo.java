import java.io.*;

public class CounterDemo {
    public static void main(String[] args) throws IOException {
        CountReader cr = new CountReader(new FileReader("input.txt"), 'a');
        CountWriter cw = new CountWriter(new FileWriter("output.txt"), 'a');
        int c = 0;
        while ((c = cr.read()) != -1) {
            cw.write(c);
        }
        System.out.println(cr.getCount());   
        System.out.println(cw.getCount());   
	cr.close();
	cw.close();
    }
}
