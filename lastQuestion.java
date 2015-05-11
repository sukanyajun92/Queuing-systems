import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;


public class lastQuestion {
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
		
	public void experiment() throws FileNotFoundException
	{
		File logFile=new File("log.txt");
		File randNums=new File("randNums");
		PrintWriter print=new PrintWriter(logFile);
		PrintWriter randomN=new PrintWriter(randNums);
		
		s1=1111.00;
		for (int i=0;i<5000;i++)
		{
			//random number generation for choosing a coin
			coinChooseRandomNumber=s1/m;
			array[i]=coinChooseRandomNumber;
			randomN.append("\n"+array[i]);
			s1=(k*s1)%m;
		}
		
		for (int i=0,numHeads=0;i<5000 && numHeads<1000;i++,numHeads++)
		{
			
			if((0<=array[i])&&(array[i]<=0.5))
			{	
				//identify the coin from here.
				//choosing coin A
				print.append("\nCoin A is chosen");
				for (int count=0;count<4;count++)
				{
					successRandomNumber=array[++i];
					if((0<=successRandomNumber)&&(successRandomNumber<=0.333))
					{
						print.append("\nCoin A shows heads");
						++numOfHeads[numHeads];
					}
					else
					{
						print.append("\nCoin A shows tail");
					}
				}
				if(numOfHeads[numHeads]==2)
				{
					++numOfTwoHeads;
					++numOfTwoHeadsOfA;
					print.append("\nCoin A has shown 2 heads");
				}
			}
			else if((0.5<=array[i])&&(array[i]<=0.75))
			{	
				//identify the coin from here.
				//choosing coin B
				print.append("\nCoin B is chosen");
				for (int count=0;count<4;count++)
				{
					
					successRandomNumber=array[++i];
					if((0<=successRandomNumber)&&(successRandomNumber<=0.25))
					{
						print.append("\nCoin B shows heads");
						++numOfHeads[numHeads];
					}
					
					
					{
						print.append("\ncoin B shows tails");
					}
				}
				if(numOfHeads[numHeads]==2)
				{
					++numOfTwoHeads;
					print.append("\nCoins B shows 2 heads");
				}
			}
			
			else if((0.75<=array[i])&&(array[i]<=1.00))
			{	
				//identify the coin from here.
				//choosing coin C
				print.append("\nCoin C is chosen");
				for (int count=0;count<4;count++)
				{
					successRandomNumber=array[++i];
					if((0<=successRandomNumber)&&(successRandomNumber<=0.667))
					{
						print.append("\nCoin C shows heads");
						++numOfHeads[numHeads];
					}
					else
					{
						print.append("\nCoin C shows tail");
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
		
		print.append("\nMean= "+mean);
		print.append("\nVariance= "+variance);
		print.append("\nThe conditional probability="+(double)numOfTwoHeadsOfA/numOfTwoHeads);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		lastQuestion last=new lastQuestion();
		try
		{
			last.experiment();
		}
		
		catch(Exception e)
		{
			System.err.println("\nHEllo");
		}
		
	}

}
