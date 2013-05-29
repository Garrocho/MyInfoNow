package com.mycurrentip.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mycurrentip.R;
import com.mycurrentip.classes.Historico;

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
		TextView ip_local;
		TextView ip_externo;
		TextView data_hora;
	}

	@Override
	public View getView(int posicao, View componente, ViewGroup pai) {
		Historico historico = (Historico)getItem(posicao);
		ViewHolder holder;
		LayoutInflater inflater =  (LayoutInflater)contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		if (componente == null) {
			componente = inflater.inflate(R.layout.lista_itens_ips_anteriores, null);
			holder = new ViewHolder();
			holder.ip_local = (TextView)componente.findViewById(R.id.lista_itens_ips_anteriores_ip);
			holder.data_hora = (TextView)componente.findViewById(R.id.lista_itens_ips_anteriores_data_hora);
			componente.setTag(holder);
		}
		else
		{
			holder = (ViewHolder)componente.getTag();
		}

		holder.ip_local.setText(historico.getIp_local());
		holder.data_hora.setText(historico.getData_hora().toString());

		return componente;
	}

	public void removeItem(int pos) {
		Object item = getItem(pos);
		listaHistorico.remove(item);
		notifyDataSetChanged();
	}
}