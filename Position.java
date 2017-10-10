enum DIR {DIR_UP,DIR_DOWN,DOR_LEFT,DIR_RIGHT}

public class Position {
	protected int x,y;
	
	public Position(int posX,int posY)
	{
		x=posX;
		y=posY;
	}
	
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	
	public boolean equals(Object obj) 
	{
		if (this == obj)
		    return true;
		if (obj == null)
		{
		    return false;
		}
		if (getClass() != obj.getClass())
		{
		    return false;
		}
		
		Position other = (Position) obj;
		if (x != other.x)
		{
		    return false;
		}
		if (y != other.y)
		{
		    return false;
		}
		
		return true;
	}
	
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String toString()
	{
		String pos="("+x+","+y+")";
		return pos;
		
	}
	
	public void move(DIR dir)
	{
		if(dir.equals(DIR.DOR_LEFT))
		{
			this.setY(this.getY()+1);
		}
		else if(dir.equals(DIR.DIR_RIGHT))
		{
			this.setY(this.getY()-1);
		}
		else if(dir.equals(DIR.DIR_UP))
		{
			this.setX(this.getX()-1);
		}
		else if(dir.equals(DIR.DIR_DOWN))
		{
			this.setX(this.getX()+1);
		}
	}
}
