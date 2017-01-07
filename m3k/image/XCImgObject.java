package m3k.image;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

abstract class XCImgObject
{
	List<XCImgObject> m_objs = new ArrayList<XCImgObject>();

	abstract void update(Graphics2D in_g2d);

	void notify(Graphics2D in_g2d)
	{
		for (int i = 0; i < m_objs.size(); i++)
		{
			m_objs.get(i).update(in_g2d);;
		}
	}

	void add(XCImgObject in_obj)
	{
		m_objs.add(in_obj);
	}
}
