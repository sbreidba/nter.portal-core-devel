/*
 * National Training and Education Resource (NTER)
 * Copyright (C) 2012 SRI International
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or (at
 * your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301, USA.
 */


package org.nterlearning.atom.parser.graph;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

/**
 * This class represents a directed acyclic graph.  Note that cycles are only
 * detected when sorting using certain algorithms.
 *
 * @author gjiva
 *
 */
public class DirectedAcyclicGraph {

	private HashMap<String,DirectedAcyclicGraphNode> nodeIdToNodeMap;

	/**
	 *
	 */
	public DirectedAcyclicGraph(){
		this.nodeIdToNodeMap = new HashMap<String, DirectedAcyclicGraphNode>();
	}

	/**
	 *
	 * @param nodes
	 */
	public DirectedAcyclicGraph(Collection<DirectedAcyclicGraphNode> nodes) {
		this();
		this.setNodes(nodes);
	}

	/**
	 *
	 * @return
	 */
	public Collection<DirectedAcyclicGraphNode> getNodes() {
		return nodeIdToNodeMap.values();
	}

	public DirectedAcyclicGraphNode getNode(String id){
		return nodeIdToNodeMap.get(id);
	}

	/**
	 *
	 * @param nodes
	 */
	public void setNodes(Collection<DirectedAcyclicGraphNode> nodes) {
		for (DirectedAcyclicGraphNode node:nodes){
			nodeIdToNodeMap.put(node.getId(), node);
		}
	}

	/**
	 *
	 * @return the list of nodes in this graph which have no incoming edges
	 */
	public List<DirectedAcyclicGraphNode> getZeroOrderNodes(){

		// make a list
		List<DirectedAcyclicGraphNode> zeroOrderNodes = new Vector<DirectedAcyclicGraphNode>();

		// go through all the nodes
		for (DirectedAcyclicGraphNode node:this.getNodes()){
			// if the node has no incoming nodes
			if (node.getIncomingNodes().isEmpty()){
				// add it to our list
				zeroOrderNodes.add(node);
			}
		}

		// return the list
		return zeroOrderNodes;
	}

	/**
	 * Adds an edge from one node to another, using their
	 * IDs.  If either or both of the nodes with those IDs are
	 * not found, they are created.
	 *
	 * @param fromNodeId
	 * @param toNodeId
	 */
	public void addEdge(String fromNodeId, String toNodeId){

		//System.out.println("adding edge from " + fromNodeId + " to " + toNodeId);
		//System.out.println(this.toString());

		// try to get the nodes from the map
		DirectedAcyclicGraphNode fromNode = this.nodeIdToNodeMap.get(fromNodeId);
		DirectedAcyclicGraphNode toNode = this.nodeIdToNodeMap.get(toNodeId);

		// if the nodes don't exist, create them
		if (fromNode == null){
			fromNode = new DirectedAcyclicGraphNode(fromNodeId);
			this.nodeIdToNodeMap.put(fromNodeId, fromNode);
		}

		if (toNode == null){
			toNode = new DirectedAcyclicGraphNode(toNodeId);
			this.nodeIdToNodeMap.put(toNodeId, toNode);
		}

		fromNode.addOutgoingNode(toNode);
		toNode.addIncomingNode(fromNode);

		//System.out.println("added edge from " + fromNodeId + " to " + toNodeId);
		//System.out.println(this.toString());
	}

	/**
	 * Removes an edge going from one node to another, using their
	 * IDs.  If either or both of the nodes with those IDs are
	 * not found, an IllegalArgumentException is thrown.
	 *
	 * @param fromNodeId
	 * @param toNodeId
	 * @throws IllegalArgumentException if either or both of the IDs are invalid
	 */
	public void removeEdge(String fromNodeId, String toNodeId){
		//System.out.println("removing edge from " + fromNodeId + " to " + toNodeId);
		//System.out.println(this.toString());
		DirectedAcyclicGraphNode fromNode = this.nodeIdToNodeMap.get(fromNodeId);
		DirectedAcyclicGraphNode toNode = this.nodeIdToNodeMap.get(toNodeId);

		if (fromNode == null){
			throw new IllegalArgumentException("Cannot remove edge: " +
					"graph does not contain node with ID " + fromNodeId);
		}

		if (toNode == null){
			throw new IllegalArgumentException("Cannot remove edge: " +
					"graph does not contain node with ID " + toNodeId);
		}

		fromNode.removeOutgoingNode(toNode);
		toNode.removeIncomingNode(fromNode);
		//System.out.println("removed edge from " + fromNodeId + " to " + toNodeId);
		//System.out.println(this.toString());
	}

	/**
	 * Sorts this graph using the topological sort algorithm described by A. B. Kahn in 1962.
	 * This algorithm detects cycles.
	 *
	 * @return a sorted list of the nodes.  The class instance nodes however, remain unsorted.
	 * @throws org.nterlearning.atom.parser.graph.CyclicGraphException if this graph has a cycle
	 */
	public List<DirectedAcyclicGraphNode> sortTopologicalKahn()
		throws CyclicGraphException{

		//System.out.println("Sorting graph");

		// make a list
		List<DirectedAcyclicGraphNode> sortedNodes = new Vector<DirectedAcyclicGraphNode>();

		// find all the zero order (no incoming edges) nodes
		List<DirectedAcyclicGraphNode> zeroOrderNodes = this.getZeroOrderNodes();

		// keep going until we run out of zero order nodes
		while (!zeroOrderNodes.isEmpty()){

			// get the next node, n
			DirectedAcyclicGraphNode n = zeroOrderNodes.remove(0);
			// add it to the list of sorted nodes
			//System.out.println("adding " + n.getId() + " to sorted nodes");
			sortedNodes.add(n);

			// go through this node's outgoing nodes
			List<String> nOutgoingNodeIds = new Vector<String>();
			for(DirectedAcyclicGraphNode m:n.getOutgoingNodes()){
				// store the ids in another list to avoid ConcurrentModificationException
				// arising from this outgoing node list being modified while being iterated
				nOutgoingNodeIds.add(m.getId());
			}

			String nId = n.getId();
			DirectedAcyclicGraphNode m;
			for(String mId:nOutgoingNodeIds){
				// remove the edge going from n to m
				// TODO: don't actually mangle the graph when sorting it; use some
				// temporary container for this operation
				this.removeEdge(nId, mId);

				m = getNode(mId);
				// if m has no other incoming edges
				if (m.getIncomingNodes().isEmpty()){
					// add m to the list of zero order nodes for later processing
					//System.out.println("adding " + mId + " to list of zero order nodes");
					zeroOrderNodes.add(m);
				}
			}
		}

		// make sure we have no edges left in the graph
		for (DirectedAcyclicGraphNode node:this.getNodes()){
			if (!node.getIncomingNodes().isEmpty() ||
					!node.getOutgoingNodes().isEmpty()){
				for (DirectedAcyclicGraphNode incomingNode:node.getIncomingNodes()){
					throw new CyclicGraphException("Directed Acyclic Graph has at least one cycle: " +
							"the edge from node [" + incomingNode.getId() + "] to node [" + node.getId() +
							"] should not exist");
				}
				for (DirectedAcyclicGraphNode outgoingNode:node.getOutgoingNodes()){
					throw new CyclicGraphException("Directed Acyclic Graph has at least one cycle: " +
							"the edge from node [" + node.getId() + "] to node [" +  outgoingNode.getId() +
							"] should not exist");
				}

			}
		}

		// return the list
		return sortedNodes;
	}

	public String toString(){

		Collection<DirectedAcyclicGraphNode> values = nodeIdToNodeMap.values();
		StringBuffer sb = new StringBuffer();
		sb.append("DAG: nodes\n");
		for (DirectedAcyclicGraphNode node:values){
			sb.append(node.toString() + "\n");
		}
		return sb.toString();
	}



}