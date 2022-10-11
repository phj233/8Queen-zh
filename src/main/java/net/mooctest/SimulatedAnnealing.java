package net.mooctest;
/**
 * @file SimulatedAnnealing.java
 * @author nsquires
 * Implements the Simulated Annealing algorithm
 */
import java.util.*;

public class SimulatedAnnealing {
	private final static int N=8;
	int nodesGenerated;	//节点已生成数量
	private Queen[] startState;	//开始状态
	private Node start;	//起始节点
	
	/**
	 * Constructor
	 */
	public SimulatedAnnealing(Queen[] s){
		nodesGenerated = 0;
		start = new Node();
		startState = new Queen[N];
		
		for(int i=0; i<N; i++){
			startState[i] = new Queen(s[i].getRow(), s[i].getColumn());
		}
		start.setState(startState);
		start.computeHeuristic();
	//	startState();
	}
	
	/**
	 * Initializes a pseudorandom configuration of queens on
	 * a board	初始化棋盘上皇后的伪随机配置
	 */
	public void startState(){
		start = new Node();
		startState = new Queen[N];
		Random gen = new Random();
		
		for(int i=0; i<N; i++){
			startState[i] = new Queen(gen.nextInt(N), i);
		}
		start.setState(startState);
		start.computeHeuristic();
	}	//起始状态 计算启发式
	
	/**
	 * The simulated annealing algorithm
	 * 模拟退火算法 是一种通用概率演算法，用来在一个大的搜索空间内寻找命题的最优解，它是基于Monte-Carlo迭代求解策略的一种随机寻优算法。
   		模拟退火算法来源于固体退火原理。
		模拟退火其实也是一种贪心算法，只不过与Local Search不同的是，模拟退火算法在搜索过程引入了随机因素。
		模拟退火算法以一定的概率来接受一个比当前解要差的解，因此有可能会跳出这个局部的最优解，达到全局的最优解。
	 * @param initialTemp	初始温度
	 * @param step	步骤
	 * @return Node		当前节点	变化值大于0，限定值小于概率 返回下一个节点
	 */
	public Node simulatedAnneal(double initialTemp, double step){
		Node currentNode = start;
		double temperature = initialTemp;
		double val = step;	
		double probability;	//概率
		int delta;		//变化值
		double determine;	//限定值
		
		Node nextNode = new Node();
		
		while(currentNode.getHeuristic()!=0 && temperature > 0){
			//select a random neighbour from currentNode	从当前节点中随机选择一个相邻节点
			nextNode = currentNode.getRandomNeighbour(currentNode);
			nodesGenerated++;
			
			if(nextNode.getHeuristic()==0)
				return nextNode;
			
			delta = currentNode.getHeuristic() - nextNode.getHeuristic();
			
			if(delta > 0){ //currentNode has a higher heuristic	当前节点具有更高的启发式
				currentNode = nextNode;
			}else{ 
				probability = Math.exp(delta/temperature);
				//Do we want to choose nextNode or stick with currentNode?	我们是选择下一个节点还是使用当前节点?
				determine = Math.random();
				
				if(determine <= probability){ //choose nextNode	选择下一节点
					currentNode = nextNode;
				}
			}
			temperature = temperature - val;
		}
		
		return currentNode;
	}
	
	/**
	 * nodesGenerated getter	得到已生成节点数
	 * @return
	 */
	public int getNodesGenerated(){
		return nodesGenerated;
	}
	
	/**
	 * start getter	得到开始的节点
	 * @return
	 */
	public Node getStartNode(){
		return start;
	}
}
