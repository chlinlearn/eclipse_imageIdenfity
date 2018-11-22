package ImageIdentifying;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class get_same_image_jpg {
//	public static void main(String arg[]){
//		String image = "C:\\Users\\xuchunlin\\Desktop\\ͼ��������ͼƬ1\\�ͺ���������\\6��δ���̣�.jpg";
//		get_same_image_jpg a = new get_same_image_jpg(image);
//		String new_image = a.get_image();
//	}
	private String old_image_path;
	public void set_oldimagePath(String oldimagePath){
		this.old_image_path = oldimagePath;
	}
	public get_same_image_jpg(){
	}
	public get_same_image_jpg(String oldimagePath){
		this.old_image_path = oldimagePath;
	}
	public String get_image(){
		//���ڵõ�image�е��ļ���
		String new_path = null;
		int begin = old_image_path.lastIndexOf("\\");
		int end = old_image_path.lastIndexOf(".");
		String image_name = old_image_path.substring(begin+1,end);
		//���ڵ���ɵĴ�����ͼƬ������С����ͼƬ
		File in_file = new File(old_image_path);
		//����һ��ͼƬ����
		BufferedImage in_image_martrix = null;
		try {
			in_image_martrix = ImageIO.read(in_file);
		}catch(Exception e) {
			e.printStackTrace();
		}
		int in_width  = in_image_martrix.getWidth();
		int in_height = in_image_martrix.getHeight();
		int in_minx   = in_image_martrix.getMinX();
		int in_miny   = in_image_martrix.getMinY();
		int a = in_image_martrix.getType();
		int out_width = in_width;int out_height = in_height;int multiple = 1;
		while(out_width * out_height > 1000000){
			//��С���ص�
			out_width = out_width/2;out_height = out_height/2;
			multiple = multiple * 2;//���ص��λ
		}	
		//����һ��ͼƬ����
		BufferedImage out_image_martrix = new BufferedImage(out_width, out_height, a);
		//����ѹ��ͼƬ
		//����һЩ���ص�
		/*pixel��32λ����߰�λ����͸���ȣ�Ȼ������Ϊr,g,b����ֵ*/
		for(int i=0;i<out_width;i++) {
			for(int j =0;j<out_height;j++) {
				int pixel =in_image_martrix.getRGB(i*multiple+in_minx, j*multiple+in_miny);
				out_image_martrix.setRGB(i, j, pixel);
			}
		}
		try{
			new_path = "C:\\Users\\xuchunlin\\Desktop\\ͼ��������ͼƬ\\ѹ����ͼƬ\\";
			new_path = new_path + image_name + ".jpg";
			//in_file.delete();//ɾ��ԭ����ͼƬ
			ImageIO.write(out_image_martrix,"jpg", new File(new_path));//����ͼƬ
			in_image_martrix = null;
			out_image_martrix = null;
		    System.out.println("ͼƬѹ���ɹ�....");
		}catch(Exception e){
			e.printStackTrace();
		}
		return new_path;
	}
}
