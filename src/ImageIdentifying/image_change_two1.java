package ImageIdentifying;

import java.awt.Window;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import ImageIdentifying.get_martrix;
import ImageIdentifying.martrix_processing1;
import ImageIdentifying.get_index_and_number1;
public class image_change_two1 {
/*	public static void main(String args[]){
		ImageTest window = new ImageTest();	
        window.open();
        System.out.println("餐前路径："+window.getImageBefore()+"\n"+"餐后路径："+window.getImageAfter());
		String old_image_before_path = "C:\\Users\\xuchunlin\\Desktop\\图像鉴别测试图片1\\餐前随意拍照\\"+window.getImageBefore(); 
		String old_image_after_path = "C:\\Users\\xuchunlin\\Desktop\\图像鉴别测试图片1\\餐后随意拍照\\"+window.getImageAfter(); 
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
		System.out.print("光盘返回0，未光盘返回1：");
		System.out.print(m);
	}*/
	
	private int[] RGB_before_number;
	private int[] RGB_after_number;
	private int[] RGB_before_index;
	private int[] RGB_after_index;
	private int[][][] RGB_martrix;

	public image_change_two1(){
	}
	
	public void set_RGB_before_number(int[] RGB_before_number){
		this.RGB_before_number = RGB_before_number;
	}
	public void set_RGB_after_number(int[] RGB_after_number){
		this.RGB_after_number = RGB_after_number;
	}
	public void set_RGB_before_index(int[] RGB_before_index){
		this.RGB_before_index = RGB_before_index;
	}
	public void set_RGB_after_index(int[] RGB_after_index){
		this.RGB_after_index = RGB_after_index;
	}
	public void set_RGB_martrix(int[][][] RGB_martrix){
		this.RGB_martrix = RGB_martrix;
	}
	
	public int get_final_martrix(){
		float[] RGB_after_before_rate = new float[1000];
		int[] food = new int[50];int food_length=0;int food_number=0;
		int[] background = new int[50];int background_length=0;int background_number=0;
		/*下面第一个for循环得到各个像素比值的一维数组*/
		for(int i=0;i<1000;i++){
			if(RGB_before_number[i]!=0){
				float a = (float)RGB_after_number[i];float b = (float)RGB_before_number[i];
				RGB_after_before_rate[i] = a/b;
			}
			else{
				RGB_after_before_rate[i] = -1;
			}
		}
		//return RGB_after_before_rate;
		int[] before_after_index = new int[100];
		int before_after_index_length=0;
		/*下面两个for循环用于将餐前和餐后有效像素点整合到一个数组中*/
		for(int i=0;i<50;i++){
			before_after_index[i] = RGB_before_index[i];
			before_after_index_length++;
		}
		for(int i=0;i<50;i++){
			int a = add_after_index(RGB_after_index[i]);
			if(a!=-1){
				before_after_index[i+50] = a;
				before_after_index_length++;
			}
		}
		/*下面的for循环是为了找到食物的像素下标和背景像素的下标*/
		for(int i=0;i<before_after_index_length;i++){
			if(RGB_after_before_rate[before_after_index[i]]>0.8){
				background[background_length++] = before_after_index[i];
				background_number += RGB_after_number[before_after_index[i]];
			}
			else if(0<RGB_after_before_rate[before_after_index[i]] && RGB_after_before_rate[before_after_index[i]]<0.8){
				food[food_length++] = before_after_index[i];
				food_number += RGB_after_number[before_after_index[i]];
			}
			//添加背景一个判断-1(油渍)
			else if(RGB_after_before_rate[before_after_index[i]]==-1) {
				background[background_length++] = before_after_index[i];
				background_number += RGB_after_number[before_after_index[i]];
			}
		}
		float food_number_float = (float)food_number;float background_number_float = (float)background_number;
		if(food_number_float/background_number_float >0.1){
			return 1;//未光盘
		}else{
			return 0;//光盘
		}
		//添加一个判断容错率高一些（食物丰富的）
	}
	
	
	
	
/**
 * 待完善
 * */	
	
	
	/*add_after_index函数用于将餐前和餐后有效像素点整合到一个数组中*/
	public int add_after_index(int a){
		for(int i=0;i<50;i++){
			if(a==RGB_before_index[i]){
				return -1;
			}
		}
		return a;
	}
	
	//就是加一个函数many_to_two,代表将食物像素变为0，背景像素变为2，不在此列变为1,判断白米饭和白盘
	public int many_to_two(int R,int G,int B,int [] food,int [] background){
		int a=1;
		for(int i=0;i<food.length;i++){
			if(R*100+G*10+B==food[i]){
				a=0;
			}
		}
		for(int i=0;i<background.length;i++){
			if (R*100+R*10+B==background[i]) {
				a=2;
			}		
		}
		return a;
	}
	
	//生成图片
	public String get_new_image2(int[][][] RGB_martrix,String new_image_path,String new_image_name){
		int in_width  = RGB_martrix.length;
		int in_height = RGB_martrix[0].length;
		int a = 1;//详情请参考这个网站：https://wenda.so.com/q/1483474151727843，a值不一定为1，也可以是其它值
		int out_width = in_width;int out_height = in_height;
		BufferedImage out_image_martrix = new BufferedImage(out_width, out_height, a);
		for(int i=0;i<out_width;i++) {
			for(int j =0;j<out_height;j++) {
				int pixel = 0xff000000;
				pixel = ((pixel & 0xff000000) | (RGB_martrix[i][j][2]<<16) | (RGB_martrix[i][j][1]<<8) | (RGB_martrix[i][j][0]));
				out_image_martrix.setRGB(i, j, pixel);
			}
		}
		try{
			new_image_path = "C:\\Users\\xuchunlin\\Desktop\\图像鉴别测试图片\\生成图片\\";
			new_image_path = new_image_path + new_image_name + ".jpeg";
			ImageIO.write(out_image_martrix, "jpeg",  new File(new_image_path));
			out_image_martrix = null;
		}catch(Exception e){
			e.printStackTrace();
		}
		return new_image_path;
	}	
}
