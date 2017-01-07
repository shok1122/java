package m3k.image.test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

import m3k.image.XCImgGraph;

public class XCImgMain extends JFrame implements ActionListener
{
	XCImgGraph m_graph1;
	XCImgGraph m_graph2;
	XCImgGraph m_graph3;
	Random m_rand1;
	Random m_rand2;
	Random m_rand3;
	Timer m_timer;

	public static void main(String[] args)
	{
		XCImgMain main = new XCImgMain();
		main.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});

		main.setBounds(0, 0, 460, 490);
		main.setVisible(true);
	}

	XCImgMain()
	{
		m_graph1 = new XCImgGraph(0, 20, -50, 150, 20, 50, 200, 200, Color.WHITE, Color.RED);
		m_graph2 = new XCImgGraph(0, 20, -50, 150, 240, 50, 200, 200, Color.WHITE, Color.GREEN);
		m_graph3 = new XCImgGraph(0, 20, -50, 150, 20, 270, 420, 200, Color.WHITE, Color.BLUE);

		m_graph1.disableAxisY();
		m_graph2.disableAxisY();
		m_graph3.disableAxisY();

		m_rand1 = new Random();
		m_rand2 = new Random();
		m_rand3 = new Random();

		m_timer = new Timer(1000, this);
		m_timer.start();
	}

	public void paint(Graphics in_graphics)
	{
		Graphics2D g2d = (Graphics2D) in_graphics;

		Dimension dim = getSize();
		g2d.setColor(getBackground());
		g2d.fillRect(0, 0, dim.width, dim.height);
		g2d.setColor(getForeground());

		m_graph1.update(g2d);
		m_graph2.update(g2d);
		m_graph3.update(g2d);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		int val1 = m_rand1.nextInt(m_graph1.m_maxY - m_graph1.m_minY) + m_graph1.m_minY;
		m_graph1.add(val1);

		int val2 = m_rand2.nextInt(m_graph2.m_maxY - m_graph2.m_minY) + m_graph2.m_minY;
		m_graph2.add(val2);

		int val3 = m_rand3.nextInt(m_graph3.m_maxY - m_graph3.m_minY) + m_graph3.m_minY;
		m_graph3.add(val3);

		repaint();
	}
}
