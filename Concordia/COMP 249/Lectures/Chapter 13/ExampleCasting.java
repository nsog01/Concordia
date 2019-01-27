public class ExampleCasting {

   public static void main (String[] args) {

       Animal a1 = new Dog();
       Dog d1 = new Dog();
       a1 = d1;

       // Dog d2 = new Animal(); // compiler: incompatible types
     //  Dog d2 = (Dog) new Animal(); // runtime: ClassCastException

       Dog d3 = new Dog();
       Animal a3 = d3;
       Dog d4 = (Dog) a3;

       Animal a5 = new Dog();
       //Dog d5 = a5;   // compiler: incompatible types
       Dog d6 = (Dog) a5;

       Helicopter h = new Helicopter();
       // Animal a5 = (Animal) h;  // compiler: incompatible types


       // let's try with interfaces now...

       Inflatable i = new Balloon();
       i = new Balloon();

       //Balloon b = i; // compiler: incompatible types
       Balloon b = (Balloon) i;

       // PartyBalloon bb = (PartyBalloon) new Balloon();  // runtime: ClassCastException

       // PartyBalloon bb2 = i;  // compiler: incompatible types
       //PartyBalloon bb3 = (Balloon) i;  // compiler: incompatible types
      //   PartyBalloon bb4 = (PartyBalloon) i;

         i = new Pool();
         b = new Balloon();
         i = b;
    }
}

class Animal {
}

class Helicopter {
}

class Dog extends Animal {
}


interface Inflatable {
}

class Balloon implements Inflatable {
}

class PartyBalloon extends Balloon  {
}

class Pool implements Inflatable {
}