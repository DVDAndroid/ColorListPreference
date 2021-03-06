/*
 * Copyright (C) 2015  dvdandroid
 *
 *     This program is free software; you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation; either version 2 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License along
 *     with this program; if not, write to the Free Software Foundation, Inc.,
 *     51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package com.dvd.android.library.colorlistpreference;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.preference.ListPreference;
import android.util.AttributeSet;
import android.widget.ListAdapter;

/**
 * The ColorListPreference class responsible for displaying a color for each
 * item within the list.
 */
public class ColorListPreference extends ListPreference {

	private CharSequence[] resourceIds;
	private CharSequence[] colors;

	/**
	 * Constructor of the ColorListPreference. Initializes the custom images.
	 *
	 * @param context
	 *            application context.
	 * @param attrs
	 *            custom xml attributes.
	 */
	public ColorListPreference(Context context, AttributeSet attrs) {
		super(context, attrs);

		TypedArray typedArray = context.obtainStyledAttributes(attrs,
				R.styleable.ColorListPreference);
		colors = typedArray
				.getTextArray(R.styleable.ColorListPreference_entryColors);

		resourceIds = new String[colors.length];
		System.arraycopy(colors, 0, resourceIds, 0, colors.length);

		typedArray.recycle();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setValue(String value) {
		super.setValue(value);

		updateColor();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onPrepareDialogBuilder(Builder builder) {

		if (!((getEntries().length == getEntryValues().length) && (getEntries().length == colors.length))) {
			throw new AssertionError(
					"ColorListPreference requires a complete entries array, a complete entryValues array and a complete entryColors array");
		}

		int index = findIndexOfValue(getSharedPreferences().getString(getKey(),
				"1"));

		ListAdapter listAdapter = new ColorArrayAdapter(getContext(),
				R.layout.listitem_row, getEntries(), resourceIds, index);

		builder.setAdapter(listAdapter, this);
		super.onPrepareDialogBuilder(builder);
	}

	/**
	 * Method to return color id in a string
	 *
	 * @return color value in string
	 */
	public String getColorString() {
		return getSharedPreferences().getString(getKey() + "_color_id", "1");
	}

	/**
	 * Method to return color id in a string
	 *
	 * @return color value
	 */
	public int getColor() {
		return Color.parseColor(getColorString());
	}

	@Override
	protected void onDialogClosed(boolean positiveResult) {
		super.onDialogClosed(positiveResult);

		// I don't know if it's right
		getSharedPreferences().edit().putString(getKey(), getValue()).apply();

		updateColor();

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onSetInitialValue(boolean restoreValue, Object defaultValue) {
		super.onSetInitialValue(restoreValue, defaultValue);

		updateColor();
	}

	private void updateColor() {
		// I don't know if this method is the best, but it works
		String color_id;
		if (colors[Integer.parseInt(getValue()) - 1].toString().contains("#")) {
			color_id = colors[Integer.parseInt(getValue()) - 1].toString();
		} else {
			color_id = "#"
					+ colors[Integer.parseInt(getValue()) - 1].toString();
		}

		getSharedPreferences().edit()
				.putString(getKey() + "_color_id", color_id).apply();
	}
}