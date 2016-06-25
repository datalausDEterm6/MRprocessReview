package Property;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class ReivewWritable implements Writable {

	private String text;
	private String stars;
	private int posLabel;
	private int negLabel;
	
	
	public void readFields(DataInput in) throws IOException {
		// TODO Auto-generated method stub
		text = in.readUTF();  
		stars =  in.readUTF();
	}

	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		out.writeUTF(text);
		out.writeUTF(stars);
	}

	public void SetName(String name)
	{
		this.text = name;
	}
	
	public void SetStars(String stars)
	{
		this.stars = stars;
	}
	
	public String GetName()
	{
		return text;
	}
	
	public String GetStars()
	{
		return stars;
	}
	
	public void SetPosLabel(int posLabel)
	{
		this.posLabel = posLabel;
	}
	
	public void SetNegLabel(int negLabel)
	{
		this.negLabel = negLabel;
	}
	
	public int GetPosLabel()
	{
		return posLabel;
	}
	
	public int GetNegLabel()
	{
		return negLabel;
	}
}
