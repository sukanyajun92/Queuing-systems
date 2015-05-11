import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TheoreticalAnalysis 
{
	public static double lambda=0.0;
	public double clock = 0.0;             
	public int N = 0, Ndep = 0,done = 0;                   
	public static double EN = 0.0,blockingProb;   
	public int k;
	public static double rhoArray[] = {0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9,1.0};
	public double utilization=0.0;
	public static double lambdaEff=0.0;
	public double randomVarParam1=16807, randomVarParam2=2147483647,randomVar;
	public static File TheoreticalFileLog=new File("TheoryFile.txt");
	static int n=0;
	
	public static void theoreticalCalculation(double mu, int k) throws FileNotFoundException
	{
		PrintWriter writer=new PrintWriter(TheoreticalFileLog);
		
		for (int i=0;i<10;i++)
		{
			blockingProb=0.0;
			int sizeOfProbArray=(2 * k);
			double probabilityArray[]=new double[sizeOfProbArray+1];
			double steadyStateProbArray[]=new double[sizeOfProbArray+1];
			double sum=0.0, sumOfSteadyStateProb=0.0;
			int index=1;double weightedAvg=0.0;
			lambda=rhoArray[i]*mu*2;
			double ratio=lambda/mu;
			lambdaEff=0.0;
			
			for (index=1;index<=k;index++)    //calculating just the ratios barring P0
			{
				probabilityArray[index]=Math.pow(ratio, index);
				sum+=probabilityArray[index];
			}
			
			for (index=k+1;index<=sizeOfProbArray;index++)  //P[n]=P[n-1]*(lambda/mu)^n
			{
				probabilityArray[index]=(ratio/4)*probabilityArray[index-1];
				sum+=probabilityArray[index];
			}
			probabilityArray[0]=1/(1+sum);   //calculate p0 from the formula...sum of all prob=1
			
			//compute the steady state probabilities from ratios computed in previous step...
			steadyStateProbArray[0]=probabilityArray[0];
			for (int steadyIndex=1;steadyIndex<=sizeOfProbArray;steadyIndex++)
			{
				steadyStateProbArray[steadyIndex]=steadyStateProbArray[0]*probabilityArray[steadyIndex];
			} 
			//computing the an=verage number of customers in the system
			weightedAvg=0.0;
			for (n=0;n<=sizeOfProbArray;n++)
			{
				weightedAvg+=n*steadyStateProbArray[n];
			}
			//computing throughput or lambda effective
			lambdaEff=0.0;
			for(n=0;n<k;n++)
			{
				lambdaEff+=lambda * steadyStateProbArray[n];
			}
			//this including the probabilitis of blocking(remember: lamda<>Arrivals they are Enterings
			for(n=k;n<sizeOfProbArray;n++)
			{
				lambdaEff+=lambda * 0.5*steadyStateProbArray[n];
			}
			//computing total utilization capacity used for each probability/total system capacity
			double utilizationSum=0.0,utilization=0.0;			
			for(n=1;n<=k;n++)
			{
				utilizationSum+=mu*steadyStateProbArray[n];
			}
			for (n=k+1;n<=sizeOfProbArray;n++)
			{
				utilizationSum+=2*mu*steadyStateProbArray[n];
			}
			
			utilization=utilizationSum/(2*mu);
			//printing steady state probabilities 
			for (int ind=0;ind<=2*k;ind++)
			{
				System.out.println("Steatdy state prob = " + steadyStateProbArray[ind]);
			}
			System.out.println("--------------------------------------------");
			
			writer.append("\nCase: rho[i]= " + rhoArray[i] + "\nExpected number of customers in the system = " + weightedAvg);
			writer.append("\n Expected time spent in the system = " +weightedAvg/lambdaEff);
			writer.append("\nUtilization of the system = " + utilization);
			//blocking probabilities -->lamda(entering*steady state probabilities)
			for (int x=k;x<sizeOfProbArray;x++)
			{
				blockingProb+=0.5*steadyStateProbArray[x];
			}
			blockingProb+=steadyStateProbArray[sizeOfProbArray];
			writer.append("\n Blocking probability = " + blockingProb);
			writer.flush();
		}
	}
	public static void main(String[] args) throws FileNotFoundException 
	{
		// TODO Auto-generated method stub
		Scanner scan=new Scanner(System.in);
		System.out.println("Enter the value of k ");
		int k=scan.nextInt();
		
		System.out.println("Enter the value of mu ");
		double mu=scan.nextDouble();
		
		TheoreticalAnalysis theory=new TheoreticalAnalysis();
		theory.theoreticalCalculation(mu, k);
	}
}
