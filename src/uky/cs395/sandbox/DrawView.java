package uky.cs395.sandbox;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class DrawView extends View {
	
	public static final String TAG = "DrawView";
	private Paint backgroundColor;
	
	public DrawView(Context c, AttributeSet as) {
		super(c, as);
		backgroundColor = new Paint();
		backgroundColor.setColor(Color.BLACK);
	}
	
	public void onDraw(Canvas c) {
		c.drawPaint(backgroundColor);
	}

}
