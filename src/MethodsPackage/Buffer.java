package MethodsPackage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;


public class Buffer {
	String inputFile;
	private int width = 1;
	private int height  = 1;
	BufferedImage quarterImage = null;
	private boolean available = false;
	BufferedImage savedImage = null;
	
	
	public Buffer(String inputFile){ // In constructorul clasei Buffer salvam inaltimea si latimea sfertului de imagine care trebuie trasmis
		this.inputFile = inputFile;
		try {
			BufferedImage measurements = ImageIO.read(new File(this.inputFile));
			this.height = measurements.getHeight()/4;
			this.width = measurements.getWidth();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Getteri pnetru inaltimea si latimea imaginii
	public int getHeight(){
		return height;
	}
	
	public int getWidth(){
		return width;
	}
	
	public synchronized BufferedImage write (int quarterNr){
		
		BufferedImage quarter = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
		
		while(available){
			try{
				wait();
			}
			catch (InterruptedException e){
				e.printStackTrace();
			}
		}
		available = true;
		notifyAll();
		
		try{
			quarterImage = ImageIO.read(new File(this.inputFile));
			quarter = quarterImage.getSubimage(0,quarterNr * this.height,this.width, this.height);	
		}
		catch (IOException e){
			e.printStackTrace(); 
		}
		return quarter;
	}
	
	public synchronized void read(){ // metoda pentru citirea imaginii pe sferturi
		
		try{
			quarterImage = ImageIO.read(new File(this.inputFile));
			this.height = quarterImage.getHeight()/4; //aici este luata dimensiunea pentru ianltimea sfertului de imagine 
			this.width = quarterImage.getWidth();
		}
		catch (IOException e){
			e.printStackTrace();
		}
		
		
		while (!available){
			try{
				wait();
			}
			catch (InterruptedException e){
				e.printStackTrace();
			}
		}
		
		available = false;
		notifyAll();
	}
	
	
	public void saveImage(BufferedImage image){ //metoda pentru salvarea imaginii intregi (toate sferturile imbinate)
		savedImage = image;
	}
	
	
	public BufferedImage getImage(){ // getter pentru a prelua imaginea trasmisa intreaga
		return savedImage;
	}

}
