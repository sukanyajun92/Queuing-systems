import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class AssignmentTrial
{
	public double lambda=0.0,clock = 0.0;             
	public int N = 0, Ndep = 0,done = 0;                   
	public double EN = 0.0,blockingProb;   
	public int k;
	public double numEntered=0, numOfArrivals=0;
	public int numOfBlocked=0;
	public double rhoArray[] = {0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9,1.0},utilization=0.0;
	public double randomVarParam1=16807, randomVarParam2=2147483647,randomVar;
	File newFile=new File("SimulationLog.txt");
	
	public void simuFunction() throws FileNotFoundException
	{
		PrintWriter writer = new PrintWriter(newFile);
		Scanner scan=new Scanner(System.in);
		System.out.println("Enter the value of mu ");
		double mu=scan.nextDouble();
		
		System.out.println("Enter the value of k ");
		k=scan.nextInt();
		
		RandomVariable randVar=new RandomVariable();
		LinkedList <EventClass>eventList=new LinkedList <EventClass>();
		EventClass currentEvent=new EventClass();
		for (int i=0;i<10;i++)
		{
			writer.append("Case : rho[i] " + rhoArray[i]);
			lambda= rhoArray[i]* 2 * mu;
			N=0;EN=0.0;clock=0;Ndep=0;done=0;
			utilization=0.0;numEntered=0;numOfArrivals=0;numOfBlocked=0;
			eventList.clear();
			double seed = 1111.0;
			randomVar=0.0;
			
			eventList.add(new EventClass(randVar.getExpoTime(lambda),"ARR"));   ///first arrival comes in to the system
			while(done==0) 
			{
				seed=(randomVarParam1*seed) % randomVarParam2;	//generate random variable...this generates the same set of random variable for evry value of rho...for 100000 departures
				randomVar=seed/randomVarParam2;
				
				double prev = clock;   //update the previous system clock
				Collections.sort(eventList, new EventClassComparator());//always sort the list to get the event with lowest time stamp
				currentEvent = eventList.getFirst();
				clock=currentEvent.getEventTime(); 
				
				switch (currentEvent.getEventType()) 
				{
					case "ARR": 
					{
						++numOfArrivals;
						if((N>0) && (N<=k))
						{
							utilization+=0.5*(clock-prev);    ///for (1-k) bracket only one of the servers'll be operational
						}
						else if((N>k) && (N<=(2*k)))
						{
							utilization+=1.0*(clock-prev);  //for (k-2k) bracket, both the servers get operational
						}
						
						if(N<k)   //processing Nth customer ---> strictly less than k
						{
				   			++numEntered;
							EN += N*(clock-prev); 
							N++;  
							if (N==1) //activate the first server
							{                             
								eventList.add(new EventClass(clock+randVar.getExpoTime(mu),"DEP"));      //  generate its departure event
							}
						}
						else if ((N>=k) && (N<(2*k)))   //processing the kth customer until 2k-1th customer
						{
							if(randomVar<=0.5)   //50% probability blocking
							{
								++numOfBlocked;
								EN += N*(clock-prev);  
							}
							else
							{
								++numEntered;    //allowing arrivals to enter the system with 50% probability
								EN += N*(clock-prev);  
								N++;
								if(N==k+1)   //activate the second server
								{
									eventList.add(new EventClass(clock+ randVar.getExpoTime(mu),"DEP"));      //  generate its departure event
								}
							}
						}
						else if (N >=(2 * k)) //Complete blocking...Dont update the number of customers in the system... But you need to update system statistics
						{
							++numOfBlocked;
							EN += N * (clock - prev);
							utilization += 1.0 * (clock - prev);
						}
						eventList.add(new EventClass(clock +  randVar.getExpoTime(lambda), "ARR"));
						break;
					}
					case "DEP":    
					{
						if((1<=N)&&(N<=k))  
				    	{
				    		utilization+=0.5*(clock-prev);
				    		EN += N*(clock-prev);                   //  update system statistics
						    N--;                                    //  decrement system size
				    	}
				    	else if((N>k) && (N<=2*k))
				    	{
				    		utilization+=1.0*(clock-prev);
				    		EN += N*(clock-prev);                   //  update system statistics
						    N--;                                    //  decrement system size
				    	}
				    	else
				    	{
				    		utilization+=1.0*(clock-prev);
				    		EN += N*(clock-prev); 
				    	}
					    Ndep++; 
					    if ((N>0) && (N<k))  //generate a departure
					    {
					    	eventList.add(new EventClass(clock+randVar.getExpoTime(mu),"DEP"));   //  generate next departure
					    }
					    
					    else if (N==k)
					    {
					    	//do nothing
					    }
					    else if ((N>k) && (N<=(2*k)))
					    {                            //generate another departure
					    	eventList.add(new EventClass(clock+randVar.getExpoTime(mu),"DEP"));
					    }
					    break;
				    }
				}
				//System.out.println(eventList);
				eventList.poll();   //remove from the head of the list
				if (Ndep > 100000) 
				{
					done=1;
				}
			}
			writer.append("\nCurrent number of customers in system: " + N );
			blockingProb=(numOfBlocked/numOfArrivals);
			writer.append("\nProbability of blocking = "+ blockingProb);
			writer.append("\nThe total time spent into the system = " + EN/numEntered);
			writer.append("\nExpected number of customers (simulation): " + EN/clock);
			writer.append("\nTotal utilization of the server " + utilization/clock);
			writer.println();
			writer.flush();
		}
	}
	public static void main(String[] args) throws FileNotFoundException 
	{
		// TODO Auto-generated method stub
		AssignmentTrial assign=new AssignmentTrial();
		assign.simuFunction();
	}
}

