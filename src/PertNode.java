import java.util.*;

public class PertNode 
{
	ArrayList<PertNode> Dependencies = new ArrayList<PertNode>();
	String Node;
	int Duration;
	
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
	
}

