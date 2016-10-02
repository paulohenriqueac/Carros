package br.com.androidessencial.carros.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.androidessencial.carros.domain.Carro;

public class CarroDAO extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "CARROSDB";
    private static final int VERSAO = 1;

    public CarroDAO(Context context){
        super(context,DATABASE_NAME, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists carro (" +
                "_id integer primary key autoincrement," +
                "nome text," +
                "desc text," +
                "url_foto text," +
                "url_video text," +
                "latitude text," +
                "longitude text," +
                "tipo text);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long save(Carro carro){
        long id = carro.id;
        SQLiteDatabase db = getWritableDatabase();

        try {
            ContentValues values = new ContentValues();

            values.put("nome", carro.nome.trim());
            values.put("desc", carro.desc);
            values.put("url_foto", carro.urlFoto);
            values.put("url_video", carro.urlVideo);
            values.put("latitude", carro.latitude);
            values.put("longitude", carro.longitude);
            values.put("tipo", carro.tipo);

            if (id != 0) {
                String _id = String.valueOf(carro.id);
                String[] whereArgs = new String[]{_id};

                int count = db.update("carro", values, "_id=?", whereArgs);
                return count;
            } else {
                id = db.insert("carro", "", values);
                return id;
            }
        } finally {
            db.close();
        }
    }

    public int delete(Carro carro){
        SQLiteDatabase db = getWritableDatabase();

        try {
            int count = db.delete("carro", "_id=?",new String[]{String.valueOf(carro.id)});

            return count;
        } finally {
            db.close();
        }
    }

    public List<Carro> findAll(){
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.query("carro", null, null, null, null, null, null, null);
        return toList(cursor);
    }

    private List<Carro> toList(Cursor c) {
        List<Carro> carros = new ArrayList<Carro>();

        if (c.moveToFirst()){
            do{
                Carro carro = new Carro();

                carro.id = c.getLong(c.getColumnIndex("_id"));
                carro.nome = c.getString(c.getColumnIndex("nome"));
                carro.desc = c.getString(c.getColumnIndex("desc"));
                carro.urlFoto = c.getString(c.getColumnIndex("url_foto"));
                carro.urlVideo = c.getString(c.getColumnIndex("url_video"));
                carro.latitude = c.getString(c.getColumnIndex("latitude"));
                carro.longitude = c.getString(c.getColumnIndex("longitude"));
                carro.tipo = c.getString(c.getColumnIndex("tipo"));

                carros.add(carro);
            } while (c.moveToNext());
        }

        return carros;
    }

    public boolean exists(String nome){
        SQLiteDatabase db = getReadableDatabase();

        try{
            Cursor c = db.query("carro", null, "nome=?", new String[]{nome}, null, null, null, null);

            boolean exists = c.getCount() > 0;

            return exists;

        } finally {
            db.close();
        }
    }
}
