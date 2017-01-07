package m3k.image;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class XCImgArea extends XCImgObject
{
	int m_startX;
	int m_startY;
	int m_width;
	int m_height;
	Color m_colorArea;
	Color m_colorBorder;

	XCImgPoint m_leftDown;
	XCImgPoint m_leftUp;
	XCImgPoint m_rightDown;
	XCImgPoint m_rightUp;

	XCImgArea(int in_startX, int in_startY, int in_width, int in_height,
			Color in_colorArea, Color in_colorBorder)
	{
		m_startX = in_startX;
		m_startY = in_startY;
		m_width = in_width;
		m_height = in_height;
		m_colorArea = in_colorArea;
		m_colorBorder = in_colorBorder;

		m_leftDown  = new XCImgPoint(m_startX,           m_startY + m_height);
		m_leftUp    = new XCImgPoint(m_startX,           m_startY);
		m_rightDown = new XCImgPoint(m_startX + m_width, m_startY + m_height);
		m_rightUp   = new XCImgPoint(m_startX + m_width, m_startY);
	}

	@Override
	void update(Graphics2D in_g2d)
	{
		in_g2d.setColor(m_colorArea);

		in_g2d.setColor(m_colorBorder);
		in_g2d.setStroke(new BasicStroke(4));
		in_g2d.drawRect(m_startX, m_startY, m_width, m_height);

		super.notify(in_g2d);
	}

	int getPosX(double in_x)
	{
		return (int) ((double) getPosLeft() + in_x);
	}

	int getPosY(double in_y)
	{
		return (int) ((double) getPosUnder() - in_y);
	}

	int getPosUnder()
	{
		return m_leftDown.y;
	}

	int getPosLeft()
	{
		return m_leftDown.x;
	}

	int getPosUp()
	{
		return m_rightUp.y;
	}

	int getPosRight()
	{
		return m_rightUp.x;
	}

}
