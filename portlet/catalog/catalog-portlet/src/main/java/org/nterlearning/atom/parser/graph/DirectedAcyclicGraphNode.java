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

import java.util.List;
import java.util.Vector;

/**
 * This class represents a node in a directed acyclic graph.  Edges are expressed
 * with their nodes ordered chronologically.  That is, if node A happens before
 * node B, the edge goes from node A to node B.
 *
 * @author gjiva
 *
 */
public class DirectedAcyclicGraphNode {

	private String id;
	private List<DirectedAcyclicGraphNode> outgoingNodes;
	private List<DirectedAcyclicGraphNode> incomingNodes;

	/**
	 * Constructor
	 *
	 * @param id - an id for this node that is unique in the graph
	 */
	public DirectedAcyclicGraphNode(String id){
		this.setId(id);
		this.setOutgoingNodes(new Vector<DirectedAcyclicGraphNode>());
		this.setIncomingNodes(new Vector<DirectedAcyclicGraphNode>());
	}

	/**
	 *
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 *
	 * @param id - an id for this node that is unique in the graph
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 *
	 * @return the list of Nodes to which there are edges directed from this node
	 */
	public List<DirectedAcyclicGraphNode> getOutgoingNodes() {
		return outgoingNodes;
	}

	/**
	 *
	 * @param outgoingNodes - the list of Nodes to which there are edges directed from this node
	 */
	public void setOutgoingNodes(List<DirectedAcyclicGraphNode> outgoingNodes) {
		this.outgoingNodes = outgoingNodes;
	}

	/**
	 *
	 * @return the list of Nodes from which there are edges directed toward this node
	 */
	public List<DirectedAcyclicGraphNode> getIncomingNodes() {
		return incomingNodes;
	}

	/**
	 *
	 * @param incomingNodes - the list of Nodes from which there are edges directed toward this node
	 */
	public void setIncomingNodes(
			List<DirectedAcyclicGraphNode> incomingNodes) {
		this.incomingNodes = incomingNodes;
	}

	/**
	 *
	 * @param incomingNode
	 */
	public void addIncomingNode(DirectedAcyclicGraphNode incomingNode){
		this.incomingNodes.add(incomingNode);
	}

	/**
	 *
	 * @param incomingNode
	 */
	public void removeIncomingNode(DirectedAcyclicGraphNode incomingNode){
		this.incomingNodes.remove(incomingNode);
	}

	/**
	 *
	 * @param outgoingNode
	 */
	public void addOutgoingNode(DirectedAcyclicGraphNode outgoingNode){
		this.outgoingNodes.add(outgoingNode);
	}

	/**
	 *
	 * @param outgoingNode
	 */
	public void removeOutgoingNode(DirectedAcyclicGraphNode outgoingNode){
		this.outgoingNodes.remove(outgoingNode);
	}

	public boolean equals(Object obj){

		// if it's null, they're not equal
		if (obj == null){
			return false;
		}
		// else, it's not null
		else {
			// if it's not an instance of this class
			if (!(obj instanceof DirectedAcyclicGraphNode)){
				return false;
			}
			// else it's the right type
			else {
				DirectedAcyclicGraphNode that = (DirectedAcyclicGraphNode) obj;
				// if their ids don't match
				if (!this.getId().equals(that.getId())){
					return false;
				}
				// else their ids matched
				else {
					// if their incoming nodes aren't the same
					if (!equalsNodeList(this.getIncomingNodes(),that.getIncomingNodes())){
						return false;
					}
					// else their incoming nodes are the same
					else {
						// if their outgoing nodes aren't the same
						if (!equalsNodeList(this.getOutgoingNodes(), that.getOutgoingNodes())){
							return false;
						}
						// else their ids, incoming and outgoing nodes all matched
						else {
							return true;
						}
					}
				}
			}
		}

	}

	/**
	 * Indicates where two lists passed in are equivalent.
	 *
	 * Note that this method only checks that the lists are the same size and that
	 * all the IDs in both lists match up.  Ideally, the neighbors of nodes in both lists
	 * would also be checked for equality, but that would recursively traverse the graph,
	 * which is probably too expensive of an operation for an equals, and the ID check should
	 * be good enough in most cases.
	 *
	 * @param one
	 * @param two
	 * @return
	 */
	private static boolean equalsNodeList(List<DirectedAcyclicGraphNode> one,
			List<DirectedAcyclicGraphNode> two){

		// if the size of the lists don't match
		if (!(one.size() == two.size())){
			return false;
		}
		// else the lists are the same size
		else {
			boolean found;
			// go through both lists
			for (DirectedAcyclicGraphNode oneNode:one){
				found = false;
				for (DirectedAcyclicGraphNode twoNode:two){
					// make sure both contain the same nodes.  However,
					// this method is used by the equals method, so we can't
					// call equals since that would end up recursively traversing
					// the graph.

					// check that the IDs are the same
					if (oneNode.getId().equals(twoNode.getId())){
						found = true;
						break;
					}
				}
				// if we didn't find this connection in their object
				if (!found){
					return false;
				}
			}

			// all the nodes match
			return true;
		}
	}

	public int hashCode(){

		// this might be a little much performance-wise, but should come up with a
		// pretty unique number
		int random = 41;
		for (DirectedAcyclicGraphNode node:getIncomingNodes()){
			random += node.getId().length();
		}
		for (DirectedAcyclicGraphNode node:getOutgoingNodes()){
			random += node.getId().length();
		}
		return 41*(random + getId().length() * 3 +
				getIncomingNodes().size() + 5 + getOutgoingNodes().size() + 7);
	}

	public String toString(){

		StringBuffer sb = new StringBuffer();
		sb.append("DAGNode: id [" + id + "], outgoing node IDs [");
		for (DirectedAcyclicGraphNode outgoingNode:getOutgoingNodes()){
			sb.append("{" + outgoingNode.getId() + "} ");
		}
		sb.append("], incoming node IDs [");
		for (DirectedAcyclicGraphNode incomingNode:getIncomingNodes()){
			sb.append("{" + incomingNode.getId() + "} ");
		}
		sb.append("].");

		return sb.toString();
	}


}