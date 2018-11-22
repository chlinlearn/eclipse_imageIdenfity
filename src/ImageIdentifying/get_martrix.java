package ImageIdentifying;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import java.io.*;


public class get_martrix {
	public static void main(String arg[]){
		String image = "C:\\Users\\xuchunlin\\Desktop\\ͼ��������ͼƬ1\\ѹ����ͼƬ\\5.jpg";
		get_martrix a = new get_martrix(image);
		int[][][] RGB_martrix = a.get_RGB_martrix();
		for(int i=0;i<RGB_martrix.length;i++){
			for(int j=0;j<RGB_martrix[0].length;j++){
				System.out.print(RGB_martrix[i][j][0]);
				System.out.print('\t');
			}
			System.out.print('\n');
		}
		System.out.println(RGB_martrix.length);
		System.out.println(RGB_martrix[0].length);
		System.out.println("ͼƬ3ά�������ɳɹ�....");
	}
	private String image_path;
	
	public get_martrix(){
	}
	public get_martrix(String image_path){
		this.image_path=image_path;
	}
	public void get_image_path(String image_path){
		this.image_path = image_path;
	}
	
	public int[][][] get_RGB_martrix() {
		File file = new File(image_path);
		BufferedImage image_martrix = null;
		try {
			image_martrix = ImageIO.read(file);
		}catch(Exception e) {
			e.printStackTrace();
		}
		int width  = image_martrix.getWidth();
		int height = image_martrix.getHeight();
		int[][][] RGB_martrix = new int[width][height][3];
		//��λ���㣬int��32λ������2,1,0 ���������
		for(int i=0;i<width;i++) {
			for(int j = 0;j<height;j++) {
				int pixel=image_martrix.getRGB(i, j);
				RGB_martrix[i][j][2] = (pixel & 0xff0000) >> 16;//16-24λ������ֵ����ɫ��32λ
				RGB_martrix[i][j][1] = (pixel & 0xff00) >> 8;//��ɫ
				RGB_martrix[i][j][0] = (pixel & 0xff);//��ɫ
			}
		}
		return RGB_martrix;
	}
	//�õ����Ⱦ���
	public int[][] get_light_martrix() {
		File file = new File(image_path);
		BufferedImage image_martrix = null;
		try {
			image_martrix = ImageIO.read(file);
		}catch(Exception e) {
			e.printStackTrace();
		}
		int width  = image_martrix.getWidth();
		int height = image_martrix.getHeight();
		int[] martrix = new int[3];
		int[][] light_martrix = new int[width][height];
		for(int i=0;i<width;i++) {
			for(int j = 0;j<height;j++) {
				int pixel=image_martrix.getRGB(i, j);
				martrix[2] = (pixel & 0x00ff0000) >> 16;
				martrix[1] = (pixel & 0x0000ff00) >> 8;
				martrix[0] = (pixel & 0x000000ff);
				light_martrix[i][j] = (martrix[2] + martrix[1] + martrix[0])/3;
			}
		}
		return light_martrix;
	}
}
