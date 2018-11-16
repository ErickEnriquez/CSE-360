/*
 * The purpose of the hash table is twofold. It will increase the efficiency of some of the traversal algorithms by preventing redundant recursive 
 * traversals or comparisons that have already been made. It will also allow for an easy way to check for loops by storing known child nodes
 * and checking whether the children are parents of any of their parent nodes. 
 * 		Loop Checking:
 * 			While the algorithms traverse from the end of the graph to the beginning, they will catalog all of the child nodes, and compare them against the
 * 			requirements for the parent nodes. If a match is found, this would indicate that an ancestor to a given vertex was also a parent to said vertex.
 * 			This is obviously a contradiction that would require an error case specifically tailored to its resolution.	
 * */
/*import java.util.*;
public class HashTable 
{
	ArrayList<PertNode>[] HashTable;
	int size;
	
	HashTable(int size)
	{
		this.size = size;
		HashTable = (ArrayList<PertNode>[]) new ArrayList[size];
	}
	int HashFunction(String pertChart)
	{
		char[] string = pertChart.toCharArray();
		int hashInt = 0;
		for (int i = 0; i < string.length;i++)
		{
			hashInt +=  string[i];
		}
		int hashValue = hashInt % size;
		return hashValue;
	}
	void AddToHash(PertNode node)
	{
		this.HashTable[HashFunction(node.getNode())].add(node);
	}
	boolean LookUpHash(PertNode node)
	{
		
		for (int i = 0; i < this.HashTable[HashFunction(node.getNode())].size(); i++)
		{
			if(Objects.equals(this.HashTable[HashFunction(node.getNode())].get(i).getNode(), node.getNode()))
			{
				return true;
			}
		}
		return false;
	}
}*/