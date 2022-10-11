package net.mooctest;
/**
 * @file Queen.java
 * @author nsquires
 * A Queen chess piece:一个 八 皇 后 象 棋 游 戏
 */

public class Queen {
	private int row; //行
	private int column; //列
	
	/**
	 * Constructor. Sets Queen's row and column
	 * 构造方法，设置皇后的行和列
	 * @param r
	 * @param c
	 */
	public Queen(int r, int c){
		row = r;
		column  = c;
	}
	
	/**
	 * Determines whether this queen can attack another 
	 * 决定一皇后是否可以攻击其他皇后
	 * @param q
	 * @return boolean
	 */
	public boolean canAttack(Queen q){
		//皇后之间不能互相攻击
		boolean canAttack=false;
		
		//test rows and columns
		//测试行和列
		if(row==q.getRow() || column==q.getColumn())
			//行或列相等
			//若皇后同时出现在同一行或者同一列，则返回T=失败
			canAttack=true;
		//test diagonal
		//测试对角线、斜线
		else if(Math.abs(column-q.getColumn()) == Math.abs(row-q.getRow()))
			//若两皇后的行和列相互交换匹配则判定两皇后处于同一条对角巷
			//则返回T=失败
			canAttack=true;
			
		return canAttack;
	}
	
	/**
	 * moves the piece down
	 *皇后往下移动的空间
	 * @param spaces
	 *参数=空间
	 */
	public void moveDown(int spaces){
		//row = row0 + space
		row += spaces;
		
		//bound check/reset
		//越界重置
		if(row>7 && row%7!=0){
			row=(row%7)-1;
		}
		else if(row>7 && row%7==0){
			row=7;
		}
	}
	//\
	// 下 面 是 set/ get/ toString 方 法
	//\
	/**
	 * row setter
	 * @param r
	 */
	public void setRow(int r){
		row = r;
	}
	
	/**
	 * row getter
	 * @return int
	 */
	public int getRow(){
		return row;
	}
	
	/**
	 * column setter
	 * @param c
	 */
	public void setColumn(int c){
		column = c;
	}
	
	/**
	 * column getter
	 * @return int
	 */
	public int getColumn(){
		return column;
	}
	
	public String toString(){
		return "("+row+", "+column+")";
	}
}
