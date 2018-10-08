import java.util.*;

public class PertNode extends PertList
{
	ArrayList<PertNode> Dependencies;
	String Node;
	int Duration;
	String[] stringDependencies;
	
	PertNode() // default constructor
	{
		this.Node = "empty";
		this.Duration = 0;
	}
	PertNode(String Node, int Duration, String[] stringDependencies)
	{
		this.Node = Node;
		this.Duration = Duration;
		this.stringDependencies = stringDependencies;
	}
	int getDuration()
	{
		return Duration;
	}
	String getNode()
	{
		return Node;
	}
	ArrayList<PertNode> getDependencies()
	{
		return Dependencies;
	}
	void setDuration(int Duration)
	{
		this.Duration = Duration;
	}
	void setNode(String Node)
	{
		this.Node = Node;
	}
	void setStringDependencies(String[] Dependencies)
	{
		this.stringDependencies = Dependencies;
	}
	//assuming beyond this point that the data entry has concluded and all nodes have been inserted into masterList;
	//function that determines dependency nodes;
	boolean checkDependecy()
	{
		for(int i = 0; i < this.stringDependencies.length; i++)
		{
			if(this.stringDependencies[i] == )
		}
		return false;
	}
	void setDependencyList()
	{
		
	}
	
}

