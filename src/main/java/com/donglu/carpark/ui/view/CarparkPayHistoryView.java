package com.donglu.carpark.ui.view;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Group;

import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.donglu.carpark.ui.list.CarparkPayHistoryListView;

import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class CarparkPayHistoryView extends Composite{
	private Text text_pay_userName;
	private Text text_pay_operaName;
	
	private CarparkPayHistoryPresenter presenter;
	private CarparkPayHistoryListView carparkPayHistoryListView;
	
	public CarparkPayHistoryView(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(1, false));
		
		Group group = new Group(this, SWT.NONE);
		group.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		group.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		group.setText("查询");
		group.setLayout(new GridLayout(9, false));
		
		Label label = new Label(group, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label.setText("用户");
		
		text_pay_userName = new Text(group, SWT.BORDER);
		text_pay_userName.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		text_pay_userName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		Label label_1 = new Label(group, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_1.setText("操作员");
		
		text_pay_operaName = new Text(group, SWT.BORDER);
		text_pay_operaName.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		text_pay_operaName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		Label label_2 = new Label(group, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_2.setText("时间");
		
		DateTime dateTime = new DateTime(group, SWT.BORDER);
		dateTime.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		
		Label label_3 = new Label(group, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_3.setText("终止时间");
		
		DateTime dateTime_1 = new DateTime(group, SWT.BORDER);
		dateTime_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		
		Button button = new Button(group, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				presenter.searchCharge(carparkPayHistoryListView,text_pay_userName.getText(),text_pay_operaName.getText(),new Date(),new Date());
			}
		});
		button.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		button.setText("查询");
		
		carparkPayHistoryListView = new CarparkPayHistoryListView(this, SWT.NONE);
		carparkPayHistoryListView.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
	}

	public void setCarparkPayHistoryPresenter(CarparkPayHistoryPresenter carparkPayHistoryPresenter) {
		this.presenter = carparkPayHistoryPresenter;
	}
	
}
