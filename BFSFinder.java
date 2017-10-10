
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;



public class BFSFinder {
	private Map<Position,CELL> cell =null;
	private Map<Position,CELL> road=new HashMap<Position,CELL>();
	private Map<Position,Position> preElement =new HashMap<Position,Position>();
	private int SIZE;
	private Position start;
	private Position end;
	private Position Teleporter;
	private Position LandingCell;
	private boolean switchT=true;
	
	private Stack<Position> pathStack= new Stack<Position>();
	
	
	Queue <Position> posQueue=new LinkedList<Position>();
	
	public BFSFinder(Map<Position,CELL> param,int size)
	{
		cell=param;
		start=new Position(0,0);
		SIZE=size;
		end=new Position(SIZE-1,SIZE-1);
	}
	public void init()
	{

		java.util.Iterator<Position> it = cell.keySet().iterator();
		while(it.hasNext()){
			Position p = it.next();
			if(!cell.get(p).equals(CELL.CELL_W)){
				road.put(p, cell.get(p));
			}
			if(cell.get(p).equals(CELL.CELL_T))
			{
				Teleporter= p;
			}
			if(cell.get(p).equals(CELL.CELL_L))
			{
				LandingCell=p;
			}	
		}
		
		posQueue.offer(start);
	}
	
	public Position loop (Position p)
	{
		pathStack.push(p);
		
		Position temp =new Position(preElement.get(p).getX(),preElement.get(p).getY());
		
		return temp;
	}
	
	public Stack<Position> result()
	{
		if(preElement.containsKey(end))
		{
			Position temp=loop(end);

			while(preElement.containsKey(temp))
			{
				temp=loop(temp);
			}
			
			pathStack.push(start);
			
			
			
		}
		else
		{
			System.out.println("There is no path");
		}
		
		return pathStack;
	}
	
	public void finderShortestPath()
	{
		
		
		while(!posQueue.isEmpty())
		{
			
			Position newPos=posQueue.poll();
			
			if(newPos.equals(start))
			{
				if(newPos.equals(LandingCell))
				{
					break;
				}
				else
				{
					if(newPos.equals(Teleporter)&&switchT)
					{
						posQueue.offer(LandingCell);
						preElement.put(LandingCell, newPos);
						switchT=false;
					}
					
				}
			}
			
			
			
			Position upPos=new Position(newPos.getX(),newPos.getY());
			upPos.move(DIR.DIR_UP);
			if(road.containsKey(upPos))
			{
				if(!preElement.containsValue(upPos))
				{
					if(upPos.equals(LandingCell))
					{
						break;
					}
					else
					{
						if(upPos.equals(Teleporter)&&switchT)
						{
							posQueue.offer(LandingCell);
							preElement.put(LandingCell, newPos);
							switchT=false;
						}
						else
						{
							posQueue.offer(upPos);
							preElement.put(upPos, newPos);
						}
					}
					
				}	
			}
			
			Position leftPos=new Position(newPos.getX(),newPos.getY());
			leftPos.move(DIR.DOR_LEFT);
			if(road.containsKey(leftPos))
			{	
				if(!preElement.containsValue(leftPos))
				{
					
					if(leftPos.equals(LandingCell))
					{
						break;
					}
					else
					{
						if(leftPos.equals(Teleporter)&&switchT)
						{
							posQueue.offer(LandingCell);
							preElement.put(LandingCell, newPos);
							switchT=false;
						}
						else
						{
							posQueue.offer(leftPos);
							preElement.put(leftPos, newPos);
						}
					}
					
				}
			}
			
			Position downPos=new Position(newPos.getX(),newPos.getY());
			downPos.move(DIR.DIR_DOWN);
			if(road.containsKey(downPos))
			{		
				if(!preElement.containsValue(downPos))
				{
					if(downPos.equals(LandingCell))
					{
						break;
					}
					else
					{
						if(downPos.equals(Teleporter)&&switchT)
						{
							posQueue.offer(LandingCell);
							preElement.put(LandingCell, newPos);
							switchT=false;
						}
						else
						{
							posQueue.offer(downPos);
							preElement.put(downPos, newPos);
						}	
					}
					
				}
			}
			
			Position rightPos=new Position(newPos.getX(),newPos.getY());
			rightPos.move(DIR.DIR_RIGHT);
			if(road.containsKey(rightPos))
			{
				if(!preElement.containsValue(rightPos))
				{
					if(rightPos.equals(LandingCell))
					{
						break;
					}
					else
					{
						if(rightPos.equals(Teleporter)&&switchT)
						{
							posQueue.offer(LandingCell);
							preElement.put(LandingCell, newPos);
							switchT=false;
						}
						else
						{
							posQueue.offer(rightPos);
							preElement.put(rightPos, newPos);
						}	
					}
					
				}
			}	
		}
	}
	
}
