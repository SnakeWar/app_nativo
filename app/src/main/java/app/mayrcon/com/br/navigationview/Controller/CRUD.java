package app.mayrcon.com.br.navigationview.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import app.mayrcon.com.br.navigationview.Model.ObjectItem;

/**
 * Criado por Gilian Marques em 13/12/2016.
 */
public class CRUD extends SQLiteOpenHelper {

    private static final String NOME_DB = "db_sqlite";
    private static final int VERSAO_DB = 2;
    private static final String TABELA = "tb_lista";

    private static final String PATH_DB = "/data/user/0/app.mayrcon.com.br.navigationview/databases/db_sqlite";
    private Context mContext;
    private SQLiteDatabase db;


    public CRUD(Context context) {
        super(context, NOME_DB, null, VERSAO_DB);
        this.mContext = context;
        db = getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // logica pra atualiza db
    }

    public ArrayList<ObjectItem> getObject() {
        openDB();
        ArrayList<ObjectItem> pArray = new ArrayList<>();
        String getObject = "SELECT * FROM " + TABELA;

        try {
            Cursor c = db.rawQuery(getObject, null);

            if (c.moveToFirst()) {
                do {
                    ObjectItem p = new ObjectItem();
                    p.setId(c.getInt(0));
                    p.setTitle(c.getString(1));
                    p.setDesc(c.getString(2));
                    pArray.add(p);
                } while (c.moveToNext());
                c.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            db.close();
        }

        return pArray;
    }

    public boolean insertObject(ObjectItem objectItem) {
        openDB();
        try {
            ContentValues cv = new ContentValues();
            cv.put("TITULO", objectItem.getTitle());
            cv.put("DESCRICAO", objectItem.getDesc());
            db.insert(TABELA, null, cv);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            db.close();
        }
    }


    public boolean updateObject(ObjectItem objectItem) {
        openDB();
        try {
            String where = "ID = '" + objectItem.getId() + "'";
            ContentValues cv = new ContentValues();
            cv.put("TITULO", objectItem.getTitle());
            cv.put("DESCRICAO", objectItem.getDesc());
            db.update(TABELA, cv, where, null);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            db.close();
        }
    }

    public boolean deleteTable() {
        openDB();
        String deletTable = "DROP TABLE IF EXISTS " + TABELA;
        try {
            db.execSQL(deletTable);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            db.close();
        }
    }


    public boolean deleteObject(ObjectItem o) {
        openDB();

        String deletePessoa = "TITULO = '" + o.getTitle() + "'";

        try {
            db.delete(TABELA, deletePessoa, null);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            db.close();
        }

    }


    private void openDB() {
        if (!db.isOpen()) {
            db = mContext.openOrCreateDatabase(PATH_DB, SQLiteDatabase.OPEN_READWRITE, null);
        }

    }


}
