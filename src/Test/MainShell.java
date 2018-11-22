package Test;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class MainShell {

	protected Shell shell;
	private Text textImageBefore;
	private Text textImageAfter;
	private Label lblNewLabel;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainShell window = new MainShell();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setImage(SWTResourceManager.getImage("C:\\Users\\xuchunlin\\Pictures\\\u77E2\u91CF\u56FE\u6807\\imageTest\\\u52FA\u5B50 \u76D8\u5B50.png"));
		shell.setBackground(SWTResourceManager.getColor(240, 240, 240));
		shell.setSize(555, 368);
		shell.setText("\u56FE\u50CF\u8BC6\u522B\u6D4B\u8BD5");
		
		Label imageBefore = new Label(shell, SWT.NONE);
		imageBefore.setBounds(85, 63, 90, 24);
		imageBefore.setText("\u9910\u524D\u56FE\u7247");
		
		Label imageAfter = new Label(shell, SWT.NONE);
		imageAfter.setBounds(85, 131, 90, 24);
		imageAfter.setText("\u9910\u540E\u56FE\u7247");
		
		textImageBefore = new Text(shell, SWT.BORDER);
		textImageBefore.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		textImageBefore.setBounds(181, 60, 267, 30);
		
		textImageAfter = new Text(shell, SWT.BORDER);
		textImageAfter.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		textImageAfter.setBounds(181, 128, 267, 30);
		
		Button testButton = new Button(shell, SWT.CENTER);
		testButton.setForeground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		testButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String imageBefore=textImageBefore.getText();
				String imageAfter=textImageAfter.getText();
				//Ò³ÃæÌø×ª
				Shell oldShell=shell ;
				Shell infoShell=new InfoDisplayShell(imageBefore,imageAfter);
				shell=infoShell;
				shell.open();
				oldShell.dispose();
						
			}
		});
		testButton.setBounds(244, 193, 114, 34);
		testButton.setText("\u5F00\u59CB\u6D4B\u8BD5");
		
		lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewLabel.setBounds(161, 278, 287, 24);
		lblNewLabel.setText("\u63D0\u793A\uFF1A\u8F93\u5165\u56FE\u7247\u6587\u4EF6\u540D(\u5305\u542B\u540E\u7F00)");

	}
}
