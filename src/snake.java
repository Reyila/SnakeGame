
public class snake {
	
	int Laenge;
	int Leben;
	int Geschwindigkeit;
	Richtung kopRichtung;
	point [] snakePoints;

	
	public snake()
	{
		Laenge =5;
		Leben=3;
		Geschwindigkeit=10;
		kopRichtung=Richtung.links;
		snakePoints = new point[100];
		snakePoints[0].positionX=5;
		snakePoints[0].positionY=5;
		
		snakePoints[1].positionX=6;
		snakePoints[1].positionY=5;
		
		snakePoints[2].positionX=7;
		snakePoints[2].positionY=5;
		
		snakePoints[3].positionX=8;
		snakePoints[3].positionY=5;
		
		snakePoints[4].positionX=9;
		snakePoints[4].positionY=5;
		
	}
	private void pushPointAndRemoveLastPoint(int x, int y)
	{
		for (int i = Laenge-2; i>=0; i--)
		{
			snakePoints[i+1].positionX = snakePoints[i].positionX;
			snakePoints[i+1].positionY = snakePoints[i].positionY;	
		}
		snakePoints[0].positionX = x;
		snakePoints[0].positionX = y;
		
	}
	
	public void snakemove()
	{
		switch(kopRichtung)
		{
			case links:
			{
				this.pushPointAndRemoveLastPoint(this.snakePoints[0].positionX--,
												 this.snakePoints[0].positionY);
				break;
				
			}
			
			case rechts:
			{
				
				this.pushPointAndRemoveLastPoint(this.snakePoints[0].positionX++,
						 this.snakePoints[0].positionY);
				break;
			}
			case up:
			{
				this.pushPointAndRemoveLastPoint(this.snakePoints[0].positionX,
						 this.snakePoints[0].positionY++);
				break;
			}
			case Down:
			{
				this.pushPointAndRemoveLastPoint(this.snakePoints[0].positionX,
						 this.snakePoints[0].positionY--);
				break;
			}
			
		default:
			break;
				
		}
		
	}
	
	public boolean CheckGameOverBecauseOfWallHitting()
	
	{
		switch (snakePoints[0].positionY) {
		case 0:
		{
			return true;
		}
		
		case 10:
		{
			return true;
		}
			

		default:
		
		}
	
		
		switch (snakePoints[0].positionX) {
		case 0:
		{
			return true;
		}
		
		case 10:
		{
			return true;
		}
			

		default:
			
		}
		
		return false;
	}

	
	public boolean CheckGameOverBecauseOfBodyHitting()
	{
		
		for (int i = 1; i<Laenge; i++)
		{
			if((snakePoints[0].positionX == snakePoints[i].positionX)
				&& (snakePoints[0].positionY == snakePoints[i].positionY))
			{
				return true;
			}
		}
		
		return false;
	
	}
	public void linksClicked()
	{
		if(this.kopRichtung != Richtung.rechts) 
		{
			this.kopRichtung = Richtung.links;
		}
		
	}
	
	public void rechtsClicked()
	{
		if(this.kopRichtung != Richtung.links) 
		{
			this.kopRichtung = Richtung.rechts;
		}
	}
	
	public void upClicked()
	{
		if(this.kopRichtung != Richtung.down) 
		{
			this.kopRichtung = Richtung.up;
		}
	}
	public void downClicked()
	{
		if(this.kopRichtung != Richtung.up) 
		{
			this.kopRichtung = Richtung.Down;
		}
	}
	
	public void eatPoint(int x, int y)
	{
		for (int i = Laenge-1; i>=0; i--)
		{
			snakePoints[i+1].positionX = snakePoints[i].positionX;
			snakePoints[i+1].positionY = snakePoints[i].positionY;
		}
		snakePoints[0].positionX = x;
		snakePoints[0].positionY = y;
		Laenge++;
		
	}

}


