package my.com.homesmartvertablet.adapter;

import java.util.List;

import com.example.homesmartvertablet.activity.R;

import my.com.homesmartvertablet.model.RoomItem;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListRoomAdapter extends ArrayAdapter<RoomItem>{

	private Context context;
	private int resourceId;
	private List<RoomItem>list;
	
	public ListRoomAdapter(Context context, int textViewResourceId,
			List<RoomItem> list) {
		super(context, textViewResourceId, list);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.resourceId = textViewResourceId;
		this.list = list;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if(convertView == null){
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(resourceId, null);

			holder = new ViewHolder();
			holder.imageRoom = (ImageView) convertView.findViewById(R.id.imageview_of_list_room);
			holder.imageName = (TextView)convertView.findViewById(R.id.text_of_item_list_room);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		RoomItem roomItem = getItem(position);
		switch(roomItem.getRoomId()){
			case 0:{
				holder.imageName.setText(roomItem.getNameRoom());
				holder.imageRoom.setImageResource(R.drawable.living_room);
				break;
			}
			case 1:{
				holder.imageName.setText(roomItem.getNameRoom());
				holder.imageRoom.setImageResource(R.drawable.bed_room);
				break;
			}
			case 2:{
				holder.imageName.setText(roomItem.getNameRoom());
				holder.imageRoom.setImageResource(R.drawable.kitchen_room);
				break;
			}
			case 3:{
				holder.imageName.setText(roomItem.getNameRoom());
				holder.imageRoom.setImageResource(R.drawable.toilet);
				break;
			}
		}
		return convertView;
	}
	public class ViewHolder{
		private ImageView imageRoom;
		private TextView imageName;
	}

}
