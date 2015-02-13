package com.zhoujg77.treelistview;

import java.util.ArrayList;
import java.util.List;

import com.zhoujg77.treelistview.adapter.SimpleTreeListViewAdapter;
import com.zhoujg77.treelistview.bean.FileBean;
import com.zhoujg77.treelistview.util.Node;
import com.zhoujg77.treelistview.util.adapter.TreeListViewAdapter.OnTreeNodeClickLister;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private ListView mTree;
	private SimpleTreeListViewAdapter<FileBean> mAdapter;
	private List<FileBean> mDatas;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mTree = (ListView) findViewById(R.id.id_listview);
		
		initDatas();
		try {
			mAdapter = new SimpleTreeListViewAdapter<FileBean>(mTree, this, mDatas, 0);
			mTree.setAdapter(mAdapter);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		initEvent();
		
	}
	private void initEvent() {
		mAdapter.setOnTreeNodeClickLister(new OnTreeNodeClickLister() {

			@Override
			public void OnClick(Node node, int position) {
				if (node.isLeaf()) {
					Toast.makeText(MainActivity.this, node.getName(), Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		
		mTree.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					final int position, long id) {
					final EditText editText = new EditText(MainActivity.this);
				
				new AlertDialog.Builder(MainActivity.this).setTitle("Add node")
				.setView(editText).setPositiveButton("确定", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						if (TextUtils.isEmpty(editText.getText().toString())) {
							return;
						}
						mAdapter.addExtraNode(position,editText.getText().toString());
					}
				}).setNegativeButton("取消", null).show();
				return true;
			}
		});
	}
	private void initDatas() {
		mDatas = new ArrayList<FileBean>();
		FileBean bean = new FileBean(1,0,"根目录一");
		mDatas.add(bean);
		bean = new FileBean(2,0,"根目录二");
		mDatas.add(bean);
		bean = new FileBean(3,0,"根目录三");
		mDatas.add(bean);
		bean = new FileBean(4,1,"根目录1-1");
		mDatas.add(bean);
		bean = new FileBean(5,1,"根目录1-2");
		mDatas.add(bean);
		bean = new FileBean(6,5,"根目录1-2-1");
		mDatas.add(bean);
		bean = new FileBean(7,2,"根目录2-2");
		mDatas.add(bean);
		bean = new FileBean(8,3,"根目录3-1");
		mDatas.add(bean);
		bean = new FileBean(9,3,"根目录3-2");
		mDatas.add(bean);
	}

}
