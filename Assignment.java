//Problem 3
import java.text.DecimalFormat;

public class Assignment 
{
	double k=16807;
	double m=2147483647;
	double randomNumber=0.00;
	double j=0.00;
	double mean=0.00, variance=0.00;
	double sum=0.00,squareSum=0.00,s;
	double squareMean=0.00;
	DecimalFormat df = new DecimalFormat("0.00");
	
	public void randomNumberGeneration()
	{
		int[] countArray=new int[10];
		double check=0.00;
		s=1111.00;
		for (int i=0;i<10000;i++)
		{
			randomNumber=s/m;
			s=(k*s)%m;			
						
			check=randomNumber;
			if((0<=check) && (check<0.1))
				++countArray[0];
			else if((0.1<=check) && (check<0.2))
				++countArray[1];
			else if((0.2<=check) && (check<0.3))
				++countArray[2];
			else if((0.3<=check) && (check<0.4))
				++countArray[3];
			else if((0.4<=check) && (check<0.5))
				++countArray[4];
			else if((0.5<=check) && (check<0.6))
				++countArray[5];
			else if((0.6<=check) && (check<0.7))
				++countArray[6];
			else if((0.7<=check) && (check<0.8))
				++countArray[7];
			else if((0.8<=check) && (check<0.9))
				++countArray[8];
			else if((0.9<=check) && (check<=10.0))
				++countArray[9];			
			
			squareSum=squareSum+(randomNumber*randomNumber);
			sum=sum+randomNumber;
		}
		
		mean=sum/10000;
		squareMean=squareSum/10000;
		variance=squareMean-(mean*mean);
		
		System.out.println("The value of the mean= "+df.format(mean));
		System.out.println("the value of variance= "+df.format(variance));
		System.out.println("the contents of the array ");

		System.out.println("Interval\t\tCount");
		System.out.println("------------------------------");
		
		System.out.println("[0,0.1]\t\t\t"+countArray[0]);
		System.out.println("[0.1,0.2]\t\t"+countArray[1]);
		System.out.println("[0.2,0.3]\t\t"+countArray[2]);
		System.out.println("[0.3,0.4]\t\t"+countArray[3]);
		System.out.println("[0.4,0.5]\t\t"+countArray[4]);
		System.out.println("[0.5,0.6]\t\t"+countArray[5]);
		System.out.println("[0.6,0.7]\t\t"+countArray[6]);
		System.out.println("[0.7,0.8]\t\t"+countArray[7]);
		System.out.println("[0.8,0.9]\t\t"+countArray[8]);
		System.out.println("[0.9,1.0]\t\t"+countArray[9]);
	}

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		Assignment assignment=new Assignment();
		assignment.randomNumberGeneration();
	}
}
