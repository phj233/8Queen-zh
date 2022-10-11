package net.mooctest;
/**
 * @file Node.java
 * @author Natasha Squires <nsquires@upei.ca>
 * Represents a Queen
 */
import java.util.*;

public class Node implements Comparable<Node>{
	private static final int N=8; //8 queens	8个皇后棋子
	public Queen[] state; //the node's state	节点状态
	private ArrayList<Node> neighbours;	//相邻节点数组
	private int hn; //heuristic score	启发分数
	
	/**
	 * Constructor	构造函数
	 */
	public Node(){
		state = new Queen[N]; //empty state	空的状态
		neighbours = new ArrayList<Node>(); //empty neighbour list	空的相邻链表
	} 
	
	/**
	 * Constructor which creates a copy of a node's state	构造函数，它创建节点状态的副本
	 * @param n	参数node对象 
	 */
	public Node(Node n){
		state = new Queen[N];
		neighbours = new ArrayList<Node>();
		for(int i=0; i<N; i++)
			state[i] = new Queen(n.state[i].getRow(), n.state[i].getColumn());
		hn=0;
	}
	
	/**
	 * Generates the possible chess board configurations given a
	 * specific board state	在给定特定棋盘状态的情况下，生成可能的棋盘配置
	 * @param startState	node对象 起始状态
	 */
	public ArrayList<Node> generateNeighbours(Node startState){
		int count=0;
		
		if(startState==null)
			System.out.println("warning");
		else{
	//		System.out.println("hmm?");
		//	System.out.println(startState);
		}
		
		for(int i=0; i<N; i++){
			for(int j=1; j<N; j++){
				neighbours.add(count, new Node(startState));
				neighbours.get(count).state[i].moveDown(j);
				//make sure to compute its hn value	确保计算它们的hn值
				neighbours.get(count).computeHeuristic();
				
				count++;
			}
		}
		
		return neighbours;		//返回相邻节点
	}
	
	/**
	 * Returns a randomly generated neighbour of a given state 返回随机生成的相邻节点的状态
	 * @param startState
	 * @return	返回相邻节点 先new一个起始状态节点 然后在0-6之间随机数移动，之后计算启发式
	 */
	public Node getRandomNeighbour(Node startState){
		Random gen = new Random();
		
		int col = gen.nextInt(N);
		int d = gen.nextInt(N-1)+1;
		
		Node neighbour = new Node(startState);
		neighbour.state[col].moveDown(d);
		neighbour.computeHeuristic();
		
		return neighbour;
	}
	
	/**
	 * computes the heuristic, which is the number of 
	 * pieces that can attack each other	计算启发式(heuristic)，即可以相互攻击的棋子的数量
	 * @return int	返回分数
	 */
	public int computeHeuristic(){
	
		for(int i=0; i<N-1; i++){
			for(int j=i+1; j<N; j++){
				if(state[i].canAttack(state[j])){
						hn++;
				}
			}
		}
		
		return hn;
	}
	

	
	/**
	 * Hn getter	获取启发分数
	 * @return
	 */
	public int getHeuristic(){
		return hn;
	}
	
	/**
	 * Implements Comparable method compareTo, judges a comparison
	 * on the basis of a Node's heuristic value	实现比较方法compareTo，根据节点的启发式值判断比较
	 * @param n
	 * @return int	
	 */
	public int compareTo(Node n){
		if(this.hn < n.getHeuristic())
			return -1;
		else if(this.hn > n.getHeuristic())
			return 1;
		else 
			return 0;
	}
	
	/**
	 * state setter	设置状态
	 * @param s
	 */
	public void setState(Queen[] s){
		for(int i=0; i<N; i++){
			state[i]= new Queen(s[i].getRow(), s[i].getColumn());
		}
	}
	
	
	/**
	 * state getter	获取状态
	 * @return	返回状态
	 */
	public Queen[] getState(){
		return state;
	}
	
	/**
	 * toString method prints out Node's state	toString方法打节点的状态
	 * @return String	返回rs结果
	 */
	public String toString(){
		String result="";
		String[][] board = new String[N][N];
		
		//initialise board with X's to indicate empty spaces	用X初始化板以表示空白
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++)
				board[i][j]="X ";
		
		//place the queens on the board
		for(int i=0; i<N; i++){
			board[state[i].getRow()][state[i].getColumn()]="Q ";	//把q放在棋盘上
		}
		
		//feed values into the result string	将值输入rs结果
		for(int i=0; i<N; i++){
			for(int j=0; j<N; j++){
				result+=board[i][j];
			}
			result+="\n";
		}
		
		return result;
	}
}
