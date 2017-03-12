package ru.jahom.assetfont;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by Anton Knyazev on 12.03.2017.
 */

public class AssetFontHelper {

    private static final String TAG = AssetFontHelper.class.getSimpleName();
    private static final String FONT_FOLDER_NAME = "fonts/";

    private static AssetFontHelper sInstance = new AssetFontHelper();
    private HashMap<String, Typeface> mFontCache = new HashMap<>();

    public static AssetFontHelper get() {
        return sInstance;
    }

    public Typeface getTypeface(Context context, String name) {
        Typeface typeface = mFontCache.get(name);

        if(typeface == null) {
            try {
                typeface = Typeface.createFromAsset(context.getAssets(), FONT_FOLDER_NAME + name);
            }
            catch (Exception e) {
                Log.e(TAG, "Error load font", e);
                typeface = Typeface.DEFAULT;
            }
            mFontCache.put(name, typeface);
        }

        return typeface;
    }

    public void applyAttributes(IAssetFontView view, @Nullable AttributeSet attrs, int defStyleAttr) {
        if (attrs != null) {
            TypedArray a = view.getContext().obtainStyledAttributes(attrs, R.styleable.AssetFontHelper,
                    defStyleAttr, 0);
            if (a.hasValue(R.styleable.AssetFontHelper_fontAssetName)) {
                String fontName = a.getString(R.styleable.AssetFontHelper_fontAssetName);
                view.setTypeface(getTypeface(view.getContext(), fontName));
            }
            a.recycle();
        }
    }

    public static void set(TextView textView, String assetFontName) {
        textView.setTypeface(get().getTypeface(textView.getContext(), assetFontName));
    }

}
