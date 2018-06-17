package app.mayrcon.com.br.navigationview.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Criado por Gilian Marques em 13/12/2016.
 */
public class Create extends SQLiteOpenHelper {

    private static final String NOME_DB = "db_sqlite";
    private static final int VERSAO_DB = 2;
    private static final String TABELA = "tb_lista";

    private static final String PATH_DB = "/data/user/0/app.mayrcon.com.br.navigationview/databases/db_sqlite";
    private Context mContext;
    private SQLiteDatabase db;


    public Create(Context context) {
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


    public boolean createTable() {
        openDB();
        String createTable = "CREATE TABLE IF NOT EXISTS " + TABELA + " ("
                + "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "TITULO TEXT NOT NULL,"
                + "DESCRICAO TEXT NOT NULL);";
        try {
            db.execSQL(createTable);
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
