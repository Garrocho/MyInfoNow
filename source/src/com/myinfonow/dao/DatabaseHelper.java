package com.myinfonow.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseHelper {
	private SQLHelper sqlHelper;
	private SQLiteDatabase db;

	private static final String NOME_BANCO = "myinfonow";
	private static final int VERSAO = 4;
	private static DatabaseHelper banco;

	private static final String[] DATABASE_TABLES = {
		"historico",
	};

	private static final String[] DATABASE_TABELAS_REMOVIDAS = {

	};

	private static final String[] DATABASE_CREATE = new String[]{
	"CREATE TABLE IF NOT EXISTS historico (" +
		"ip_local VARCHAR(100), " +
		"ip_externo VARCHAR(100), " +
		"mac VARCHAR(100), " +
		"vazao VARCHAR(100), " +
		"latitude VARCHAR(100), " +
		"longitude VARCHAR(100), " +
		"data_hora TIMESTAMP, " +
		"PRIMARY KEY (ip_local, ip_externo, data_hora)" +
		");" +
	""
	};

	private DatabaseHelper(Context context) {
		// cria o SQLHelper
		sqlHelper = new SQLHelper(context, NOME_BANCO, VERSAO, DATABASE_TABLES, DATABASE_CREATE, DATABASE_TABELAS_REMOVIDAS);

		//abre o banco de dados para escrita e leitura
		db = sqlHelper.getWritableDatabase();
	}

	public synchronized static DatabaseHelper getInstance(Context context){
		if(banco == null || !banco.db.isOpen()){
			banco = new DatabaseHelper(context);
		}
		return banco;
	}
	
	public synchronized boolean isOpen(){
		if(db != null && db.isOpen()){
			return true;
		}
		return false;
	}

	public synchronized void fechar(){
		if(sqlHelper != null){
			sqlHelper.close();
		}
		if(db != null && db.isOpen()){
			db.close();
		}
	}

	public synchronized SQLiteDatabase getDb() {
		return db;
	}

}
