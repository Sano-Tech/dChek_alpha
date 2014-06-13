package com.sanotech.dchek;

import java.io.IOException;
import java.sql.SQLException;

import com.sanotech.dchek.adapters.DatabaseAdapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class DashBoard extends Activity implements OnRefreshListener,
		OnClickListener {
	SwipeRefreshLayout swipeLayout;
	LinearLayout bgLevelValues,drAppointment,calorieDetails;
	ImageView imgUser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard_activity);
		imgUser = (ImageView) findViewById(R.id.img_user);

		swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
		swipeLayout.setOnRefreshListener(this);
		swipeLayout.setColorScheme(android.R.color.holo_blue_bright,
				android.R.color.holo_green_light,
				android.R.color.holo_orange_light,
				android.R.color.holo_red_light);

		bgLevelValues = (LinearLayout) findViewById(R.id.ll_bg_values);
		bgLevelValues.setOnTouchListener(mTouchListener);
		
		drAppointment = (LinearLayout)findViewById(R.id.ll_dr_values);
		drAppointment.setOnTouchListener(mTouchListener);

		calorieDetails = (LinearLayout)findViewById(R.id.ll_cl_values);
		calorieDetails.setOnTouchListener(mTouchListener);
		imgUser.setOnClickListener(this);
		
		DatabaseAdapter dataBaseAdapter = new DatabaseAdapter(DashBoard.this);
		try {
			dataBaseAdapter.createDataBase();
			dataBaseAdapter.openDataBase();
			dataBaseAdapter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onRefresh() {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				swipeLayout.setRefreshing(false);
			}
		}, 5000);
	}

	boolean mItemPressed = false;
	boolean mSwiping = false;
	int SWIPE_DURATION = 250;
	int MOVE_DURATION = 150;
	View.OnTouchListener mTouchListener = new View.OnTouchListener() {

		float mDownX;
		private int mSwipeSlop = -1;

		@SuppressLint("NewApi")
		@Override
		public boolean onTouch(final View v, MotionEvent event) {
			if (mSwipeSlop < 0) {
				mSwipeSlop = ViewConfiguration.get(DashBoard.this)
						.getScaledTouchSlop();
			}
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				if (mItemPressed) {
					// Multi-item swipes not handled
					return false;
				}
				mItemPressed = true;
				mDownX = event.getX();
				break;
			case MotionEvent.ACTION_CANCEL:
				v.setAlpha((float) 1.0);
				v.setTranslationX((float) 0.0);
				mItemPressed = false;
				break;
			case MotionEvent.ACTION_MOVE: {
				swipeLayout.setEnabled(false);
				float x = event.getX() + v.getTranslationX();
				float deltaX = x - mDownX;
				float deltaXAbs = Math.abs(deltaX);
				// this allow animation from left to right
				if (deltaX > 0) {
					if (!mSwiping) {
						if (deltaXAbs > mSwipeSlop) {
							mSwiping = true;

							// ll_bg_level.requestDisallowInterceptTouchEvent(true);
							// mBackgroundContainer.showBackground(v.getTop(),
							// v.getHeight());
						}
					}
					if (mSwiping) {
						v.setTranslationX((x - mDownX));
						v.setAlpha(1 - deltaXAbs / v.getWidth());
					}
				}
			}
				break;
			case MotionEvent.ACTION_UP: {
				swipeLayout.setEnabled(true);
				// User let go - figure out whether to animate the view out, or
				// back into place
				if (mSwiping) {
					float x = event.getX() + v.getTranslationX();
					float deltaX = x - mDownX;
					float deltaXAbs = Math.abs(deltaX);
					float fractionCovered;
					float endX;
					float endAlpha;
					final boolean remove;
					if (deltaXAbs > v.getWidth() / 4) {
						// Greater than a quarter of the width - animate it out
						fractionCovered = deltaXAbs / v.getWidth();
						endX = deltaX < 0 ? -v.getWidth() : v.getWidth();
						endAlpha = 0;
						remove = true;
					} else {
						// Not far enough - animate it back
						fractionCovered = 1 - (deltaXAbs / v.getWidth());
						endX = 0;
						endAlpha = 1;
						remove = false;
					}
					// Animate position and alpha of swiped item
					// NOTE: This is a simplified version of swipe behavior, for
					// the
					// purposes of this demo about animation. A real version
					// should use
					// velocity (via the VelocityTracker class) to send the item
					// off or
					// back at an appropriate speed.
					long duration = (int) ((1 - fractionCovered) * SWIPE_DURATION);
					// ll_bg_level.setEnabled(false);
					v.animate().setDuration(duration).alpha(endAlpha)
							.translationX(endX).withEndAction(new Runnable() {
								@Override
								public void run() {
									// Restore animated values
									v.setAlpha((float) 1.0);
									v.setTranslationX(0);
									if (remove) {
										// animateRemoval(mListView, v);
										// ll_bg_level.setEnabled(true);
										if (v.getId() == R.id.ll_bg_values) {
											startActivity(new Intent(DashBoard.this, BGLevel.class));

										}else if (v.getId() == R.id.ll_dr_values) {
										  startActivity(new Intent(DashBoard.this,DoctorInfo.class));
										}else if (v.getId() == R.id.ll_cl_values) {
											startActivity(new Intent(DashBoard.this,UserCustomeFoodList.class));
										}
										 
									} else {
										mSwiping = false;
									
									}
								}
							});
				}
			}
				mItemPressed = false;
				break;
			default:
				return false;
			}
			return true;
		}
	};

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.img_user:
			startActivity(new Intent(DashBoard.this, UserInfo.class));
			break;

		default:
			break;
		}

	}

}
