package Test;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

import ImageIdentifying.get_index_and_number1;
import ImageIdentifying.get_martrix;
import ImageIdentifying.get_same_image_jpg;
import ImageIdentifying.image_change_two1;
import ImageIdentifying.martrix_processing1;

public class InfoDisplayShell extends Shell {
	private Text textInfo;
	private String imageBefore=null;
	private String imageAfter=null;	

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			InfoDisplayShell shell = new InfoDisplayShell(display);
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the shell.
	 * @param display
	 * @wbp.parser.constructor
	 */
	public InfoDisplayShell(Display display) {
		super(display, SWT.SHELL_TRIM);
		setImage(SWTResourceManager.getImage("C:\\Users\\xuchunlin\\Pictures\\\u77E2\u91CF\u56FE\u6807\\imageTest\\\u52FA\u5B50 \u76D8\u5B50.png"));
		
		Label labelInfo = new Label(this, SWT.WRAP);
		labelInfo.setFont(SWTResourceManager.getFont("Microsoft Yi Baiti", 12, SWT.NORMAL));
		labelInfo.setAlignment(SWT.CENTER);
		labelInfo.setText("\u6D4B\u8BD5\u7ED3\u679C");
		labelInfo.setBounds(227, 51, 104, 24);
		
		textInfo = new Text(this, SWT.WRAP | SWT.CENTER);
		textInfo.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		textInfo.setBounds(159, 94, 236, 30);
		
		Button returnButton = new Button(this, SWT.CENTER);
		returnButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MainShell test=new MainShell();
				InfoDisplayShell.this.dispose();
				test.open();
			}
		});
		returnButton.setBounds(227, 164, 104, 34);
		returnButton.setText("\u7EE7\u7EED\u6D4B\u8BD5");
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("\u4FE1\u606F\u663E\u793A");
		setSize(550, 332);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public InfoDisplayShell(String imageBefore,String imageAfter){
		this(Display.getDefault());
		this.imageBefore=imageBefore;
		this.imageAfter=imageAfter;
		displayInfo();
  }
	private void displayInfo() {
		System.out.println(imageBefore+"\n"+imageAfter);
		String old_image_before_path = "C:\\Users\\xuchunlin\\Desktop\\图像鉴别测试图片1\\餐前随意拍照\\"+imageBefore; 
		String old_image_after_path = "C:\\Users\\xuchunlin\\Desktop\\图像鉴别测试图片1\\餐后随意拍照\\"+imageAfter; 
	 
		System.out.println(old_image_before_path);
		System.out.println(old_image_after_path);
		get_same_image_jpg l1 = new get_same_image_jpg(old_image_before_path);
		get_same_image_jpg l2 = new get_same_image_jpg(old_image_after_path);
		String image_before_path = l1.get_image(); 
		String image_after_path = l2.get_image();
		get_martrix a1 = new get_martrix(image_before_path);
		get_martrix a2 = new get_martrix(image_after_path);
		int[][][] RGB_martrix1 = a1.get_RGB_martrix();a1 = null;
		int[][][] RGB_martrix2 = a2.get_RGB_martrix();a2 = null;
		martrix_processing1 b1 = new martrix_processing1(RGB_martrix1);RGB_martrix1 = null;
		martrix_processing1 b2 = new martrix_processing1(RGB_martrix2);RGB_martrix2 =null;
		RGB_martrix1 = b1.set_RGB_martrix();
		RGB_martrix2 = b2.set_RGB_martrix();
		get_index_and_number1 c1 = new get_index_and_number1(RGB_martrix1);
		get_index_and_number1 c2 = new get_index_and_number1(RGB_martrix2);
		int[] RGB_before_number = c1.RGB_number();
		int[] RGB_before_index = c1.get_index();
		int[] RGB_after_number = c2.RGB_number();
		int[] RGB_after_index = c2.get_index();
		image_change_two1 d = new image_change_two1();
		d.set_RGB_before_number(RGB_before_number);
		d.set_RGB_after_number(RGB_after_number);
		d.set_RGB_before_index(RGB_before_index);
		d.set_RGB_after_index(RGB_after_index);
		int m = d.get_final_martrix();
		System.out.println("返回值为0则光盘，返回值为1则未光盘");
		System.out.print("返回值："+m);
		//返回值为0则光盘，返回值为1则未光盘
		if(m==0){
		    textInfo.append("光盘");	
		}
		else {
			textInfo.append("未光盘");
		}
	}
}
