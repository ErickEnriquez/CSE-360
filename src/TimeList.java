import java.util.*;

public class TimeList 
{
	int time;
	ArrayList<ArrayList<PertNode>> bigList;
	ArrayList<PertNode> smallList;
//this comment counts as a change
	void insertionSortTimeList(ArrayList<TimeList> array, String order)
	{
		if(Objects.equals(order, "i"))
		{
			for (int j = 1; j < array.size(); j++)
			{
				TimeList Key = array.get(j);
				int i = j - 1;
				while(i > -1 && array.get(i).time > Key.time)
				{
					array.set(i + 1, array.get(i));
					i = i - 1;
				}
				array.set(i + 1, Key);
			}
		}
		else
		{
			for (int j = 1; j < array.size(); j++)
			{
				TimeList Key = array.get(j);
				int i = j - 1;
				while(i > -1 && array.get(i).time < Key.time)
				{
					array.set(i + 1, array.get(i));
					i = i - 1;
				}
				array.set(i + 1, Key);
			}
		}
	}
}
