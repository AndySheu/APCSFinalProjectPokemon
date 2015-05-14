import java.awt.*;
import javax.swing.*;

public class Pokemon
{
	private Image image;
	private double x;
	private double y;
	private double vx;
	private double vy;
	
	public Pokemon(Image img, double xPos, double yPos)
	{
		image = img;
		x = xPos;
		y = yPos;
		vx = vy = 0;
	}
	
	public Pokemon(String path, double xPos, double yPos)
	{
		this(new ImageIcon(path).getImage(), xPos, yPos);	
	}
	
	public void setPosition(double xPos, double yPos)
	{
		x = xPos;
		y = yPos;
	}
	
	public void setImage(Image img)
	{
		image = img;
	}
	
	public void setVelocity(double newVx, double newVy)
	{
		vx = newVx;
		vy = newVy;
	}
	
	public void incrementPosition()
	{
	    x += vx;
	    y += vy;
	}
	
	public void reflect(boolean side)
	{
		if(side)
			vx = -1 * vx;
		else
			vy = -1 * vy;
	}
	
	public double getX()
	{
		return x;
	}
	
	public double getY()
	{
		return y;
	}
	
	public Image getImage()
	{
		return image;
	}
}