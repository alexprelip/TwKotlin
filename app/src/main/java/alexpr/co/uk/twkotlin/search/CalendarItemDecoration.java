package alexpr.co.uk.twkotlin.search;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import alexpr.co.uk.twkotlin.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CalendarItemDecoration extends RecyclerView.ItemDecoration {
    private String[] monthsArray;
    private Paint paint = new Paint();

    public CalendarItemDecoration(Context context) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize(60);
        paint.setColor(Color.rgb(100, 100, 100));
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        monthsArray = context.getResources().getStringArray(R.array.months_array);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if (parent.getChildLayoutPosition(view) % 49 <= 6) {
            //the text height should be measured with the paint
            outRect.set(0, 200, 0, 0);
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i += 49) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            String monthName = monthsArray[((CalendarAdapter) parent.getAdapter()).getItemAtPosition(params.getViewAdapterPosition()).getMonth()];
            monthName = monthName.toUpperCase();
            c.drawText(monthName, parent.getWidth() / 2, child.getTop() - 100, paint);
        }
    }
}