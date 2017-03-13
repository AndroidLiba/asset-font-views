package ru.jahom.assetfont.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.util.AttributeSet;

import ru.jahom.assetfont.AssetFontHelper;
import ru.jahom.assetfont.IAssetFontView;

/**
 * Created by Anton Knyazev on 12.03.2017.
 */

public class AssetFontCheckedTextView extends AppCompatCheckedTextView implements IAssetFontView {
    public AssetFontCheckedTextView(Context context) {
        super(context);
        init(null, 0);
    }

    public AssetFontCheckedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public AssetFontCheckedTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(@Nullable AttributeSet attrs, int defStyleAttr) {
        AssetFontHelper.get().applyAttributes(this, attrs, defStyleAttr);
    }

}
