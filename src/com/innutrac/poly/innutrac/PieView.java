package com.innutrac.poly.innutrac;

import com.innutrac.poly.innutrac.PieWedge;
import com.innutrac.poly.innutrac.R;
import com.innutrac.poly.innutrac.database.*;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;

public class PieView extends View {
	private int drawMode;
	private Bitmap overlayBitmap;
	private int overlayWidth;
	private float adjustY;
	
	private PieWedge green;
	private PieWedge blue;
	private PieWedge purple;
	private PieWedge pink;
	private PieWedge red;
	private PieWedge orange;
	private PieWedge yellow;
	
	private boolean inside;
	
	int dimX, dimY;

	public PieView(Context context) {
		super(context);
		overlayBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.piechart_shade, null);
		
		
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		dimX = size.x;
		dimY = size.y;

		inside = false;
	}

	public PieView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Paint mBgPaints = new Paint();
		mBgPaints.setAntiAlias(true);
		mBgPaints.setStyle(Paint.Style.FILL); // look further into this
		mBgPaints.setColor(Color.BLUE);
		mBgPaints.setStrokeWidth(0.5f);
		
		// Here there will be a data fetch for 8 proportion values (percentages)
		// The following is sample data:
		float greenVal, blueVal, purpleVal, pinkVal, redVal, orangeVal, yellowVal, drkGreenVal;
		greenVal = 11;
		blueVal = 20;
		purpleVal = 11;
		pinkVal = 12;
		redVal = 8;
		orangeVal = 20;
		yellowVal = 8;
		drkGreenVal = 10;

		greenVal = (greenVal / 100) * 360;
		blueVal = (blueVal / 100) * 360;
		purpleVal = (purpleVal / 100) * 360;
		pinkVal = (pinkVal / 100) * 360;
		redVal = (redVal / 100) * 360;
		orangeVal = (orangeVal / 100) * 360;
		yellowVal = (yellowVal / 100) * 360;
		drkGreenVal = (drkGreenVal / 100) * 360;

		float angle = 360;

		// Wedge data allocation
		angle -= greenVal;
		green = new PieWedge(angle, greenVal, "#07B708");
		angle -= blueVal;
		blue = new PieWedge(angle, blueVal, "#0083FC");
		angle -= purpleVal;
		purple = new PieWedge(angle, purpleVal, "#8907B4");
		angle -= pinkVal;
		pink = new PieWedge(angle, pinkVal, "#FCA8F1");
		angle -= redVal;
		red = new PieWedge(angle, redVal, "#EC0808");
		angle -= orangeVal;
		orange = new PieWedge(angle, orangeVal, "#F89F28");
		angle -= yellowVal;
		yellow = new PieWedge(angle, yellowVal, "#F8F628");
		angle -= drkGreenVal;
		// This is roughly where the overlay wedge drawing should occur

		Paint tPaint = new Paint();
		tPaint.setAlpha(0);
		canvas.drawColor(tPaint.getColor());
		
		int scale = dimX - (dimX / 10);
		overlayBitmap = Bitmap.createScaledBitmap(overlayBitmap, scale, scale, true);
		overlayWidth = overlayBitmap.getWidth();

		RectF mOvals = new RectF(0, 0, overlayWidth, overlayWidth);
		
		float yFactor = (float) (overlayWidth / 1.5);
		adjustY = (dimY / 2) - yFactor;
		mOvals.offsetTo(((dimX / 2) - (overlayWidth / 2)), adjustY);
		

		if (drawMode == 1) {
			// dark green
			mBgPaints.setColor(Color.parseColor(green.getColor()));
			canvas.drawArc(mOvals, green.getStartAngle(), green.getSweep(),
					true, mBgPaints);

			// blue
			mBgPaints.setColor(Color.parseColor(blue.getColor()));
			canvas.drawArc(mOvals, blue.getStartAngle(), blue.getSweep(), true,
					mBgPaints);

			// purple
			mBgPaints.setColor(Color.parseColor(purple.getColor()));
			canvas.drawArc(mOvals, purple.getStartAngle(), purple.getSweep(),
					true, mBgPaints);

			// pink
			mBgPaints.setColor(Color.parseColor(pink.getColor()));
			canvas.drawArc(mOvals, pink.getStartAngle(), pink.getSweep(), true,
					mBgPaints);

			// red
			mBgPaints.setColor(Color.parseColor(red.getColor()));
			canvas.drawArc(mOvals, red.getStartAngle(), red.getSweep(), true,
					mBgPaints);

			// orange
			mBgPaints.setColor(Color.parseColor(orange.getColor()));
			canvas.drawArc(mOvals, orange.getStartAngle(), orange.getSweep(),
					true, mBgPaints);

			// yellow
			mBgPaints.setColor(Color.parseColor(yellow.getColor()));
			canvas.drawArc(mOvals, yellow.getStartAngle(), yellow.getSweep(),
					true, mBgPaints);

			// green
			mBgPaints.setColor(Color.GREEN);
			canvas.drawArc(mOvals, angle, drkGreenVal, true, mBgPaints);

		} else {
			canvas.drawArc(mOvals, 270, 140, true, mBgPaints);
			mBgPaints.setColor(Color.YELLOW);
			canvas.drawArc(mOvals, 50, 40, true, mBgPaints);
			mBgPaints.setColor(Color.parseColor("#574200"));
			canvas.drawArc(mOvals, 90, 40, true, mBgPaints);
			mBgPaints.setColor(Color.parseColor("#0085BA"));
			canvas.drawArc(mOvals, 130, 50, true, mBgPaints);
			mBgPaints.setColor(Color.parseColor("#F59C3D"));
			canvas.drawArc(mOvals, 180, 90, true, mBgPaints);
		}
		canvas.drawBitmap(overlayBitmap, ((dimX / 2) - (overlayWidth / 2)), adjustY, null);
	}
	
	public void wedgeDetect(float x, float y)// Handle wedge tap detection here
	{
		int width = this.getWidth(); // this is the width of the pieChart!!

		int yCoord = (int) ((dimY / 2) - adjustY);
		double distance = Math.sqrt(Math.pow((x - (dimX / 2)), 2) + Math.pow((y - yCoord), 2));

		// radius of circle is the width / 2
		int radius = width / 2;
		double tapAngle = 0;
		
		if (distance <= radius) 
		{
			inside = true;
			// need the tangent and adjacent values as distances
			double xDistance = x - (dimX / 2);
			double yDistance = yCoord - y;
			
			tapAngle = Math.toDegrees(Math.atan2(yDistance , xDistance));
			
			// must do this to comply with domain constraints of atan2 function
			if (tapAngle < 0)
			{
				tapAngle += 360;
			}
		}else
		{
			inside = false;
		}

		// cross reference wedge data
		if (tapAngle < (360 - green.getStartAngle()) && 
				tapAngle >= ((360 - green.getStartAngle()) - green.getSweep()) && 
				inside)
		{
			System.out.println("You just clicked green!!!!!!!!");
		}else
		if (tapAngle < (360 - blue.getStartAngle()) && 
				tapAngle >= ((360 - blue.getStartAngle()) - blue.getSweep()) && 
				inside)
		{
			System.out.println("You just clicked blue!!!!!!!!");
		}else
		if (tapAngle < (360 - purple.getStartAngle()) && 
				tapAngle >= ((360 - purple.getStartAngle()) - purple.getSweep()) && 
				inside)
		{
			System.out.println("You just clicked purple!!!!!!!!");
		}else
		if (tapAngle < (360 - pink.getStartAngle()) && 
				tapAngle >= ((360 - pink.getStartAngle()) - pink.getSweep()) && 
				inside)
		{
			System.out.println("You just clicked pink!!!!!!!!");
		}else
		if (tapAngle < (360 - red.getStartAngle()) && 
				tapAngle >= ((360 - red.getStartAngle()) - red.getSweep()) && 
				inside)
		{
			System.out.println("You just clicked red!!!!!!!!");
		}else
		if (tapAngle < (360 - orange.getStartAngle()) && 
				tapAngle >= ((360 - orange.getStartAngle()) - orange.getSweep()) && 
				inside)
		{
			System.out.println("You just clicked orange!!!!!!!!");
		}else
		if (tapAngle < (360 - yellow.getStartAngle()) && 
				tapAngle >= ((360 - yellow.getStartAngle()) - yellow.getSweep()) && 
				inside)
		{
			System.out.println("You just clicked yellow!!!!!!!!");
		}else
		if (inside)
		{
			System.out.println("You just clicked light green!!!!!!!!");
		}
	}

	public void setMode(int mode) {
		drawMode = mode;
	}
}
