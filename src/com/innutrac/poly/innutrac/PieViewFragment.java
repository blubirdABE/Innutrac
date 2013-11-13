package com.innutrac.poly.innutrac;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


public class PieViewFragment extends Fragment {
	LinearLayout pieContainer;
	int factor = 1;
	private PieView pie;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		return inflater.inflate(R.layout.fragment_pie, null);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
        pieContainer = (LinearLayout) getView().findViewById(R.id.pie_container_id);
        pie = new PieView(getActivity());
        pie.setMode(factor);
        pieContainer.addView(pie);
        
        pieContainer.setOnTouchListener(new View.OnTouchListener() 
      {
              
              @Override
              public boolean onTouch(View v, MotionEvent event) 
              {
                      float x = event.getX();
                      float y = event.getY();
                      pie.wedgeDetect(x, y); // will send touch location
                      // to PieView and appropriately change to detail view
                      return false;
              }
      });
	}
}