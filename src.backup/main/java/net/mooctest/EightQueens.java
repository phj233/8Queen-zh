package net.mooctest;
/**
 * @file EightQueens.java
 * @author nsquires
 * Solves the eight queens problem using various AI techniques
 */
import java.util.*;
import java.text.NumberFormat;

//     �? �? �?
public class EightQueens {
	
	public EightQueens(){
		//无参构�?�方�?
	}
	
	/**
	 * The starter board
	 *  比赛棋盘
	 * @return Queen[]
	 */
	public Queen[] generateBoard(){
		//棋盘生成
		Queen[] start = new Queen[8];
		Random gen = new Random();
		
		for(int i=0; i<8; i++){
			start[i] = new Queen(gen.nextInt(8),i);
		}
		return start;
	}
}
