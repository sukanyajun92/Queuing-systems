import java.sql.Time;
import java.util.Comparator;

public class EventClass 
{
	String eventType;
	double eventTime;
	
	EventClass()
	{
		
	}
	EventClass(double time,String type)
	{
		this.eventTime=time;
		this.eventType=type;
	}
	
	public double getEventTime()
	{
		return this.eventTime;
	}
	
	public String getEventType()
	{
		return this.eventType;
	}
}
//including a comparator class
class EventClassComparator implements Comparator<EventClass>   //user defined comparator
{
	public int compare(EventClass eve1, EventClass eve2)
	{
		double time1=eve1.getEventTime();
		double time2=eve2.getEventTime();
		if (time1 > time2) //used to sort in ascending order of timestamps
		{
			return 1;
		} 
		else if (time1 < time2) //if first event's time is less than the second event's time
		{
			return -1;
		} 
		else 
		{
			return 0;
		}
	}
}