package ru.jahom.assetfont.views;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;

import ru.jahom.assetfont.AssetFontHelper;
import ru.jahom.assetfont.AssetFontSpan;
import ru.jahom.assetfont.IAssetFontView;

/**
 * Created by Anton Knyazev on 13.03.2017.
 */

public class AssetFontToolbar extends Toolbar implements IAssetFontView {

    private Typeface mTypeFace;

    public AssetFontToolbar(Context context) {
        super(context);
        init(null, 0);
    }

    public AssetFontToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public AssetFontToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(@Nullable AttributeSet attrs, int defStyleAttr) {
        AssetFontHelper.get().applyAttributes(this, attrs, defStyleAttr);
    }

    @Override
    public void setTypeface(Typeface typeFace) {
        mTypeFace = typeFace;
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(AssetFontSpan.convert(title, mTypeFace));
    }

    @Override
    public void setSubtitle(CharSequence subtitle) {
        super.setSubtitle(AssetFontSpan.convert(subtitle, mTypeFace));
    }
}
