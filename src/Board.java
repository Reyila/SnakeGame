import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.Random;
import java.awt.event.KeyEvent;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Board  extends JPanel implements ActionListener{
	
	private snake snake;
	private Image Cat;
	private Image ball;
	private Image food;
	private Timer timer;
	private point foodPoint;
	private static final int DELAY=400;
	private static final int BOARDWIDTH= 500;
	private static final int BOARDHIGHT= 500;
	private int xScale;
	private int yScale;
	private Boolean isGameOver=false;
	
	public Board() // Konstruktoren angelegt 
	{
		snake = new snake();
		xScale = BOARDWIDTH/snake.xPointScale; // pixelberechnung 
		yScale = BOARDHIGHT/snake.yPointScale;
		locateFood(); // methode die Food lokalisiert
		setBackground(Color.ORANGE); // Es ist geerbt von JPanel 
		
		setSize(BOARDWIDTH, BOARDHIGHT); // kommt von JPanel
		loadimages(); // in Board Bilder eingefügt 
		timer = new Timer(DELAY, this);
		timer.start();
	}
	public void locateFood()
	{
		Random rand = new Random();
		int foodPosX = rand.nextInt(9);
		int foodPosY = rand.nextInt(9);
		foodPoint = new point(foodPosX, foodPosY);
		while(snake.CheckFoodEaten(foodPoint))
		{
			foodPosX = rand.nextInt(9);
			foodPosY = rand.nextInt(9);
			foodPoint.setPoint(foodPosX, foodPosY);
		}
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if(this.isGameOver)
		{
			this.gameOver(g);
		}
		else 
		{
			doDrawing(g);
		}	
	}
    private void gameOver(Graphics g) 
    {
        
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.black);
        g.setFont(small);
        g.drawString(msg, (BOARDWIDTH - metr.stringWidth(msg)) / 2, BOARDHIGHT / 2);
    }
	public void doDrawing(Graphics g)
	{
		g.drawImage(food, foodPoint.positionX*xScale,
				foodPoint.positionY*yScale,xScale,yScale,this);
		g.drawImage(Cat, snake.snakePoints[0].positionX*xScale,
				snake.snakePoints[0].positionY*yScale,xScale,yScale,this);
		for (int z = 1; z < snake.getLaenge(); z++) 
		{
			g.drawImage(ball, snake.snakePoints[z].positionX*xScale,snake.snakePoints[z].positionY*yScale,xScale,yScale,this);
        }
		java.awt.Toolkit.getDefaultToolkit().sync();
	}
	
	public void loadimages() // liegt neue Objekte für alle Bilder 
	// jeder Objekt ImageIcon ruft die Konstruktoren von ImageIcon 
	{
		ImageIcon catIcon = new ImageIcon("src/katze.jpg"); 
		Cat = catIcon.getImage(); 
		ImageIcon ballIcon = new ImageIcon("src/ball.jpg");
		ball = ballIcon.getImage(); 
		ImageIcon foodIcon = new ImageIcon("src/food.jpg");
		food = foodIcon.getImage(); 
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JFrame jf = new JFrame();
		jf.setVisible(true);
		jf.setSize(BOARDWIDTH,BOARDHIGHT);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLocationRelativeTo(null);  // Null ist mitte, Fenster soll in der mitte erscheinen
		jf.setTitle("Reyila Snake Game");
		jf.setResizable(false);	// Fenstere kleiner und größer machen wollen
		Board theboard = new Board();
		jf.add(theboard);
		jf.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) { // eine Methode von Interface
				// Interface: enthält Methoden und Konstanten
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				int keycode = e.getKeyCode();
				switch (keycode)
				{
					case KeyEvent.VK_LEFT:
						theboard.snake.linksClicked();
						break;
						
					case KeyEvent.VK_RIGHT:
						theboard.snake.rechtsClicked();
						break;
						
					case KeyEvent.VK_UP:
						theboard.snake.upClicked();
						break;
						
					case KeyEvent.VK_DOWN:
						theboard.snake.downClicked();
						break;	
				}
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}});
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		
		
		if(snake.CheckFoodAboutToEat(foodPoint))
		{
			snake.eatPoint(foodPoint.positionX, foodPoint.positionY);
			this.locateFood();
			timer.setDelay(DELAY - snake.getGeschwindigkeit()); 
			repaint();
			return;
		}
		else
		{
			snake.snakemove();
		}
		if(snake.CheckGameOverBecauseOfBodyHitting()||  // oder 
				snake.CheckGameOverBecauseOfWallHitting())
		{
			this.isGameOver = true;
			return;
		}
		
        repaint();
	}

}
