package assign;

import javax.swing.*;



import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



public class GUISolution extends JFrame implements MouseListener{
	public final int SIZE=10;
	
	private GUIDrawingPanel panel ;
	
	private int RectWidth;
	private int RectHeight;
	
	private int[][] maze;
	
	
	private Map<Position,CELL> cell= new HashMap<Position,CELL>();
	
	public enum CELL {
		CELL_E, CELL_W, CELL_T, CELL_L;
	}
	
	public GUISolution(String filename)
	{
		LoadMaze loadMaze=new LoadMaze(filename);
		maze=loadMaze.convertToArray();
		setTitle("Maze");
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		setSize(dim.width/2+300, dim.height/2+322);
		setLocation(new Point(0, 50));
		panel = new GUIDrawingPanel(dim.width/2+300, dim.height/2+300);
		panel.addMouseListener(this);
		Container contentPane = this.getContentPane();
		contentPane.add(panel);
	}
	
	
	
	public class GUIDrawingPanel extends JPanel
	{
		int Panel_width=0;
		int Panel_height=0;
		public GUIDrawingPanel(int PANEL_WIDTH,int PANEL_HEIGHT) {
			setBackground(Color.white);
			Panel_width=PANEL_WIDTH;
			Panel_height=PANEL_HEIGHT;
			setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
			createMap();
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			RectWidth=this.getWidth()/SIZE;
			RectHeight=this.getHeight()/SIZE;
			drawRectangle(g);
			
			BFSFinder bfsFinder=new BFSFinder(cell,SIZE);
			bfsFinder.init();
			bfsFinder.finderShortestPath();
			drawNum(g,bfsFinder.result());
			
		}
		
		public void createMap ()
		{
			int positionX=0;
			int positionY=0;
			for(int i=0;i<maze.length;i++)
			{	
				for (int j=0;j<maze[i].length;j++)
				{
					if(maze[i][j]==CELL.CELL_E.ordinal())
					{
						Position pos =new Position(positionX,positionY);
						cell.put(pos, CELL.CELL_E);
						positionY+=1;
					}
					else if(maze[i][j]==CELL.CELL_W.ordinal())
					{
						Position pos =new Position(positionX,positionY);
						cell.put(pos, CELL.CELL_W);
						positionY+=1;
					}
					else if(maze[i][j]==CELL.CELL_T.ordinal())
					{
						Position pos =new Position(positionX,positionY);
						cell.put(pos, CELL.CELL_T);
						positionY+=1;
					}
					else if(maze[i][j]==CELL.CELL_L.ordinal())
					{
						Position pos =new Position(positionX,positionY);
						cell.put(pos, CELL.CELL_L);
						positionY+=1;
					}
				}
				positionY=0;
				positionX+=1;	
			}
		}
		
		public void drawNum(Graphics g,Stack<Position> pathStack)
		{
			Font numFont= new Font("Times New Roma",Font.ITALIC,20);
			g.setFont(numFont);
			int num=1;
			while(!pathStack.isEmpty())
			{
				Position pos=pathStack.pop();
				int strHeight=pos.getX()*RectHeight+RectHeight/2+5;
				int strWidth=pos.getY()*RectWidth+RectWidth/2-10;
				g.drawString(String.valueOf(num++), strWidth, strHeight);
			}


		}
		
		public void drawRectangle(Graphics g)
		{
			
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(1));
			
			for(Map.Entry<Position, CELL> entry:cell.entrySet())
			{
				if(entry.getValue().equals(CELL.CELL_E))
				{
					g2.setPaint(Color.white);
					g2.fillRect(entry.getKey().getY()*RectWidth,entry.getKey().getX()*RectHeight, RectWidth, RectHeight);
				}
				if(entry.getValue().equals(CELL.CELL_W))
				{
					g2.setPaint(Color.black);
					g2.fillRect(entry.getKey().getY()*RectWidth,entry.getKey().getX()*RectHeight,  RectWidth, RectHeight);
				}
				if(entry.getValue().equals(CELL.CELL_T))
				{
					g2.setPaint(Color.red);
					g2.fillRect(entry.getKey().getY()*RectWidth,entry.getKey().getX()*RectHeight,  RectWidth, RectHeight);
				}
				if(entry.getValue().equals(CELL.CELL_L))
				{
					g2.setPaint(Color.green);
					g2.fillRect(entry.getKey().getY()*RectWidth,entry.getKey().getX()*RectHeight,  RectWidth, RectHeight);
				}
			}	
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.isShiftDown())
		{	
			for(Map.Entry<Position, CELL> entry :cell.entrySet())
			{
				if(entry.getKey().getX()*RectHeight<e.getY()&&(entry.getKey().getX()*RectHeight+RectHeight)>e.getY())
				{
					if(entry.getKey().getY()*RectWidth<e.getX()&&(entry.getKey().getY()*RectWidth+RectWidth)>e.getX())
					{
						Position temp=null;
						for(Map.Entry<Position, CELL> entry1 :cell.entrySet())
						{
							if(entry1.getValue().equals(CELL.CELL_T))
							{
								temp=entry1.getKey();
							}
						}
						CELL tempC=entry.getValue();
						cell.replace(entry.getKey(), entry.getValue(), CELL.CELL_T);
						cell.replace(temp, CELL.CELL_T, tempC);
					}
				}
			}
			
		}
		
		else if (e.isControlDown())
		{
			for(Map.Entry<Position, CELL> entry :cell.entrySet())
			{
				if(entry.getKey().getX()*RectHeight<e.getY()&&(entry.getKey().getX()*RectHeight+RectHeight)>e.getY())
				{
					if(entry.getKey().getY()*RectWidth<e.getX()&&(entry.getKey().getY()*RectWidth+RectWidth)>e.getX())
					{
						Position temp=null;
						for(Map.Entry<Position, CELL> entry1 :cell.entrySet())
						{
							if(entry1.getValue().equals(CELL.CELL_L))
							{
								temp=entry1.getKey();
							}
						}
						CELL tempC=entry.getValue();
						cell.replace(entry.getKey(), entry.getValue(), CELL.CELL_L);
						
						cell.replace(temp, CELL.CELL_L, tempC);
					}
				}
			}
		}
		
		else
		{
			for(Map.Entry<Position, CELL> entry :cell.entrySet())
			{
				if(entry.getKey().getX()*RectHeight<e.getY()&&(entry.getKey().getX()*RectHeight+RectHeight)>e.getY())
				{
					if(entry.getKey().getY()*RectWidth<e.getX()&&(entry.getKey().getY()*RectWidth+RectWidth)>e.getX())
					{	
						if(entry.getValue().equals(CELL.CELL_W))
						{
							cell.replace(entry.getKey(), CELL.CELL_W, CELL.CELL_E);
						}
						else if(entry.getValue().equals(CELL.CELL_E))
						{
							cell.replace(entry.getKey(), CELL.CELL_E, CELL.CELL_W);
						}
					}
				}
				
				
			}
		}
		repaint();
	}
	

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
