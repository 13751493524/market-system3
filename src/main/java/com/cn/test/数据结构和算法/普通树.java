package com.cn.test.数据结构和算法;

public class 普通树 {
	public TreeNode root;
	
	public void insert(int data){
		TreeNode node = new TreeNode(data);
		if(root == null){
			root = node;
		}else{
			TreeNode current = root;
			while(current != null){
				TreeNode parent = current;
				if(data < current.data){
					current = current.leftChild;
					if(current == null){
						parent.leftChild = node;
					}
				}else{
					current = current.rightChild;
					if(current == null){
						parent.rightChild = node;
					}
				}
			}
		}
	}
	
	public TreeNode delete(int data){
		return null;
	}
	
	public TreeNode find(int data){
		TreeNode current = root;
		while(current != null){
			if(current.data != data){
				if(data < current.data){
					current = current.leftChild;
				}else{
					current = current.rightChild;
				}
				continue;
			}
			return current;
		}
		return null;
	}
	
	public void display(){
		inOrder(root);
	}
	
	private void inOrder(TreeNode localRoot){
		if(localRoot != null){
			inOrder(localRoot.leftChild);
			System.out.print(localRoot.data+" ");
			inOrder(localRoot.rightChild);
		}
	}
	
	public static void main(String[] args) {
		普通树 tree = new 普通树();
		TreeNode root = new TreeNode(50);
		TreeNode node1 = new TreeNode(30);
		TreeNode node2 = new TreeNode(60);
		root.leftChild = node1;
		root.rightChild = node2;
		tree.root = root;
		tree.insert(40);
		tree.insert(80);
		tree.insert(90);
		TreeNode node3 = tree.find(50);
		System.out.println(node3.data);
		tree.display();
	}
}
