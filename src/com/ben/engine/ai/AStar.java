package com.ben.engine.ai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ben.engine.util.Mathf;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class AStar {
	
	private int[][] map;
	private boolean diagonal;
	
	private class Node implements Comparable {
		
		public Node parent;
		
		public int x;
		public int y;
		
		public float gCost;
		public float hCost;
		
		public Node(Node parent, int x, int y, float g, float h) {
			this.parent = parent;
			this.x = x;
			this.y = y;
			this.gCost = g;
			this.hCost = h;
		}
		
		@Override
		public int compareTo(Object o) {
			Node that = (Node)o;
			return (int)((this.gCost + this.hCost) - (that.gCost + that.hCost));
		}
		
	}
	
	public AStar(int[][] map, boolean diagonal) {
		this.map = map;
		this.diagonal = diagonal;
	}
	
	public List<Node> findPath(int xStart, int yStart, int xEnd, int yEnd) {		
		Node now = new Node(null, xStart, yStart, 0, 0);
		
		List<Node> open = new ArrayList<>();
		List<Node> closed = new ArrayList<>();
		List<Node> path = new ArrayList<>();
		
		addNeighboursToOpenList(open, closed, now, xEnd, yEnd);
		
		while (now.x != xEnd || now.y != yEnd) {
			if (open.isEmpty())
				return null; // No path found
			
			now = open.get(0);
			open.remove(0);
			closed.add(now);
			
			addNeighboursToOpenList(open, closed, now, xEnd, yEnd);
		}
		
		path.add(0, now);
		
		while (now.x != xStart || now.y != yStart) {
			now = now.parent;
			path.add(0, now);
		}
		
		return path;
	}
	
	private void addNeighboursToOpenList(List<Node> open, List<Node> closed, Node now, int xEnd, int yEnd) {
		Node node;
		
		for (int x = -1; x <= 1; x++) {
			for (int y = -1; y <= 1; y++) {
				if (!diagonal && x != 0 && y != 0)
					continue;
				
				node = new Node(now, now.x + x, now.y + y, now.gCost, distance(x, y, xEnd, yEnd, now));
				
				if ((x != 0 || y != 0) && 
				    now.x + x >= 0 && now.x + x < map[0].length && 
				    now.y + y >= 0 && now.y + y < map.length &&
				    map[now.y + y][now.x + x] != -1 &&
				    !findNeighbourInList(open, node) && !findNeighbourInList(closed, node)) {
					
					node.gCost = node.parent.gCost + 1f;
					node.gCost += map[now.y + y][now.x + x];
					open.add(node);
				}
			}
		}
		
		Collections.sort(open);
	}
	
	private float distance(int dx, int dy, int xEnd, int yEnd, Node now) {
		if (diagonal)
			return Mathf.hypot(now.x + dx - xEnd, now.y + dy - yEnd);
		else
			return Mathf.abs(now.x + dx - xEnd) + Math.abs(now.y + dy - yEnd);
	}
	
	private boolean findNeighbourInList(List<Node> list, Node node) {
		return list.stream().anyMatch((n) -> (n.x == node.x && n.y == node.y));
	}

}
