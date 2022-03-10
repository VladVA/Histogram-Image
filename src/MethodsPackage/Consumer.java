package MethodsPackage;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class Consumer extends Thread{
	private Buffer buffer;
	String auxFile;
	
	public Consumer(Buffer buffer){ //constructor pentru consumer
		this.buffer = buffer;
	}
	
	@Override
	public void run(){
		BufferedImage consumedImage = new BufferedImage(buffer.getWidth(), buffer.getHeight() * 4, BufferedImage.TYPE_INT_RGB);
		
		Graphics2D g = consumedImage.createGraphics();
		for(int i = 0; i < 4; i++){
			
			BufferedImage image = new BufferedImage(buffer.getWidth(), buffer.getHeight(), BufferedImage.TYPE_INT_RGB);
			
			image = buffer.write(i); //preluam cate un sfert de imagine de la producer
			g.drawImage(image, 0, i * (buffer.getHeight()), null); //adaugam cate un sfert de imagine la imaginea complet preluata de la producer
			
			System.out.println("Quarter consumed " + (i + 1)); //afisam o data ce a fost consumat cate un sfert
			try{
				sleep(1000);
			}
			catch(InterruptedException e) {}
		}
		
		g.dispose();
		
		buffer.saveImage(consumedImage); //salvam imaginea compeleta o data ce a fost transmisa
	}

}
