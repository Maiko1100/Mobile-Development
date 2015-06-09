package com.testapplication.wfcmainpage.activity;

/**
 * Created by Nick Zwaans on 3-3-2015.
 * Provides de facility page met hierin de faciliteiten van het world fashion centre,
 * Also holds the search filter.
 */

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.testapplication.wfcmainpage.R;
import com.testapplication.wfcmainpage.adapters.FacilityAdapter;
import com.testapplication.wfcmainpage.database.MyDatabase;
import com.testapplication.wfcmainpage.models.Facility;

import java.util.ArrayList;
import java.util.List;


public class FacilitiesActivity extends ActionBarActivity {

	/**
	 * @param mFacilityAdapter Provides a custom adapter to put the items from the database to the list.
	 * @param mFacilityList Provides a listview for the page.
	 * @param mFacilities Provides an arraylist in which the Facility items are stored.
	 * @param mFacilityModeCategories Provides an arraylist where all the categories that a store has are specified.
	 * @param mDB Provides an Database where all the facilities are stored in
	 * @param mSearchInput Provides and inputfield (Edittext) for searching.
	 * @param mItems Provides a list for the facility items. data gets added from the database.
	 * @param mIsSearchActive Provides a boolean for opening and closing the searchInput.
	 * @param inputMethodManager Provides a show / hide for the softkeyboard.
	 * @param mClearText Provides a button to clear the text in de inputsearch
	 * @param mModeArray Provides an array with all the mode categories which is used to fill the mFacilityMode.
	 * @param mModeCategories Provides an array with all the drawerlist items.
	 * @param mDrawerList Provides an listview with all the items from the mModeCategories array.
	 * @param mTitle Provides a textview with the title of this activity.
	 */

	private FacilityAdapter mFacilityAdapter;
	private ListView mFacilityList;
	private ArrayList<Facility> mFacilities = new ArrayList<>();
	private ArrayList<Facility> mFacilityModeCategories = new ArrayList<>();
	private List<Facility> mItems;
	private MyDatabase mDb;
	private EditText mSearchInput;
	private TextView mTitle;
	private Button mClearText;
	private Menu menu;
	private boolean mIsSearchActive;
	private String[] mModeArray;
	private ArrayList<String> mFacilityMode = new ArrayList<>();
	private String[] mModeCategories;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private TransitionDrawable mTransitionDrawable;
	private MenuItem mOpenSearchItem;
	private boolean mIsDrawerOpen = false;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_facilities);
		initActionbar();
		declareItems();

		Resources res = this.getResources();
		mTransitionDrawable = (TransitionDrawable) res.getDrawable(R.drawable.transition_drawable);
		assert mTransitionDrawable != null;
		mTransitionDrawable.setCrossFadeEnabled(true);


		mSearchInput.addTextChangedListener(textWatcher);
		mFacilityList.setOnItemClickListener(
				new AdapterView.OnItemClickListener() {
					/**
					 * Provides a itemClickListener for the listview, registers clicks and
					 * sends the extra info through to the details activity.
					 */
					@Override
					public void onItemClick(AdapterView<?> parent, View view, int pPosition, long pId) {
						Facility facility = (Facility) parent.getItemAtPosition(pPosition);
						Intent myIntent = new Intent(FacilitiesActivity.this, FacilitiesDetailsActivity.class);
						myIntent.putExtra("facilityname", facility.getmFacilityName());
						myIntent.putExtra("telefoon", facility.getmTelefoonNummer());
						myIntent.putExtra("website", facility.getmWebsite());
						myIntent.putExtra("tower", facility.getmTower());
						myIntent.putExtra("etage", facility.getmEtage());
						myIntent.putExtra("showroom", facility.getmShowRoom());
						myIntent.putExtra("email", facility.getmEmail());
						myIntent.putExtra("mode", getModeArray(facility));
						FacilitiesActivity.this.startActivity(myIntent);
						overridePendingTransition(R.anim.slide_in_right, R.anim.hold_screen);
					}
				}
		);

		mDrawerToggle = new ActionBarDrawerToggle(
				this,                  /* host Activity */
				mDrawerLayout,         /* DrawerLayout object */
				R.string.drawer_open,  /* "open drawer" description */
				R.string.drawer_close  /* "close drawer" description */
		) {

			/** Called when a drawer has settled in a completely closed state. */
			public void onDrawerClosed(View view) {
				super.onDrawerClosed(view);
				hideIcon(R.id.action_search, false);
				mOpenSearchItem = menu.findItem(R.id.action_search);
				mOpenSearchItem.setIcon(R.drawable.abc_ic_search_api_mtrl_alpha);
				closeSearch();
				mIsDrawerOpen = false;


			}

			/** Called when a drawer has settled in a completely open state. */
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				closeSearch();
				hideIcon(R.id.action_search, true);
				changeIcon(R.id.action_search, false);
				mIsDrawerOpen = true;


			}
		};

		// Set the drawer toggle as the DrawerListener
		mDrawerLayout.setDrawerListener(mDrawerToggle);


	}

	protected TextWatcher textWatcher = new TextWatcher() {

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {

		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			mFacilityAdapter.getFilter().filter(s.toString());
		}

		@Override
		public void afterTextChanged(Editable s) {

		}
	};

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	private class DrawerItemClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView parent, View view, int position, long id) {
			selectDrawerItem(position);
		}
	}

	/**
	 * Declares all items used in the onCreate method
	 */
	private void declareItems() {
		mModeCategories = getResources().getStringArray(R.array.mode_categories_array);
		mModeArray = getResources().getStringArray(R.array.mMode_array);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);
		mDrawerList.setAdapter(new ArrayAdapter<>(this, R.layout.custom_row_navoptions, mModeCategories));
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());


		mDb = new MyDatabase(this);
		mItems = mDb.getAllFacilities();
		mTitle = (TextView) getSupportActionBar().getCustomView().findViewById(R.id.facilitiesTitle);


		mFacilities = getAllFacilities();

		mFacilityAdapter = new FacilityAdapter(getBaseContext(), mFacilities);
		mFacilityList = (ListView) findViewById(R.id.facilitiesList);
		mFacilityList.setAdapter(mFacilityAdapter);


	}

	private void initActionbar() {

		android.support.v7.app.ActionBar actionBar = getSupportActionBar();
		actionBar.setCustomView(R.layout.actionbar_view);
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_HOME);

		initSearch(actionBar);

		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeButtonEnabled(true);
	}

	private void initSearch(android.support.v7.app.ActionBar pActionbar) {
		mClearText = (Button) pActionbar.getCustomView().findViewById(R.id.clear_text);
		mClearText.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mSearchInput.setText("");
			}
		});
		mClearText.setVisibility(View.GONE);

		mSearchInput = (EditText) pActionbar.getCustomView().findViewById(R.id.searchfield);
		mSearchInput.setVisibility(View.GONE);
		mIsSearchActive = false;
	}

	/**
	 * Checks which drawerItem is selected if the position is 0 the method gets the whole facilitie list
	 * when the position isnt 0 the getModeFacility(position) method is used to fill the list with the slected
	 * mode categorie
	 *
	 * @param position The position of the selected drawerlist item
	 */
	private void selectDrawerItem(int position) {
		if (position == 0) {
			mFacilities = getAllFacilities();
			mFacilityAdapter = new FacilityAdapter(getBaseContext(), mFacilities);
			mTitle.setText(R.string.facilities_button_text);
			mDrawerList.setItemChecked(position, true);
			mDrawerLayout.closeDrawer(mDrawerList);
			mFacilityList.setAdapter(mFacilityAdapter);
		} else {
			mFacilities = getModeFacilities(position);
			mFacilityAdapter = new FacilityAdapter(getBaseContext(), mFacilities);
			mFacilityList.setAdapter(mFacilityAdapter);
			mTitle.setText(mModeCategories[position]);
			mDrawerList.setItemChecked(position, true);
			mDrawerLayout.closeDrawer(mDrawerList);
		}
	}

	/**
	 * Provides an open method for the actionbar search button and opens the softkeyboard.
	 * it also hides the title.
	 */
	public void openSearch() {
		if (!mIsSearchActive) {
			mSearchInput.setVisibility(View.VISIBLE);
			mClearText.setVisibility(View.VISIBLE);
			mOpenSearchItem = menu.findItem(R.id.action_search);

			AnimatorSet animations = new AnimatorSet();
			animations.playTogether(
					ObjectAnimator.ofFloat(mSearchInput, "translationY", -250, 0),
					ObjectAnimator.ofFloat(mClearText, "translationY", -250, 0),
					ObjectAnimator.ofFloat(mTitle, "translationY", 0, 250));
			animations.setInterpolator(new AccelerateInterpolator(3f));
			animations.setDuration(400).start();
			// If the animation is running you can't click the buttons
			if (animations.isRunning()) {
				mOpenSearchItem.setEnabled(false);
			}

			animations.addListener(new Animator.AnimatorListener() {
				                       @Override
				                       public void onAnimationStart(Animator animation) {
				                       }

				                       @Override
				                       public void onAnimationRepeat(Animator animation) {
				                       }

				                       @Override
				                       public void onAnimationEnd(Animator animation) {
					                       mTitle.setVisibility(View.GONE);
					                       mOpenSearchItem.setEnabled(true);
					                       mIsSearchActive = true;
					                       mSearchInput.requestFocus();
				                       }

				                       @Override
				                       public void onAnimationCancel(Animator animation) {

				                       }
			                       }
			);


			InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
		}
	}

	public void closeSearch() {
		if (mIsSearchActive) {
			mSearchInput.setText("");
			mOpenSearchItem = menu.findItem(R.id.action_search);
			mTitle.setVisibility(View.VISIBLE);

			AnimatorSet animations = new AnimatorSet();
			animations.playTogether(
					ObjectAnimator.ofFloat(mSearchInput, "translationY", 0, -250),
					ObjectAnimator.ofFloat(mClearText, "translationY", 0, -250),
					ObjectAnimator.ofFloat(mTitle, "translationY", 250, 0));
			animations.setInterpolator(new AccelerateInterpolator(3f));
			animations.setDuration(400).start();
			// If the animation is running you can't click the buttons
			if (animations.isRunning()) {
				mOpenSearchItem.setEnabled(false);
			}

			animations.addListener(new Animator.AnimatorListener() {
				                       @Override
				                       public void onAnimationStart(Animator animation) {
				                       }

				                       @Override
				                       public void onAnimationRepeat(Animator animation) {
				                       }

				                       @Override
				                       public void onAnimationEnd(Animator animation) {
					                       mSearchInput.setVisibility(View.GONE);
					                       mClearText.setVisibility(View.GONE);
					                       mOpenSearchItem.setEnabled(true);
					                       mIsSearchActive = false;
				                       }

				                       @Override
				                       public void onAnimationCancel(Animator animation) {

				                       }
			                       }
			);

			InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			inputMethodManager.hideSoftInputFromWindow(mSearchInput.getWindowToken(), 0);
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds mItems to the action bar if it is present.
		this.menu = menu;
		getMenuInflater().inflate(R.menu.menu_facilities_activity, menu);
		return true;
	}


	/**
	 * @param facility
	 * @return returns  an Arraylist with all mode categories from the given facility
	 */
	public ArrayList getModeArray(Facility facility) {
		mFacilityMode.clear();
		for (int i = 0; i <= mModeArray.length - 1; i++) {
			if (!facility.isLeeg(i)) {
				mFacilityMode.add(mModeArray[i]);
			}
		}
		return mFacilityMode;
	}

	/**
	 * @param id        defines the menuId from the menuitem that changes
	 * @param isPressed boolean to check of the button is pressed
	 *                  changes the icon to a X when the search button is pressed
	 */
	private void changeIcon(int id, boolean isPressed) {
		MenuItem item = menu.findItem(id);
		if (isPressed) {
			item.setIcon(mTransitionDrawable);
			mTransitionDrawable.startTransition(400);

		} else {
			item.setIcon(mTransitionDrawable);
			mTransitionDrawable.reverseTransition(400);
		}
	}

	/**
	 * @param id      defines the menuId from the menuitem that changes
	 * @param visible boolean to check if the search icon is visible or not
	 *                Hides the Menuitem with the given id.
	 *                this method is used when the openDrawer method is used.
	 *                we use this method to check if the drawer is open or close. when the drawer opens the search icon is hid
	 *                when the drawer closes the search icon is visible
	 */
	private void hideIcon(int id, boolean visible) {
		MenuItem item = menu.findItem(id);
		if (visible) {
			item.setVisible(false);
		} else {
			item.setVisible(true);
		}

	}


	/**
	 * Provides the option to open the inputfield for searching through the list.
	 *
	 * @param item gets the menu item id
	 * @return
	 */

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		// Pass the event to ActionBarDrawerToggle, if it returns
		// true, then it has handled the app icon touch event
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_search && !mIsSearchActive) {
			changeIcon(R.id.action_search, true);
			openSearch();
			return true;
		} else {
			changeIcon(R.id.action_search, false);
			closeSearch();

			return super.onOptionsItemSelected(item);
		}
	}

	/**
	 * Method to fil an arraylist with all the Facilities in the database
	 *
	 * @return Arraylist with all the facilities in the db
	 */
	public ArrayList getAllFacilities() {
		mFacilities.clear();
		for (int i = 0; i < mItems.size(); i++) {
			mFacilities.add(
					new Facility(
							mItems.get(i).getmFacilityName(),
							mItems.get(i).getmTelefoonNummer(),
							mItems.get(i).getmWebsite(),
							mItems.get(i).getmTower(),
							mItems.get(i).getmEtage(),
							mItems.get(i).getmShowRoom(),
							mItems.get(i).getmEmail(),
							mItems.get(i).getmDamesMode(),
							mItems.get(i).getmHerenMode(),
							mItems.get(i).getmKinderMode(),
							mItems.get(i).getmAccessoires(),
							mItems.get(i).getmVoorraad(),
							mItems.get(i).getmXlDames(),
							mItems.get(i).getmXlHeren(),
							mItems.get(i).getmSportKleding(),
							mItems.get(i).getmBruidsKleding(),
							mItems.get(i).getmBabySpullen(),
							mItems.get(i).getmBadMode(),
							mItems.get(i).getmOntwerper(),
							mItems.get(i).getmDiversen(),
							mItems.get(i).getmQuickResponse(),
							mItems.get(i).getmCollectie(),
							mItems.get(i).getmOpAfspraak()));
		}
		return mFacilities;
	}

	/**
	 * @param mode Id of the modeCategorie you want to search for
	 *             Method to fil an arraylist with all the Facilities with the given ModeCategorie
	 * @return Arraylist with all the facilities in the db
	 */
	public ArrayList getModeFacilities(int mode) {
		mFacilityModeCategories.clear();
		for (int i = 1; i < mItems.size(); i++) {
			if (!mItems.get(i).isListLeeg(mode)) {
				mFacilityModeCategories.add(
						new Facility(
								mItems.get(i).getmFacilityName(),
								mItems.get(i).getmTelefoonNummer(),
								mItems.get(i).getmWebsite(),
								mItems.get(i).getmTower(),
								mItems.get(i).getmEtage(),
								mItems.get(i).getmShowRoom(),
								mItems.get(i).getmEmail(),
								mItems.get(i).getmDamesMode(),
								mItems.get(i).getmHerenMode(),
								mItems.get(i).getmKinderMode(),
								mItems.get(i).getmAccessoires(),
								mItems.get(i).getmVoorraad(),
								mItems.get(i).getmXlDames(),
								mItems.get(i).getmXlHeren(),
								mItems.get(i).getmSportKleding(),
								mItems.get(i).getmBruidsKleding(),
								mItems.get(i).getmBabySpullen(),
								mItems.get(i).getmBadMode(),
								mItems.get(i).getmOntwerper(),
								mItems.get(i).getmDiversen(),
								mItems.get(i).getmQuickResponse(),
								mItems.get(i).getmCollectie(),
								mItems.get(i).getmOpAfspraak()));
			}
		}
		return mFacilityModeCategories;
	}

	@Override
	public void onBackPressed() {

		if (mIsDrawerOpen) {
			mDrawerLayout.closeDrawers();
		} else {
			super.onBackPressed();
			MainActivity.animateReverseButtons();
			overridePendingTransition(R.anim.hold_screen, R.anim.zoom_exit);
		}
	}

}
