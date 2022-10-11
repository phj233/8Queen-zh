package net.mooctest;
/**
 * @file RandomRestart.java
 * @author Natasha Squires <nsquires@upei.ca>
 * Implements the random restart hill climbing algorithm	实现随机重启爬坡算法
 */
public class RandomRestart {
	private HillClimbing hillClimber;	
	/*爬山算法 爬山算法是一种局部择优的方法，采用启发式方法，
	 * 是对深度优先搜索的一种改进，它利用反馈信息帮助生成解的决策。 属于人工智能算法的一种。
	 * 爬山搜索的意思就是不断地向更高点前进，那么当然我们爬山都是一步步爬过去的，所以它是一点一点爬过去的
	 * 它返回的是一个状态，这个状态是一个局部最值点，它只能保证返回一个局部最值点，但不能保证返回一个全局最值点。
	 * */
	private int nodesGenerated;	//节点生成
	private Node start;	//起始节点
	
	/**
	 * Constructor
	 */
	public RandomRestart(Queen[] startBoard){
		hillClimber = new HillClimbing(startBoard);
		nodesGenerated = 0;	//节点生成数
	}
	
	/**
	 * The random restart hill climbing algorithm	随机重启爬坡算法
	 * @return		返回当前节点
	 */
	public Node randomRestart(){
		Node currentNode = hillClimber.getStartNode();
		setStartNode(currentNode);
		int heuristic = currentNode.getHeuristic();
				
		while(heuristic!=0){
			Node nextNode = hillClimber.hillClimbing();
			nodesGenerated+=hillClimber.getNodesGenerated();
			heuristic = nextNode.getHeuristic();
			
			if(heuristic!=0){ //restart
				hillClimber = new HillClimbing();
			}else
				currentNode = nextNode;
		}
		return currentNode;
	}
	
	/**
	 * Sets the initial board	设置初始棋盘
	 * @param n		参数 节点
	 */
	public void setStartNode(Node n){
		start = n;
	}
	
	/**
	 * Start set getter	开始设置获取
	 * @return Node	返回开始节点
	 */
	public Node getStartNode(){
		return start;
	}
	
	/**
	 * Returns the amount of nodes generated during the 
	 * random restart algorithm	返回随机重启算法期间生成的节点数量
	 * @return int	返回已生成节点数
	 */
	public int getNodesGenerated(){
		return nodesGenerated;
	}
}
