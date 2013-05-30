package com.myinfonow.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.myinfonow.R;
import com.myinfonow.MyInfoNow;

public class MenuAdapter extends BaseAdapter{
	Context contexto;
	MyInfoNow myInfoNow;
	
	public MenuAdapter(MyInfoNow c) {
		contexto = (Context)c;
		myInfoNow = c;
	}

	@Override
	public int getCount() {
		return myInfoNow.getNomesMenus().length;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view;
		if (convertView == null) { 
			LayoutInflater inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.item_menu_inicial, parent, false);
		} else {
			view = (View) convertView;
		}
		TextView textView = (TextView)view.findViewById(R.id.item_menu_inicial_icone_texto);
		textView.setText(myInfoNow.getNomesMenus()[position]);
		ImageView imageView = (ImageView)view.findViewById(R.id.item_menu_inicial_icone_imagem);
		imageView.setImageResource(myInfoNow.getImagensMenus()[position]);
		imageView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				Animation fadeOut = new AlphaAnimation(1, 0);
				fadeOut.setDuration(300);
				view.startAnimation(fadeOut);
				view.getAnimation().startNow();

				myInfoNow.trataEventoMenu(position);
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