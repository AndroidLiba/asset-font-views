package ru.jahom.assetfont;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.util.TypedValue;

/**
 * Created by Anton Knyazev on 13.03.2017.
 */

public class AssetFontAlertDialogBuilder extends AlertDialog.Builder {
    private static final String TAG = AssetFontAlertDialogBuilder.class.getSimpleName();
    @Nullable
    private Typeface mTypeface;

    public AssetFontAlertDialogBuilder(@NonNull Context context) {
        super(context);
        init();
    }
    public AssetFontAlertDialogBuilder(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        init();
    }

    private void init() {
        mTypeface = findTypeface(R.attr.fontAssetName);;
    }
    @Nullable
    private Typeface findTypeface(@AttrRes int attr) {
        TypedValue outValue = new TypedValue();
        getContext().getTheme().resolveAttribute(attr, outValue, true);
        String fontName;
        if (outValue.type == TypedValue.TYPE_REFERENCE) {
            fontName = getContext().getString(outValue.resourceId);
        } else if (outValue.type == TypedValue.TYPE_STRING) {
            fontName = outValue.string.toString();
        } else {
            fontName = null;
            Log.w(TAG, "Unknown attr type = " + outValue.type);
        }
        if (fontName != null) {
            return AssetFontHelper.get().getTypeface(getContext(), fontName);
        }
        return null;
    }

    public AssetFontAlertDialogBuilder setTypeFace(Typeface typeFace) {
        mTypeface = typeFace;
        return this;
    }

    // Message text font
    @Override
    public AlertDialog.Builder setMessage(@Nullable CharSequence message) {
        if (mTypeface == null) {
            return super.setMessage(message);
        } else {
            return super.setMessage(AssetFontSpan.convert(message, mTypeface));
        }
    }
    @Override
    public AlertDialog.Builder setMessage(@StringRes int messageId) {
        return setMessage(getContext().getText(messageId));
    }
    @Override
    public AlertDialog create() {
        final AlertDialog alertDialog = super.create();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setTypeface(mTypeface);
                alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTypeface(mTypeface);
                alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL).setTypeface(mTypeface);
            }
        });
        return alertDialog;
    }
}
