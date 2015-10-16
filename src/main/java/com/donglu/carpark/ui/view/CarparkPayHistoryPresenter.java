package com.donglu.carpark.ui.view;

import java.util.Date;
import java.util.List;

import javax.security.auth.Refreshable;

import org.eclipse.swt.widgets.Composite;

import com.donglu.carpark.service.CarparkDatabaseServiceProvider;
import com.donglu.carpark.ui.common.AbstractListView;
import com.donglu.carpark.ui.list.CarparkPayHistoryListPresenter;
import com.donglu.carpark.ui.list.CarparkPayHistoryListView;
import com.dongluhitec.card.domain.db.singlecarpark.SingleCarparkMonthlyUserPayHistory;
import com.google.inject.Inject;

public class CarparkPayHistoryPresenter {
	private CarparkPayHistoryView view;
	int max=0;
	int size=50;
	String userName, operaName;
	Date start,  end;
	CarparkPayHistoryListView carparkPayHistoryListView;
	
	@Inject
	private CarparkDatabaseServiceProvider sp;
	@Inject
	private CarparkPayHistoryListPresenter carparkPayHistoryListPresenter;
	
	public Composite getView(Composite parent, int style){
		view=new CarparkPayHistoryView(parent, style);
		view.setCarparkPayHistoryPresenter(this);
		carparkPayHistoryListPresenter.go(view.getListComposite());
		return view;
	}
	
	public void searchCharge(String userName, String operaName, Date start, Date end) {
		this.userName=userName;
		this.operaName=operaName;
		this.start=start;
		this.end=end;
		carparkPayHistoryListPresenter.searchCharge(userName, operaName, start, end);
	}
}