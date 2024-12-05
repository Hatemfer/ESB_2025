package com.example.esb_2025;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserDB {
    private static final int VERSION_BDD =1;
    private static final String NOM_BDd = "Bis3.db";
    private static final String TABLE_USER = "table_user";

    private static final String COL_Email = "Email";
    private static final int NUM_COL_Email = 0;

    private static final String COL_username = "Username";

    private static final int NUM_COL_Username = 1;
    private static final String COL_Password = "Password";

    private static final int NUM_COL_Password = 2;
    private SQLiteDatabase bdd;
    private MabaseSQLite maBaseSQLite;

    public UserDB(Context context) {
        //on créer la BDD et sa table
        maBaseSQLite = new MabaseSQLite(context,NOM_BDd,null,VERSION_BDD);
    }

    public void open(){
        //on ouvre la BDD en écriture
        bdd = maBaseSQLite.getWritableDatabase();
        //dbb = maBaseSQLite.getReadableDatabase();
    }
    public void close(){
        //on ferme l'accès à la BDD
        bdd.close();
    }
    public SQLiteDatabase getBDD(){
        return bdd;
    }

    public long insertUser(User user){
        //Création d'un ContentValues
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans
        //laquelle on veut placer la valeur)
        values.put(COL_Email, user.getEmail());
        values.put(COL_username,user.getUsername());
        values.put(COL_Password,user.getPwd());
        //on insére l'objet dans la BDD via le ContentValues
        long res = bdd.insert(TABLE_USER, null, values);
        return res;
    }

    public int getUserWithlog(User user){
        //Récupérer dans un Cursor les valeurs correspondant
        // à un étudiant contenu dans la BDD (ici on sélectionne le etudiant
        // grâce à son prénom)
        Cursor z = bdd.rawQuery("SELECT * FROM " + TABLE_USER +
                        " WHERE " + COL_username + "=? AND " + COL_Password + "=?",
                new String[]{user.getUsername(), user.getPwd()});

//        Cursor z = bdd.rawQuery("select * from "+TABLE_USER+
////                " where username='"+
////                user.getUsername()+"' and pwd='"+
////                user.getPwd()+"'", new String[] { } );
        return z.getCount();
    }
}
