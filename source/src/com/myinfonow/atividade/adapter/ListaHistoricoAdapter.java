package com.myinfonow.atividade.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.myinfonow.R;
import com.myinfonow.classes.Historico;

public class ListaHistoricoAdapter extends BaseAdapter {

	private Context contexto;
	private List<Historico> listaHistorico;

	public ListaHistoricoAdapter(Context contexto, List<Historico> listaHistorico) {
		this.contexto = contexto;
		this.listaHistorico = listaHistorico;
	}

	@Override
	public int getCount() {
		return this.listaHistorico.size();
	}

	@Override
	public Object getItem(int posicao) {
		return this.listaHistorico.get(posicao);
	}

	@Override
	public long getItemId(int posicao) {
		return posicao;
	}

	private class ViewHolder {
		TextView titulo;
		TextView sumario;
		TextView texto;
	}

	@Override
	public View getView(int posicao, View componente, ViewGroup pai) {
		Historico historico = (Historico)getItem(posicao);
		ViewHolder holder;
		LayoutInflater inflater =  (LayoutInflater)contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		if (componente == null) {
			componente = inflater.inflate(R.layout.lista_titulo_sumario_texto, null);
			holder = new ViewHolder();
			holder.titulo = (TextView)componente.findViewById(R.id.titulo_texto);
			holder.sumario = (TextView)componente.findViewById(R.id.sumario_texto);
			holder.texto = (TextView)componente.findViewById(R.id.texto_texto);
			componente.setTag(holder);
		}
		else
		{
			holder = (ViewHolder)componente.getTag();
		}

		holder.titulo.setText(historico.getIp_local() + " | " + historico.getIp_externo());
		holder.sumario.setText(historico.getMac());
		holder.texto.setText(historico.getData_hora().toString());

		return componente;
	}

	public void removeItem(int pos) {
		Object item = getItem(pos);
		listaHistorico.remove(item);
		notifyDataSetChanged();
	}
}