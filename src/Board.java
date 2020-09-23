import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.xml.catalog.Catalog;

import com.sun.media.sound.Toolkit;

public class Board  extends JPanel implements ActionListener{
	
	public snake snake;
	public Image Cat;
	public Timer timer;
	public final int DELAY=140;
	private class TAdapter extends KeyAdapter
	{
		public void KeyPressed(KeyEvent e)
		{
			int key = e.getKeyCode();
			switch (key)
			{
				case KeyEvent.VK_LEFT:
					snake.linksClicked();
					break;
					
				case KeyEvent.VK_RIGHT:
					snake.rechtsClicked();
					break;
					
				case KeyEvent.VK_UP:
					snake.upClicked();
					break;
					
				case KeyEvent.VK_DOWN:
					snake.downClicked();
					break;	
			}
		}
	}
	
	public Board()
	{
		snake = new snake();
		addKeyListener(new TAdapter());
		setBackground(Color.orange);
		setFocusable(false);
		loadimages();
		timer = new Timer(DELAY, this);
		timer.start();
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
	}
	public void doDrawing(Graphics g)
	{
		g.drawImage(Cat, 100,100,this);
		java.awt.Toolkit.getDefaultToolkit().sync();
	}
	
	public void loadimages()
	{
		ImageIcon catIcon = new ImageIcon("src/katze.jpg");
		Cat = catIcon.getImage(); 
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JFrame jf = new JFrame();
		jf.setVisible(true);
		jf.setSize(1000,1000);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLocationRelativeTo(null);
		jf.setTitle("Snake Game");
		jf.setResizable(false);	
		
		jf.add(new Board());
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
