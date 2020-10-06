
public class snake {
	
	private int Laenge;
	private int geschwindigkeit;
	public final int xPointScale = 20;
	public final int yPointScale = 20;
	private Richtung kopfRichtung;
	public point [] snakePoints;
	
	public int getLaenge()
	{
		return Laenge;
	}
	
	public int getGeschwindigkeit()
	{
		return geschwindigkeit;
	}

	
	public snake()  // Konstruktoren mit Attributen initialisierung
	{
		Laenge =5;
		kopfRichtung=Richtung.links;
		geschwindigkeit = 0;
		snakePoints = new point[xPointScale*yPointScale];
		snakePoints[0] = new point(4, 5);
		snakePoints[1] = new point(5, 5);
		snakePoints[2] = new point(6, 5);
		snakePoints[3] = new point(7, 5);
		snakePoints[4] = new point(8, 5);
		snakePoints[5] = new point(9, 5);
		// erste Körper von snake in kästchen Darstellung 
	
	}
	
	//    ------        ------
	//   | 4,5  |---   | 3,5  |
	//    ------    |   ------
	//     5,5       --| 4,5  |
	//    Laenge -2 wird hier dementsprechend in den Array geändert
	private void pushPointAndRemoveLastPoint(int x, int y) //snake
	{
		for (int i = Laenge-1; i>=0; i--) // i>=0 bedeutet das es 
			// nicht auf -0 gehen kann und nicht mehr wie 4 Zeile hat!
			// i-- bedeutet das es durch remove/bewegung unten snake immer gelöscht wird.	
		{
			snakePoints[i+1].positionX = snakePoints[i].positionX;
			snakePoints[i+1].positionY = snakePoints[i].positionY;	
		}
		snakePoints[0].positionX = x; 
		snakePoints[0].positionY = y;
		
	}
	
	public void snakemove()
	{
		switch(kopfRichtung)
		{
			case links:
			{
				this.pushPointAndRemoveLastPoint(this.snakePoints[0].positionX - 1,
												 this.snakePoints[0].positionY);
				break;
				
			}
			
			case rechts:  // snake kopf berechnung  
			{
				
				this.pushPointAndRemoveLastPoint(this.snakePoints[0].positionX + 1,
						 this.snakePoints[0].positionY);
				break;
			}
			case up:
			{
				this.pushPointAndRemoveLastPoint(this.snakePoints[0].positionX,
						 this.snakePoints[0].positionY - 1);
				break;
			}
			case Down:
			{
				this.pushPointAndRemoveLastPoint(this.snakePoints[0].positionX,
						 this.snakePoints[0].positionY + 1);
				break;
			}
			
		default:
			break;
				
		}
		
	}
	
	public boolean CheckGameOverBecauseOfWallHitting()
	
	{
		if(snakePoints[0].positionY < 0) 
		{
			return true;
		}
		
		if(snakePoints[0].positionY > this.yPointScale-1) 
		{
			return true;
		}
		if(snakePoints[0].positionX < 0) 
		{
			return true;
		}
		
		if(snakePoints[0].positionX > this.xPointScale-1) 
		{
			return true;
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
	
	public boolean CheckFoodEaten(point food)
	{
		
		for (int i = 0; i<Laenge; i++)
		{
			if((food.positionX == snakePoints[i].positionX)
				&& (food.positionY == snakePoints[i].positionY))
			{
				return true;
			}
		}
		
		return false;
	// wenn der Punkt gegessen wird dann ist es in körper 
		// wenn nicht dann ist es falsch 
	}
	
	public boolean CheckFoodAboutToEat(point food)
	{
		switch(kopfRichtung)
		{
			case links:
			{
				if((food.positionY == snakePoints[0].positionY) && 
						(food.positionX == snakePoints[0].positionX-1))
				{
					return true;
				}
				return false;
			}
			
			case rechts:
			{
				
				if((food.positionY == snakePoints[0].positionY) && 
						(food.positionX == snakePoints[0].positionX+1))
				{
					return true;
				}
				return false;
			}
			case up:
			{
				if((food.positionY == snakePoints[0].positionY-1) && 
						(food.positionX == snakePoints[0].positionX))
				{
					return true;
				}
				return false;
			}
			case Down:
			{
				if((food.positionY == snakePoints[0].positionY+1) && 
						(food.positionX == snakePoints[0].positionX))
				{
					return true;
				}
				return false;
			}
			
		default:
			break;
				
		}
		return false;
	
	}
	public void linksClicked()
	{
		if(this.kopfRichtung != Richtung.rechts) 
		{
			this.kopfRichtung = Richtung.links;
		}
		
	}
	
	public void rechtsClicked()
	{
		if(this.kopfRichtung != Richtung.links) 
		{
			this.kopfRichtung = Richtung.rechts;
		}
	}
	
	public void upClicked()
	{
		if(this.kopfRichtung != Richtung.Down) 
		{
			this.kopfRichtung = Richtung.up;
		}
	}
	public void downClicked()
	{
		if(this.kopfRichtung != Richtung.up) 
		{
			this.kopfRichtung = Richtung.Down;
		}
	}
	
	public void eatPoint(int x, int y)
	{
		snakePoints[Laenge+1] = new point(snakePoints[Laenge-1].positionX, 
				                  snakePoints[Laenge-1].positionY);
		for (int i = Laenge; i>=0; i--)
		{
			snakePoints[i+1].positionX = snakePoints[i].positionX;
			snakePoints[i+1].positionY = snakePoints[i].positionY;
		}
		snakePoints[0].positionX = x;
		snakePoints[0].positionY = y;
		Laenge++;
		geschwindigkeit=geschwindigkeit+10;
		
	}

}


