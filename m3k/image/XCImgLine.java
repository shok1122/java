package m3k.image;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class XCImgLine extends XCImgObject
{
	int m_x1;
	int m_y1;
	int m_x2;
	int m_y2;
	float m_width;
	Color m_color;

	XCImgLine(int in_x1, int in_y1, int in_x2, int in_y2, Color in_color)
	{
		build(in_x1, in_y1, in_x2, in_y2, 1.0F, in_color);
	}

	XCImgLine(int in_x1, int in_y1, int in_x2, int in_y2, float in_width, Color in_color)
	{
		build(in_x1, in_y1, in_x2, in_y2, in_width, in_color);
	}

	void build(int in_x1, int in_y1, int in_x2, int in_y2, float in_width, Color in_color)
	{
		m_x1 = in_x1;
		m_y1 = in_y1;
		m_x2 = in_x2;
		m_y2 = in_y2;
		m_width = in_width;
		m_color = in_color;
	}

	@Override
	void update(Graphics2D in_g2d)
	{
		in_g2d.setColor(m_color);
		in_g2d.setStroke(new BasicStroke(m_width));
		in_g2d.drawLine(m_x1, m_y1, m_x2, m_y2);

		super.notify(in_g2d);
	}

}
