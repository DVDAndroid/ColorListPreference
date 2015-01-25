package com.dvd.android.colorlistpreference;

import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.dvd.android.library.colorlistpreference.ColorListPreference;

public class MainActivity extends PreferenceActivity implements
		SharedPreferences.OnSharedPreferenceChangeListener {

	private ColorListPreference clp;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("deprecation")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.prefs);

		clp = (ColorListPreference) getPreferenceScreen().findPreference(
				"colorList");

		apply();
	}

	@Override
	@SuppressWarnings("deprecation")
	protected void onResume() {
		super.onResume();
		getPreferenceScreen().getSharedPreferences()
				.registerOnSharedPreferenceChangeListener(this);
	}

	@Override
	@SuppressWarnings("deprecation")
	protected void onPause() {
		super.onPause();
		getPreferenceScreen().getSharedPreferences()
				.unregisterOnSharedPreferenceChangeListener(this);
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		apply();
	}

	private void apply() {
		if (getActionBar() != null) {
			getActionBar().setBackgroundDrawable(
					new ColorDrawable(clp.getColor()));
		}

		clp.setSummary(clp.getColorString());
	}
}
