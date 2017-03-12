package ru.jahom.assetfont.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.Chronometer;

import ru.jahom.assetfont.AssetFontHelper;
import ru.jahom.assetfont.IAssetFontView;

/**
 * Created by Anton Knyazev on 12.03.2017.
 */

public class AssetFontChronometer extends Chronometer implements IAssetFontView {

    public AssetFontChronometer(Context context) {
        super(context);
        init(null, 0);
    }

    public AssetFontChronometer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public AssetFontChronometer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(@Nullable AttributeSet attrs, int defStyleAttr) {
        AssetFontHelper.get().applyAttributes(this, attrs, defStyleAttr);
    }

}
