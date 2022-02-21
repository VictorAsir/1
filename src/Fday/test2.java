package Fday;
import java.util.Scanner;
public class test2 {

    static int MAX_VALUE = 100;
    int row;int line;
    int[][] Figure;int[][] Tags;//记录到走过的点的步数
    //移动偏向(-1向左，1向右)
    int DIRECTION;
    //起点和终点的坐标
    int x1;int y1;int x2;int y2;



    public void Init() {
        Scanner sr = new Scanner(System.in);
        row = sr.nextInt();
        line = sr.nextInt();
        Figure = new int[row][line];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < line; j++) {
                Figure[i][j] = sr.nextInt();
            }
        }
        x1 = sr.nextInt();
        y1 = sr.nextInt();
        x2 = sr.nextInt();
        y2 = sr.nextInt();
        Tags = new int[row][line];
        //初始化记录步数的数组
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < line; j++) {
                //MAX_VALUE代表没走过
                Tags[i][j] = MAX_VALUE;
            }
        }
        //初始化移动偏向
        if(y1 < y2) {
            DIRECTION = 1;
        }else if(y1 > y2){
            DIRECTION = -1;
        }else {
            DIRECTION = 0;
        }
        Figure[x1][y1] = 1;
        Tags[x1][y1] = 0;
    }

    //单点向周围分支
    public void Branch(int x,int y,int D) {
        //限界
        if(Tags[x][y]>=D) {
            Tags[x][y] = D;
        }else {
            return;
        }
        //使用移动偏好
        if(y <= y1 && DIRECTION == 1) {
            //越界判断应该在前面不然后面的Figure[x+1][y] == 0可能报错
            if(!isBorder(x+1, y) && Figure[x+1][y] == 0) {
                Branch(x+1,y,D+1);
            }
            if( !isBorder(x, y+1) && Figure[x][y+1] == 0) {
                Branch(x,y+1,D+1);
            }
            if(!isBorder(x-1, y) && Figure[x-1][y] == 0) {
                Branch(x-1,y,D+1);
            }
        }else if(y >= y1 && DIRECTION == -1) {
            if(!isBorder(x-1, y) && Figure[x-1][y] == 0) {
                Branch(x-1,y,D+1);
            }
            if(!isBorder(x+1, y) && Figure[x+1][y] == 0) {
                Branch(x+1,y,D+1);
            }
            if(!isBorder(x, y-1) && Figure[x][y-1] == 0) {
                Branch(x,y-1,D+1);
            }
        }else {
            if(!isBorder(x+1, y) && Figure[x+1][y] == 0) {
                Branch(x+1,y,D+1);
            }
            if(!isBorder(x-1, y) && Figure[x-1][y] == 0) {
                Branch(x-1,y,D+1);
            }
            if(!isBorder(x, y+1) && Figure[x][y+1] == 0) {
                Branch(x,y+1,D+1);
            }
            if(!isBorder(x, y-1) && Figure[x][y-1] == 0) {
                Branch(x,y-1,D+1);
            }
        }
    }

    //判断是否超过边界
    public boolean isBorder(int x,int y) {
        if(x<0||y<0||x >= row||y >= line) {
            return true;
        }else {
            return false;
        }
    }

    public static void main(String[] args) {
        test2 test2 = new test2();
        test2.Init();
        test2.Branch(test2.x1, test2.y1, 0);
        if(test2.Tags[test2.x2][test2.y2]<=0){
            System.out.println("0");
        }else
            System.out.println(test2.Tags[test2.x2][test2.y2]);
    }
}