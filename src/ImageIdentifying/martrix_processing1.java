package ImageIdentifying;


public class martrix_processing1 {
	private int[][][] image_martrix;
	
 	public martrix_processing1(){
	}
 	public martrix_processing1(int[][][] image_martrix){
 		this.image_martrix = image_martrix;
 	}
 	public void set_image_martrix(int[][][] image_martrix){
 		this.image_martrix = image_martrix;
 	}
 	
	public int[][][] set_RGB_martrix(){
		int width = image_martrix.length;int length = image_martrix[0].length;
		int[][][] RGB_martrix = new int[width][length][3];
		for(int i=0;i<width;i++){
			for(int j=0;j<length;j++){
				for(int k=0;k<3;k++){
					RGB_martrix[i][j][k] = many_to_ten(image_martrix[i][j][k]);
				}
			}
		}
		return RGB_martrix;
	}
	/*下面函数用于将256的RGB像素值转换为10的像素值*/
	public int many_to_ten(int RGB){
		if(RGB>=0&&RGB<=15){
			return 0;
		}
		else if(RGB>15&&RGB<=45){
			return 30;
		}
		else if(RGB>45&&RGB<=75){
			return 60;
		}
		else if(RGB>75&&RGB<=105){
			return 90;
		}
		else if(RGB>105&&RGB<=135){
			return 120;
		}
		else if(RGB>135&&RGB<=165){
			return 150;
		}
		else if(RGB>165&&RGB<=195){
			return 180;
		}
		else if(RGB>195&&RGB<=225){
			return 210;
		}
		else if(RGB>225&&RGB<=255){
			return 240;
		}
		else{
			return 255;
		}
	}
}
