package TestPackage;

import MethodsPackage.Buffer;
import MethodsPackage.Consumer;
import MethodsPackage.Histogram;
import MethodsPackage.Producer;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {

		String inputFile = null;
		String outputFile = null;

		if(args.length < 2){ //verific daca aplicatia a primit toate argumentele de care are nevoie
			Scanner keyboard = new Scanner(System.in);
			System.out.println("Introduceti caile fisierelor");
			
			inputFile = keyboard.nextLine(); //daca nu, le preiau de la tastatura
			outputFile = keyboard.nextLine();
		}	
		else {
			inputFile = args[0]; //daca da, le preiua din linie de comanda
			outputFile = args[1];
		}
		double startTime, finishedTime;

		//Citirea si trsamiterea imaginii cu multithreading
		startTime = System.currentTimeMillis();


		Buffer buffer =  new Buffer(inputFile); //Initializam un buffer pentru trasmiterea de date
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Producer p1 = new Producer(buffer);
		Consumer c1 = new Consumer(buffer);
		//Pornim thread-urile pentru producer si consumer
		p1.start();
		c1.start();

		try {
			c1.join(); //Asteptam sa termine consumer-ul sa consume imaginea
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		finishedTime = System.currentTimeMillis();
		System.out.println("Timp transmisie imagine = " + (finishedTime - startTime)); // Afisam timpul necesar trasmiterii imaginii cu multithreading

		startTime = System.currentTimeMillis();
		Histogram hist = new Histogram();
		hist.histogramImage(buffer.getImage(), outputFile); //Aplicam algoritmul pentru obtinerea histogramei
		finishedTime = System.currentTimeMillis();
		System.out.println("Timp procesare imagine = " + (finishedTime - startTime)); //Afisam timpul necesar procesarii de imagine 


	}
}