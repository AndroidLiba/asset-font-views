package ru.jahom.assetfont;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

import static java.security.AccessController.getContext;

/**
 * Created by Anton Knyazev on 12.03.2017.
 */

public class AssetFontSpan extends MetricAffectingSpan {

    private final Typeface mTypeface;

    public AssetFontSpan(Context context, String typefaceName) {
        this(AssetFontHelper.get().getTypeface(context, typefaceName));
    }

    public AssetFontSpan(Typeface typeface) {
        mTypeface = typeface;
    }

    @Override
    public void updateMeasureState(TextPaint tp) {
        aplay(tp);
    }

    @Override
    public void updateDrawState(TextPaint tp) {
        aplay(tp);
    }

    private void aplay(TextPaint textPaint) {
        textPaint.setTypeface(mTypeface);

        // Note: This flag is required for proper typeface rendering
        textPaint.setFlags(textPaint.getFlags() | Paint.SUBPIXEL_TEXT_FLAG);
    }

    public static Spannable convert(CharSequence charSequence, Typeface typeface) {
        final SpannableString spannableString = new SpannableString(charSequence);
        spannableString.setSpan(new AssetFontSpan(typeface), 0, charSequence.length(), 0);
        return spannableString;
    }
}