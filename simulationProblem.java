//Problem 5
import java.text.DecimalFormat;

public class simulationProblem {
	double k=16807.00;
	double m=2147483647.00;
	double coinChooseRandomNumber=0.00,successRandomNumber=0.00;
	final double lambda=2.00;
	double mean=0.00, variance=0.00;
	double sum=0.0,squareSum=0.00,s1;
	double squareMean=0.00;
	DecimalFormat df = new DecimalFormat("0.00");
	int numOfHeads[]=new int[1000];
	int numOfTwoHeads=0,numOfTwoHeadsOfA=0;
	double array[]=new double[5000];
	int index=0,j=0,numHeads=0;
		
	public void experiment()
	{
		s1=1111.00;
		for (int i=0;i<5000;i++)
		{
			//random number generation for choosing a coin
			coinChooseRandomNumber=s1/m;
			array[i]=coinChooseRandomNumber;
			s1=(k*s1)%m;
		}
		
		for (int i=0,numHeads=0;i<5000 && numHeads<1000;i++,numHeads++)
		{
			
			if((0<=array[i])&&(array[i]<=0.5))
			{	
				//identify the coin from here.
				//choosing coin A
				for (int count=0;count<4;count++)
				{
					successRandomNumber=array[++i];
					if((0<=successRandomNumber)&&(successRandomNumber<=0.333))
					{
						++numOfHeads[numHeads];
					}
					else
					{
						//Do nothing
					}
				}
				if(numOfHeads[numHeads]==2)
				{
					++numOfTwoHeads;
					++numOfTwoHeadsOfA;
				}
			}
			else if((0.5<=array[i])&&(array[i]<=0.75))
			{	
				//identify the coin from here.
				//choosing coin B
				for (int count=0;count<4;count++)
				{
					
					successRandomNumber=array[++i];
					if((0<=successRandomNumber)&&(successRandomNumber<=0.25))
					{
						++numOfHeads[numHeads];
					}
					
					{
						//DO nothing
					}
				}
				if(numOfHeads[numHeads]==2)
				{
					++numOfTwoHeads;
				}
			}
			
			else if((0.75<=array[i])&&(array[i]<=1.00))
			{	
				//identify the coin from here.
				//choosing coin C
				for (int count=0;count<4;count++)
				{
					successRandomNumber=array[++i];
					if((0<=successRandomNumber)&&(successRandomNumber<=0.667))
					{
						++numOfHeads[numHeads];
					}
					else
					{
						//Do nothing
					}
				}	
				if(numOfHeads[numHeads]==2)
				{
					++numOfTwoHeads;
				}
			}			
		}
		
		for (int i=0;i<1000;i++)
		{
			sum+=numOfHeads[i];
			squareSum+=(numOfHeads[i]*numOfHeads[i]);
		}
		
		mean=sum/1000;
		variance=(squareSum/1000)-(mean*mean);
		
		System.out.println("The total number of heads "+sum);
		System.out.println("Mean= "+mean);
		System.out.println("Variance= "+variance);
		System.out.println("The conditional probability that Coin A was selected given the 2 heads appear="+(double)numOfTwoHeadsOfA/numOfTwoHeads);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		simulationProblem last=new simulationProblem();
		last.experiment();
	}
}
