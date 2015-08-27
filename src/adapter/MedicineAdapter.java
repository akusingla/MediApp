package adapter;

import java.util.List;

import activities.HomeActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import beans.MedicineBean;

import com.example.mediapp.R;

public class MedicineAdapter extends ArrayAdapter<MedicineBean> {
	
	Context context;
	int layoutId;
	List<MedicineBean> information;
	MedicineBean bean;
	
	
public MedicineAdapter(Context context, int layoutId, List<MedicineBean> information) {
		super(context, layoutId,information);
		this.context=context;
		this.layoutId=layoutId;
		this.information=information;
		
	}

public View getView(int position, View convertView, ViewGroup parentView)
{
	LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	View view=inflater.inflate(layoutId, parentView ,false);
	TextView name= (TextView) view.findViewById(R.id.txtName);
	TextView price=(TextView) view.findViewById(R.id.txtPrice);
	TextView type=(TextView) view.findViewById(R.id.txtType);
	TextView unit=(TextView) view.findViewById(R.id.txtUnit);
	TextView stdPack=(TextView) view.findViewById(R.id.txtStdPack);
	TextView brand=(TextView) view.findViewById(R.id.txtBrand);
	bean =HomeActivity.lstMedicines.get(position);
	name.setText("Medicne Name:"+bean.getMedicineName());
	price.setText(String.valueOf("Price: "+bean.getPrice()));
	type.setText("Type:" + bean.getType());
	unit.setText("Unit" + bean.getUnit());
	stdPack.setText("Standard Pack:" +bean.getStandardPackaging());
	brand.setText("Brand Name:"+bean.getBrandName());
		return view;
}

}
