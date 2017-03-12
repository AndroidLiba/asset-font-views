package ru.jahom.assetfont;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by Anton Knyazev on 12.03.2017.
 */

public interface IAssetFontView  {

    Context getContext();
    void setTypeface(Typeface typeFace);
}
