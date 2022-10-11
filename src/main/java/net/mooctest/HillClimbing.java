package net.mooctest;
/**
 * @file HillClimbing.java
 * @author Natasha Squires <nsquires@upei.ca>
 * Implementation of the hill climbing algorithm
 */
import java.util.*;

public class HillClimbing {
	//攀登  = 比赛开始
	//最 大 限度8
	private final static int N=8;
	private Queen[] startState;
	private Node start; //start state
	private int nodesGenerated;
	
	/**
	 * Constructor
	 * 构造方法 : 初始化
	 */
	public HillClimbing(){
		//初始化状态 [null]
		start = new Node(); //empty start node
		startState = new Queen[N]; //empty start state
		startState();
		nodesGenerated=0;
	}
	
	/**
	 * Constructs HillClimbing with a starting board
	 * 开始入口
	 * @param s
	 */
	public HillClimbing(Queen[] s){
		start = new Node();
		startState = new Queen[N];
		for(int i=0; i<s.length; i++){
			//循环：获得每个皇后的坐标
			startState[i] = new Queen(s[i].getRow(), s[i].getColumn());
		}
		start.setState(startState);
		start.computeHeuristic();
		
		//？
		nodesGenerated=0;
	}
	
	/**
	 * Sets the starting state
	 *开始状态
	 */
	public void startState(){
		//sets up a pseudo random start state
		//设置一个假的随机数状态
		Random gen = new Random();
		for(int i=0; i<N; i++){
			//棋盘生成会限制随机数【0-7】
			//这里没有限制
			startState[i] = new Queen(gen.nextInt(N), i);
		}
		start.setState(startState);
		start.computeHeuristic();
	//	System.out.println("start:\n"+start);
	}
	 
	/**
	 * The hill climbing algorithm
	 * 皇后运动算法
	 * @return Node
	 */
	public Node hillClimbing(){
		Node currentNode = start;
		
		while(true){
			// List数组接收node的值的结果集
			ArrayList<Node> successors = currentNode.generateNeighbours(currentNode);
			nodesGenerated+=successors.size();
			
			Node nextNode = null;
			
			//循环：对Node的对象nextNode赋值
			for(int i=0; i<successors.size(); i++){
				if(successors.get(i).compareTo(currentNode) < 0){
					nextNode = successors.get(i);
				}
			}
			
			//非空判断
			if(nextNode==null)
				return currentNode;
			
			currentNode = nextNode;
		}
	}
	
	/**
	 * Returns the Node's state
	 * @return Node
	 */
	public Node getStartNode(){
		return start;
	}
	
	/**
	 * Returns amount of nodes generated
	 *返回 Node 生成的 数 量
	 * @return int
	 */
	public int getNodesGenerated(){
		return nodesGenerated;
	}
}
