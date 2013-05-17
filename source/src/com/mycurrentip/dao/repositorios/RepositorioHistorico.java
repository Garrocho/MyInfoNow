package com.mycurrentip.dao.repositorios;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.mycurrentip.classes.Historico;
import com.mycurrentip.classes.Historico.Historicos;
import com.mycurrentip.dao.DatabaseHelper;

public class RepositorioHistorico implements IRepositorio<Historico>{
	private static final String NOME_TABELA = "historico";

	private SQLiteDatabase db;
	private Historico historico;

	public RepositorioHistorico(Context context) {
		historico = new Historico();
		//pega o banco de dados
		db = DatabaseHelper.getInstance(context).getDb();
	}

	public ContentValues createContentValues(Historico Historico){
		ContentValues valores = new ContentValues();
		valores.put(Historicos.IP, Historico.getIp());
		valores.put(Historicos.DATA_HORA, Historico.getData_hora().toString());
		return valores;
	}

	public long insert(Historico Historico){
		ContentValues valores = createContentValues(Historico);

		return db.insert(NOME_TABELA, "", valores);
	}
	
	public int deleteTudo(){
		return db.delete(NOME_TABELA, "1", null);
	}

	public int count(){
		Cursor cursorNumeroRegistros = db.rawQuery(String.format("SELECT COUNT(*) FROM %s", NOME_TABELA), null);
		int numeroRegistros = 0;
		if(cursorNumeroRegistros.moveToFirst()){
			numeroRegistros = cursorNumeroRegistros.getInt(0);
		}
		cursorNumeroRegistros.close();
		return numeroRegistros;
	}

	public Cursor getCursor(){
		String orderBy = Historicos.DATA_HORA + " ASC";
		try{
			return db.query(NOME_TABELA, Historicos.COLUNAS, null, null, null, null, orderBy);
		}catch(SQLException e){
			return null;
		}
	}
	
	public Map<String, Integer> getIndices(Cursor cursor){
		Map<String, Integer> indices = new HashMap<String, Integer>();
		
		int indiceIP = cursor.getColumnIndex(Historicos.IP);
		int indiceDataHora = cursor.getColumnIndex(Historicos.DATA_HORA);
		
		indices.put(Historicos.IP, indiceIP);
		indices.put(Historicos.DATA_HORA, indiceDataHora);
		
		return indices;
	}
	
	public Historico createObject(Cursor cursor){
		Map<String, Integer> indices = getIndices(cursor);
		
		Historico Historico = new Historico();
		Historico.setIp(cursor.getString(indices.get(Historicos.IP)));
		Historico.setData_hora(new Timestamp(cursor.getLong(indices.get(Historicos.DATA_HORA))));
		
		return Historico;
	}

	public List<Historico> listar(){
		Cursor cursor = getCursor();
		List<Historico> Historicos = new ArrayList<Historico>();

		if(cursor.moveToFirst()){
			do{
				Historico Historico = createObject(cursor);
				Historicos.add(Historico);

			}while(cursor.moveToNext());
		}

		return Historicos;
	}
	
	public List<Historico> listarHistoricosComOpcaoTodas(){
		Cursor cursor = getCursor();
		List<Historico> Historicos = new ArrayList<Historico>();
		Historicos.add(historico);

		if(cursor.moveToFirst()){
			do{
				Historico Historico = createObject(cursor);
				Historicos.add(Historico);

			}while(cursor.moveToNext());
		}

		return Historicos;
	}

	public void fecharDB(){
		if(db != null)
			db.close();
	}
}
