package com.zhoujg77.treelistview.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.LightingColorFilter;
import android.opengl.Visibility;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zhoujg77.treelistview.R;
import com.zhoujg77.treelistview.util.Node;
import com.zhoujg77.treelistview.util.TreeHelper;
import com.zhoujg77.treelistview.util.adapter.TreeListViewAdapter;

public class SimpleTreeListViewAdapter<T> extends TreeListViewAdapter<T> {
	
	public SimpleTreeListViewAdapter(ListView tree, Context context,
			List<T> datas, int defaultExpandLevel)
			throws IllegalArgumentException, IllegalAccessException {
		super(tree, context, datas, defaultExpandLevel);
	}

	@Override
	public View getConverView(Node node, int position, View convertView,
			ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView ==null) {
			convertView = mInflater.inflate(R.layout.list_item, parent,false);
			holder = new ViewHolder();
			holder.mIcon = (ImageView) convertView.findViewById(R.id.id_item_icon);
			holder.mText = (TextView) convertView.findViewById(R.id.id_item_text);
			
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		if (node.getIcon()==-1) {
			holder.mIcon.setVisibility(View.INVISIBLE);
		}else{
			holder.mIcon.setVisibility(View.VISIBLE);
			holder.mIcon.setImageResource(node.getIcon());
		}
		
		holder.mText.setText(node.getName());
		return convertView;
	}
	
	private class ViewHolder{
		ImageView mIcon;
		TextView mText;
	}
	/**
	 * 动态插入节点
	 * @param position
	 * @param text
	 */
	public void addExtraNode(int position, String text) {
		Node node = mVisibleNodes.get(position);
		int indexOf = mAllnodes.indexOf(node);
		Log.e("TAG", "--"+indexOf);
		Node extraNode = new Node(-1, node.getId(), text);
		
		node.getChildren().add(extraNode);
		mAllnodes.add(indexOf+1,extraNode);
		mVisibleNodes = TreeHelper.filterVisibleNode(mAllnodes);
		notifyDataSetChanged();
	}

}
