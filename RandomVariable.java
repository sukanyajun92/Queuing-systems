import java.util.Random;

public class RandomVariable 
{
	double parameter;
	double Seed = 1111.0;	
	
	RandomVariable()
	{
		
	}
	
	double uni_rv()           
	{
	    double k = 16807.0;
	    double m = 2.147483647e9;
	    double rv;

	    Seed=(k*Seed) % m;	
	    rv=Seed/m;
	    return(rv);
	}
	
	RandomVariable(double value)
	{
		this.parameter=value;
	}
	
	public double getExpoTime(double value)
	{
		return (((-1) / value) * Math.log(uni_rv()));
	}
	
	public double generateRandomVariable(double seed) //my random variable from homework1
	{
		  double k=16807;
		  double m=2147483647;
		  double rv;
          Seed=(k*seed) % m;	
		  rv=Seed/m;
		  return(rv);
	}
}
