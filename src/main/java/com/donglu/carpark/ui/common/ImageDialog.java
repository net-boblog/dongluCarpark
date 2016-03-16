package com.donglu.carpark.ui.common;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.donglu.carpark.util.CarparkUtils;
import com.dongluhitec.card.domain.util.StrUtil;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;

public class ImageDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	String img;
	static Image image;
	private CLabel lbl_image;
	private byte[] lastImage;

	/**
	 * 
	 * @param img 进出场记录的图片字段属性
	 */
	public ImageDialog(String img) {
		super(Display.getDefault().getActiveShell());
		setText("图片显示");
		this.img=img;
	}
	/**
	 * 
	 * @param image swt图片
	 */
	public ImageDialog(Image image) {
		super(Display.getDefault().getActiveShell());
		setText("图片显示");
		ImageDialog.image=image;
	}
	public ImageDialog(byte[] lastImage) {
		super(Display.getDefault().getActiveShell());
		setText("图片显示");
		this.lastImage=lastImage;
	}
	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		shell.setMaximized(true);
		shell.addDisposeListener(new DisposeListener() {
			@Override
			public void widgetDisposed(DisposeEvent e) {
				System.out.println("imagedialog is disponse");
				
			}
		});
		
		if (!StrUtil.isEmpty(img)) {
			setImage(img);
		}else if (!StrUtil.isEmpty(image)){
			setImage();
		}else if (!StrUtil.isEmpty(lastImage)){
			setImage(lastImage);
		}
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}


	private void setImage(byte[] lastImage2) {
		image=CarparkUtils.getImage(lastImage2, lbl_image, shell);
		lbl_image.setImage(image);
	}
	private void setImage() {
		
		image=CarparkUtils.getImage(CarparkUtils.getImageBytes(image), lbl_image, shell);
		lbl_image.setImage(image);
	}
	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), SWT.DIALOG_TRIM);
		shell.setSize(1280, 720);
		shell.setText(getText());
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		lbl_image = new CLabel(shell, SWT.NONE);

	}
	private void setImage(String img) {
		CarparkUtils.setBackgroundImage(CarparkUtils.getImageByte(img), lbl_image, img);
	}
	public static void main(String[] args) {
		ImageDialog d=new ImageDialog("");
		d.open();
	}
}
