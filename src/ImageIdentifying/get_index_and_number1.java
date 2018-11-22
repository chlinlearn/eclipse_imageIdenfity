package ImageIdentifying;

import java.util.Arrays;

import ImageIdentifying.get_martrix;
import ImageIdentifying.martrix_processing1;

public class get_index_and_number1 {
	public static void main(String args[]){
		String image_path = "C:\\Users\\xuchunlin\\Desktop\\图像鉴别测试图片1\\压缩后图片\\5.jpg";
		get_martrix a =new get_martrix(image_path);
		int[][][] RGB_martrix = a.get_RGB_martrix();a = null;
		martrix_processing1 b = new martrix_processing1(RGB_martrix);RGB_martrix = null;
		RGB_martrix = b.set_RGB_martrix();b=null;
		get_index_and_number1 c = new get_index_and_number1(RGB_martrix);
		int[] RGB_statistics_number = c.RGB_number();
		int[] index = c.get_index();
		for(int i=0;i<50;i++){
			System.out.print(index[i]);
			System.out.print('\t');
		}
		System.out.print('\n');
		for(int i=0;i<50;i++){
			System.out.print(RGB_statistics_number[index[i]]);
			System.out.print('\t');
		}
	}
	private int[][][] RGB_martrix;
	public get_index_and_number1(){
	}
	public get_index_and_number1(int[][][] RGB_martrix){
		this.RGB_martrix = RGB_martrix;
	}
	public void set_RGB_martrix(int[][][] RGB_martrix){
		this.RGB_martrix = RGB_martrix;
	}
	//数量更10*10*10有关
	public int[] RGB_number(){
		int[] RGB_statistic_number = new int[1000];
		for(int i=0;i<1000;i++){
			RGB_statistic_number[i]=0;
		}
		int[][][] RGB_KEY = new int[RGB_martrix.length][RGB_martrix[0].length][RGB_martrix[0][0].length];
		/*下面的for循环用于得到每一个像素点的个数*/
		//取决于10*10*10
		for(int i=0;i<RGB_martrix.length;i++){
			for(int j=0;j<RGB_martrix[0].length;j++){
				for(int k=0;k<RGB_martrix[0][0].length;k++){
					RGB_KEY[i][j][k] = values_to_keys(RGB_martrix[i][j][k]);
				}
				RGB_statistic_number[RGB_KEY[i][j][2]*100+RGB_KEY[i][j][1]*10+RGB_KEY[i][j][0]]++;
			}
		}
		return RGB_statistic_number;
	}
	//取得范围可改
	public int[] get_index(){
		int[] RGB_statistic_number = RGB_number();
		int[] RGB_number_copy = new int[RGB_statistic_number.length];
		for(int i=0;i<RGB_statistic_number.length;i++){
			RGB_number_copy[i] = RGB_statistic_number[i];
		}
		int[] RGB_number_index = new int[50];
		for(int i=0;i<50;i++){
			RGB_number_index[i]=0;
		}
		Arrays.sort(RGB_number_copy);//对数值个数排序
		/*用于找到前50种像素点的下标*/
		for(int i=RGB_number_copy.length-1;i>RGB_number_copy.length-51;i--){
			for(int j=0;j<RGB_number_copy.length;j++){
				if(RGB_statistic_number[j] == RGB_number_copy[i]){
					RGB_number_index[RGB_number_copy.length-i-1] = j;
					RGB_statistic_number[j] = -1;
					break;
				}
			}
		}
		return RGB_number_index;
	}
	/*将像素值转换为123，便于后续的排序处理*/
	//算法思路,空间换时间
	public int values_to_keys(int RGB){
		if(RGB>=0&&RGB<=15){
			return 0;
		}
		else if(RGB>15&&RGB<=45){
			return 1;
		}
		else if(RGB>45&&RGB<=75){
			return 2;
		}
		else if(RGB>75&&RGB<=105){
			return 3;
		}
		else if(RGB>105&&RGB<=135){
			return 4;
		}
		else if(RGB>135&&RGB<=165){
			return 5;
		}
		else if(RGB>165&&RGB<=195){
			return 6;
		}
		else if(RGB>195&&RGB<=225){
			return 7;
		}
		else if(RGB>225&&RGB<=255){
			return 8;
		}
		else{
			return 9;
		}
	}
}
