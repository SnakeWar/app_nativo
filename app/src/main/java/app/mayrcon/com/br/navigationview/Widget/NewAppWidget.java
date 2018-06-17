package app.mayrcon.com.br.navigationview.Widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.util.Log;
import android.widget.RemoteViews;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import app.mayrcon.com.br.navigationview.Model.ObjectItem;
import app.mayrcon.com.br.navigationview.R;
import io.paperdb.Paper;


/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        Paper.init(context);
        ObjectItem objectItem = new ObjectItem();
        objectItem.setTitle(Paper.book().read("title").toString());
        objectItem.setDesc(Paper.book().read("desc").toString());
        /*String cidade = Paper.book().read("cidade");*/


        //------------ Hora e Data -------------

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");

        Date data = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        Date data_atual = cal.getTime();

        String data_completa = dateFormat.format(data_atual);

        String hora_atual = hourFormat.format(data_atual);

        Log.i("data_completa", data_completa);
        Log.i("data_atual", data_atual.toString());
//        Log.i("hora_atual", hora_atual); // Esse é o que você quer
        //--------- Fim Hora de Data ----------

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        views.setTextViewText(R.id.appwidget_text, objectItem.getTitle());
        views.setTextViewText(R.id.appwidget_text2, objectItem.getDesc());
        views.setTextViewText(R.id.appwidget_text3, data_completa);
        views.setTextViewText(R.id.appwidget_text4, hora_atual);
        views.setImageViewResource(R.id.imageView2, R.mipmap.ic_launcher);



        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);


    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}
