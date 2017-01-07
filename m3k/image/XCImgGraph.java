package m3k.image;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.LinkedList;

public class XCImgGraph extends XCImgArea
{
	public int m_maxX;
	public int m_minX;
	public int m_maxY;
	public int m_minY;
	public int m_width;
	public int m_interval;
	public int m_numHistory;
	LinkedList<Integer> m_history;

	XCImgLineH m_lineX;
	XCImgLineV m_lineY;

	boolean m_bEnableAxisX;
	boolean m_bEnableAxisY;

	public XCImgGraph(int in_minX, int in_maxX, int in_minY, int in_maxY,
			int in_startX, int in_startY, int in_width, int in_height,
			Color in_colorArea, Color in_colorBorder)
	{
		super(in_startX, in_startY, in_width, in_height, in_colorArea, in_colorBorder);

		m_maxX = in_maxX;
		m_minX = in_minX;
		m_maxY = in_maxY;
		m_minY = in_minY;
		m_width = in_width;
		m_interval = m_width / in_maxX;
		m_numHistory = m_maxX - m_minX + 1;
		m_history = new LinkedList<Integer>();

		m_lineX = new XCImgLineH(super.getPosLeft(), getPosZeroY(), super.m_width, 1.0F, Color.BLACK);
		m_lineY = new XCImgLineV(getPosZeroX(), super.getPosUp(), super.m_height, 1.0F, Color.BLACK);

		m_bEnableAxisX = true;
		m_bEnableAxisY = true;
	}

	public void add(int in_val)
	{
		if (m_numHistory <= m_history.size())
		{
			m_history.pollLast();
		}

		m_history.push(in_val);

	}

	public int getPosZeroX()
	{
		double ratio = ((double) 0 - (double) m_minX) / ((double) m_maxX - (double) m_minX);
		return super.getPosX(ratio * (double) super.m_width);
	}

	public int getPosZeroY()
	{
		double ratio = ((double) 0 - (double) m_minY) / ((double) m_maxY - (double) m_minY);
		return super.getPosY(ratio * (double) super.m_height);
	}

	public int getPosY(int in_val)
	{
		double ratio = ((double) in_val - (double) m_minY) / ((double) m_maxY - (double) m_minY);
		return super.getPosY(ratio * (double) super.m_height);
	}

	public void enableAxisX()
	{
		m_bEnableAxisX = true;
	}

	public void disableAxisX()
	{
		m_bEnableAxisX = false;
	}

	public void enableAxisY()
	{
		m_bEnableAxisY = true;
	}

	public void disableAxisY()
	{
		m_bEnableAxisY = false;
	}

	@Override
	public void update(Graphics2D in_g2d)
	{
		super.update(in_g2d);

		if (true == m_bEnableAxisX)
		{
			m_lineX.update(in_g2d);
		}
		if (true == m_bEnableAxisY)
		{
			m_lineY.update(in_g2d);
		}

		int x = 0;
		LinkedList<Integer> tmp = new LinkedList<Integer>();
		for (int i = 0; i < m_history.size(); i++)
		{
			if (2 <= tmp.size())
			{
				tmp.pollLast();
			}

			tmp.push(m_history.get(m_history.size() - 1 - i));

			if (2 <= tmp.size())
			{
				XCImgLine line = new XCImgLine(super.getPosX(x), this.getPosY(tmp.get(1)), super.getPosX(x + m_interval), this.getPosY(tmp.get(0)), Color.BLACK);
				line.update(in_g2d);
				x += m_interval;
			}
		}
	}
}
