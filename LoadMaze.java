

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadMaze {
	String filename;
	public LoadMaze(String name)
	{
		filename=name;
	}
	
	public int[][] convertToArray()
	{
		int [][]maze= new int[10][10];
		List<String> dataList=importCsv(new File(filename));
		for(int i=0;i<dataList.size();i++)
		{	
			String[] a=dataList.get(i).split(",");
			for (int j=0;j<a.length;j++)
			{
				if(a[j].equals(""))
				{
					maze[i][j]=0;
				}
				else if(a[j].equals("W"))
				{
					maze[i][j]=1;
				}
				else if(a[j].equals("T"))
				{
					maze[i][j]=2;
				}
				else if(a[j].equals("L"))
				{
					maze[i][j]=3;
				}
			}
		}
		

		return maze;
	}
	
	
	public  List<String> importCsv(File file)
	{
        List<String> dataList=new ArrayList<String>();
        
        BufferedReader br=null;
        try 
        { 
            br = new BufferedReader(new FileReader(file));
            String line = ""; 
            while ((line = br.readLine()) != null) 
            { 
                dataList.add(line);
            }
        }
        catch (Exception e) 
        {
        	
        }
        finally
        {
            if(br!=null)
            {
                try 
                {
                    br.close();
                    br=null;
                } 
                catch (IOException e) 
                {
                    e.printStackTrace();
                }
            }
        }
 
        return dataList;
    }

}
