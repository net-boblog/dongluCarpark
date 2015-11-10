package com.donglu.carpark.ui.view;

import java.util.Date;

import org.eclipse.swt.widgets.Composite;

import com.donglu.carpark.ui.common.AbstractListPresenter;
import com.donglu.carpark.ui.common.Presenter;
import com.donglu.carpark.ui.list.InOutHistoryListPresenter;
import com.donglu.carpark.ui.list.ReturnAccountListPresenter;
import com.donglu.carpark.ui.list.SystemLogListPresenter;
import com.donglu.carpark.ui.list.UserListPresenter;
import com.dongluhitec.card.domain.db.singlecarpark.SystemOperaLogTypeEnum;
import com.google.inject.Inject;

public class SystemLogPresenter  extends AbstractListPresenter{
	private SystemLogView view;
	@Inject
	private SystemLogListPresenter listPresenter;
	@Override
	public void go(Composite c) {
		view=new SystemLogView(c, c.getStyle());
		view.setPresenter(this);
		listPresenter.go(view.getListComposite());
	}
	public SystemLogListPresenter getListPresenter() {
		return listPresenter;
	}
	
	public void search(String operaName, Date start, Date end, SystemOperaLogTypeEnum type) {
		if (type.equals(SystemOperaLogTypeEnum.全部)) {
			type=null;
		}
		listPresenter.search(operaName, start, end, type);
	}
	
}
