package com.mycurrentip.dao.repositorios;

import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.database.Cursor;


public interface IRepositorio<T> {
	public ContentValues createContentValues(T object);
	public long insert(T object);
	public Cursor getCursor();
	public Map<String, Integer> getIndices(Cursor cursor);
	public T createObject(Cursor cursor);
	public List<T> listar();
	public void fecharDB();
}
