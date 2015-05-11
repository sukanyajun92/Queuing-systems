//problem 4
import java.text.DecimalFormat;

public class exponentialRandomVariable {

	double k=16807.00;
	double m=2147483647.00;
	double randomNumber=0.00,y=0.00;
	final double lambda=2.00;
	double j=0.00;
	double mean=0.00, variance=0.00;
	double sum=0.0f,squareSum=0.00,s;
	double squareMean=0.00;
	DecimalFormat df = new DecimalFormat("0.00");
	
	public void randomNumberGeneration()
	{
		s=1111.00;
		for (int i=0;i<10000;i++)
		{
			randomNumber=s/m;
			y=(-1/lambda)*(Math.log(randomNumber));
			s=(k*s)%m;
			
			squareSum=squareSum+(y*y);
			sum=sum+(y);
		}
		
		mean=sum/10000;
		squareMean=squareSum/10000;
		variance=squareMean-(mean*mean);
		
		System.out.println("The value of the mean= "+df.format(mean));
		System.out.println("the value of variance= "+df.format(variance));
	}

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		exponentialRandomVariable assignment=new exponentialRandomVariable();
		assignment.randomNumberGeneration();
	}
}
