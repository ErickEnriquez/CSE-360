/* 
 * This class may be unnecessary. The idea is to have all of the pertNodes share
 * a single common list between themselves. This way, they all access the
 * same list when determining whether the given dependency is already a
 * declared/initialized node;
 */
import java.util.*;

public class PertList 
{
	ArrayList<PertNode> masterList;
	PertList() //default constructor
	{
		this.masterList = new ArrayList<PertNode>();
	}
	void updateList(PertNode Node)
	{
		this.masterList.add(Node);
	}
}
