package com.dvd.android.library.colorlistpreference;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.res.TypedArray;
import android.preference.ListPreference;
import android.util.AttributeSet;
import android.widget.ListAdapter;

/**
 *
 * The ColorListPreference class responsible for displaying a color for each
 * item within the list.
 *
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
	protected void onPrepareDialogBuilder(Builder builder) {

		if (!((getEntries().length == getEntryValues().length) && (getEntries().length == colors.length)))
			throw new AssertionError(
					"ColorListPreference requires a complete entries array, a complete entryValues array and a complete entryColors array");

		int index = findIndexOfValue(getSharedPreferences().getString(getKey(),
				"1"));

		ListAdapter listAdapter = new ColorArrayAdapter(getContext(),
				R.layout.listitem_row, getEntries(), resourceIds, index);

		builder.setAdapter(listAdapter, this);
		super.onPrepareDialogBuilder(builder);
	}
}
