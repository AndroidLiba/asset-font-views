package ru.jahom.assetfont;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Type;
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

    public Typeface getTypeface(Context context, @StringRes int fontResId) {
        return getTypeface(context, context.getString(fontResId));
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

    public void set(TextView textView, String assetFontName) {
        textView.setTypeface(getTypeface(textView.getContext(), assetFontName));
    }

    public void set(AlertDialog alertDialog, @StringRes int resId) {
        set(alertDialog, alertDialog.getContext().getString(resId));
    }

    public void set(AlertDialog alertDialog, String assetFontName) {
        Typeface typeface = getTypeface(alertDialog.getContext(), assetFontName);

        int[] ids = new int[]{android.R.id.message, android.R.id.button1,
                android.R.id.button2, android.R.id.button3};
        for (int id : ids) {
            TextView textView = (TextView) alertDialog.findViewById(id);
            if (textView != null) {
                textView.setTypeface(typeface);
            }
        }
    }

    public void set(ViewGroup viewGroup, @StringRes int resId) {
        set(viewGroup, viewGroup.getContext().getString(resId));
    }

    public void set(ViewGroup viewGroup, String assetFontName) {
        Typeface typeface = getTypeface(viewGroup.getContext(), assetFontName);
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View v = viewGroup.getChildAt(i);
            if (v instanceof TextView) {
                ((TextView) v).setTypeface(typeface);
            }
        }
    }

    public void set(Toolbar toolbar, @StringRes int resId) {
        set(toolbar, resId);
    }

    public void set(Toolbar toolbar, String assetFontName) {
        set((ViewGroup) toolbar, assetFontName);
    }

}
