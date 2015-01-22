package com.dvd.android.library.colorlistpreference;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;

/**
 * 
 * The ColorArrayAdapter is the array adapter used for displaying a color to a
 * list preference item.
 * 
 */
public class ColorArrayAdapter extends ArrayAdapter<CharSequence> {

	private int index = 0;
	private CharSequence[] resourceIds = null;

	/**
	 * ColorArrayAdapter constructor.
	 * 
	 * @param context
	 *            the context.
	 * @param textViewResourceId
	 *            resource id of the text view.
	 * @param objects
	 *            to be displayed.
	 * @param ids
	 *            color id to be displayed.
	 * @param i
	 *            index of the previous selected item.
	 */
	public ColorArrayAdapter(Context context, int textViewResourceId,
			CharSequence[] objects, CharSequence[] ids, int i) {
		super(context, textViewResourceId, objects);

		index = i;
		resourceIds = ids;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressLint("ViewHolder")
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
		View row = inflater.inflate(R.layout.listitem_row, parent, false);

		ImageView imageView = (ImageView) row.findViewById(R.id.color);
		imageView.setImageDrawable(resourceIds[position].toString().contains(
				"#") ? new ColorDrawable(Color.parseColor(resourceIds[position]
				.toString())) : new ColorDrawable(Color.parseColor("#"
				+ resourceIds[position].toString())));

		CheckedTextView checkedTextView = (CheckedTextView) row
				.findViewById(R.id.check);
		checkedTextView.setText(getItem(position));

		if (position == index)
			checkedTextView.setChecked(true);

		return row;
	}


}
