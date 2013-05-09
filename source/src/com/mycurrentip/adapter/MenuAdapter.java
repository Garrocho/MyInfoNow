package com.mycurrentip.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mycurrentip.MyCurrentIP;
import com.mycurrentip.R;

public class MenuAdapter extends BaseAdapter{
	Context contexto;
	MyCurrentIP myCurrentIP;
	
	public MenuAdapter(MyCurrentIP c) {
		contexto = (Context)c;
		myCurrentIP = c;
	}

	@Override
	public int getCount() {
		return myCurrentIP.getNomesMenus().length;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view;
		if (convertView == null) {  // if it's not recycled, initialize some attributes
			LayoutInflater inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.item_menu_inicial, parent, false);
		} else {
			view = (View) convertView;
		}
		TextView textView = (TextView)view.findViewById(R.id.item_menu_inicial_icone_texto);
		textView.setText(myCurrentIP.getNomesMenus()[position]);
		ImageView imageView = (ImageView)view.findViewById(R.id.item_menu_inicial_icone_imagem);
		imageView.setImageResource(myCurrentIP.getImagensMenus()[position]);
		imageView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				Animation fadeOut = new AlphaAnimation(1, 0);
				fadeOut.setDuration(300);
				view.startAnimation(fadeOut);
				view.getAnimation().startNow();

				myCurrentIP.trataEventoMenu(position);
			}
		});
		return view;
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}
}