package m3k.image;

import java.awt.Color;

public class XCImgLineV extends XCImgLine
{

	XCImgLineV(int in_x1, int in_y1, int in_length, float in_width, Color in_color)
	{
		super(in_x1, in_y1, in_x1, in_y1 + in_length, in_width, in_color);
	}

}
