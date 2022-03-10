package MethodsPackage;


public class Producer extends Thread{
	private Buffer buffer;
	
	public Producer (Buffer buffer){ //constructor producer
		this.buffer = buffer;
	}
	
	@Override
	public void run(){
		for(int i = 0; i < 4; i++){
			buffer.read(); //citim cate un sfert de imagine la fiecare iteratie a for-ului
			System.out.println("Quarter produced " + (i + 1)); //afisam odata ce a fost produs un sfert
			try{
				sleep(1000);
			}
			catch(InterruptedException e) {}
		}
	}
}
