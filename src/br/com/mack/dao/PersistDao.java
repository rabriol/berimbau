package br.com.mack.dao;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PersistDao {
	
	private static SQLiteDatabase banco = null;
	private static int idGenerated = 0;
	private Context context;
	
	public PersistDao(Context context){
		this.context = context;
		criarBancoDeDados();
	}
	
	private boolean criarBancoDeDados() {
		try {
			
			banco = context.openOrCreateDatabase("berimbau", Context.MODE_PRIVATE, null);
			banco.execSQL("CREATE TABLE IF NOT EXISTS BERIMBAU (ID INTEGER PRIMARY KEY, TERMOBUSCA TEXT);");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static void salvar(String termo) {
		ContentValues values = new ContentValues();
		values.put("ID", idGenerated++);
		values.put("TERMOBUSCA", termo);
		banco.insert("BERIMBAU", null, values);
		System.out.println("salvou");
	}
	
	public static void deletar(String termo) {
		banco.execSQL("DELETE FROM BERIMBAU WHERE TERMOBUSCA = '"+termo+"'");
	}
	
	public String[] pesquisarTodos(){
		final String[] columns = {"ID", "TERMOBUSCA"};
		Cursor cursor = banco.query("BERIMBAU", columns, null, null, null, null, null);
		
		String[] resultado = new String[cursor.getCount()];
		if (cursor.moveToFirst()) {
			int i = 0;
			do {
				resultado[i] = cursor.getString(1);
				i++;
			} while (cursor.moveToNext());
		}
		return resultado;
	}
}
