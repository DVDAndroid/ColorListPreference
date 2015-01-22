ColorListPreference
===================

Library for add a custom list preference with a preview of the color

![screenshot](screenshot.gif)


Usage
-----

1) work in progress...

2) Add an entries array, entryValues array and an entryColors array in your project
	Example: 
```xml
		<string-array name="listNames" translatable="false">
			<item>Red</item>
			<item>Pink</item>
			<item>Purple</item>
			<item>Deep purple</item>
			.....
		</string-array>	
		
		<string-array name="listValues">
			<item>"1"</item>
			<item>"2"</item>
			<item>"3"</item>
			<item>"4"</item>
			.....
		</string-array>
		
		<string-array name="colorsValues">
			<!-- NOTE: you can add an item as #f44336 or f44336 -->
			<item>#f44336</item>
			<item>#e91e63</item>
			<item>#9c27b0</item>
			<item>#673ab7</item>
			.....
		</string-array>
```
3) Add a XML Namespace Prefix `xmlns:app="http://schemas.android.com/apk/res-auto`

4) You should incorporate this xml code in your preference layout:
```xml
		<com.dvd.android.library.colorlistpreference.ColorListPreference
                android:entries="@array/listNames"
                android:entryValues="@array/listValues"
                android:key="colorList"
                android:defaultValue="2"
                android:summary="Summary"
                android:title="Title"
                app:entryColors="@array/colorsValues"/>
```
5) Enjoy!

# To get the clicked color id do:

```java
    getSharedPreferences().getString("your_key" + "_color_id", "1");        // replace "your_key" with your preference key
```


LICENSE
-------

GNU GENERAL PUBLIC LICENSE v2

Credits
-------

[CMWmobile](http://www.cmwmobile.com) helps me to create this library.   [link](http://www.cmwmobile.com/index.php?option=com_content&view=article&id=4:how-to-create-an-imagelistpreference&catid=10:blog&Itemid=12)
