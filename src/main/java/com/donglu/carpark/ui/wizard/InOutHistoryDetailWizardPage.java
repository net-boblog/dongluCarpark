package com.donglu.carpark.ui.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;

import com.donglu.carpark.model.ShowInOutHistoryModel;
import com.donglu.carpark.util.ImageUtils;
import com.dongluhitec.card.domain.util.StrUtil;

import org.eclipse.swt.layout.GridData;


import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.core.databinding.beans.BeanProperties;

public class InOutHistoryDetailWizardPage extends WizardPage {
	@SuppressWarnings("unused")
	private DataBindingContext m_bindingContext;
	private ShowInOutHistoryModel model;
	private Label lbl_inBigImg;
	private Label lbl_outBigImg;
	private SashForm sashForm;
	private static Image inImage;
	private Image outImage;
	private Text text;
	private Boolean isEdit=false;
	
	/**
	 * Create the wizard.
	 * @param model 
	 * @param file 
	 * @wbp.parser.constructor
	 */
	public InOutHistoryDetailWizardPage(ShowInOutHistoryModel model) {
		super("wizardPage");
		setTitle("进出场抓拍信息");
		setDescription("进出场抓拍的图片信息");
		this.model=model;
	}

	public InOutHistoryDetailWizardPage(ShowInOutHistoryModel model2, Boolean isEdit) {
		this(model2);
		this.isEdit=isEdit;
		if (isEdit) {
			setDescription("点击完成修改车牌");
		}
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	@Override
	public void createControl(Composite parent) {
		parent.addDisposeListener(new DisposeListener() {
			@Override
			public void widgetDisposed(DisposeEvent e) {
				System.out.println("InOutHistoryDetailWizardPage is dispose");
				if (inImage!=null) {
					inImage.dispose();
					inImage=null;
				}
				if (outImage!=null) {
					outImage.dispose();
					outImage=null;
				}
			}
		});
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		container.setLayout(new GridLayout(1, false));
		
		Composite composite = new Composite(container, SWT.BORDER);
		composite.setLayout(new GridLayout(1, false));
		GridData gd_composite = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_composite.widthHint = 554;
		gd_composite.heightHint = 427;
		composite.setLayoutData(gd_composite);
		
		Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		composite_1.setLayout(new GridLayout(4, false));
		
		Label label = new Label(composite_1, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label.setText("车牌");
		
		text = new Text(composite_1, SWT.BORDER);
		GridData gd_text = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_text.widthHint = 99;
		text.setLayoutData(gd_text);
		text.setEditable(isEdit);
		
		
		Button button = new Button(composite_1, SWT.NONE);
		button.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, false, false, 1, 1));
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setImage();
			}
		});
		button.setText("刷新图片");
		new Label(composite_1, SWT.NONE);
		
		sashForm = new SashForm(composite, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Composite composite_4 = new Composite(sashForm, SWT.NONE);
		composite_4.setLayout(new GridLayout(1, false));
		
		lbl_inBigImg = new Label(composite_4, SWT.NONE);
		lbl_inBigImg.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Composite composite_5 = new Composite(sashForm, SWT.NONE);
		composite_5.setLayout(new GridLayout(1, false));
		
		lbl_outBigImg = new Label(composite_5, SWT.NONE);
		lbl_outBigImg.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		sashForm.setWeights(new int[] {1, 1});
		setImage();
		m_bindingContext = initDataBindings();
	}
	
	private void setImage() {
		getShell().getDisplay().asyncExec(new Runnable() {
			@Override
			public void run() {
				try {
					if (inImage!=null) {
						inImage.dispose();
						inImage=null;
					}
					if (outImage!=null) {
						outImage.dispose();
						outImage=null;
					}
					
					inImage = ImageUtils.getImage(ImageUtils.getImageByte(model.getBigImg()), lbl_inBigImg, getShell());
					lbl_inBigImg.setImage(inImage);
					outImage = ImageUtils.getImage(ImageUtils.getImageByte(model.getOutBigImg()), lbl_outBigImg, getShell());
					lbl_outBigImg.setImage(outImage);
					if (StrUtil.isEmpty(outImage)) {
						sashForm.setWeights(new int[]{1,0});
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	public void setBigImg(){
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public InOutHistoryDetailWizard getWizard() {
		
		return (InOutHistoryDetailWizard) super.getWizard();
	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextTextObserveWidget = WidgetProperties.text(SWT.Modify).observe(text);
		IObservableValue nowPlateNoModelObserveValue = BeanProperties.value("nowPlateNo").observe(model);
		bindingContext.bindValue(observeTextTextObserveWidget, nowPlateNoModelObserveValue, null, null);
		//
		return bindingContext;
	}
}
