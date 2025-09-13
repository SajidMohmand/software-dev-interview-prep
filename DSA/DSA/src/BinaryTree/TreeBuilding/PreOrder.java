package BinaryTree.TreeBuilding;

import java.util.LinkedList;
import java.util.Queue;

public class PreOrder {


    static class Node{
        int data;
        Node left;
        Node right;
        Node(int data){
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    // use for finding diameter
    static class TreeInfo{
        int ht;
        int diam;
        TreeInfo(int ht,int diam){
            this.ht = ht;
            this.diam = diam;
        }
    }

    static class BinaryTreeTraversal{

        static int idx = -1;
        public Node preOrderTreeBuild(int[] nodes){
            idx++;
            if (nodes[idx] == -1){
                return  null;
            }

            Node newNode  = new Node(nodes[idx]);
            newNode.left = preOrderTreeBuild(nodes);
            newNode.right = preOrderTreeBuild(nodes);

            return newNode;
        }


        // Traversals
        public void preOrderTraversal(Node root){

            if (root == null){
                return;
            }
            System.out.print(root.data+" ");
            preOrderTraversal(root.left);
            preOrderTraversal(root.right);
        }

        public void inOrderTraversal(Node root){

            if (root == null){
                return;
            }

            inOrderTraversal(root.left);
            System.out.println(root.data);
            inOrderTraversal(root.right);
        }
        public void postOrderTraversal(Node root){
            if (root == null){
                return;
            }

            postOrderTraversal(root.left);
            postOrderTraversal(root.right);
            System.out.print(root.data+" ");
        }

        public void levelOrderTraversal(Node root){

            Queue<Node> queue = new LinkedList<>();


            queue.add(root);
            queue.add(null);

            while (!queue.isEmpty()){

                Node current = queue.poll();
                if (current != null){
                    System.out.print(current.data+" ");
                    if (current.left != null){
                        queue.add(current.left);
                    }
                    if(current.right != null){
                        queue.add(current.right);
                    }
                }else{
                    System.out.println();
                    if (!queue.isEmpty()){
                        queue.add(null);
                    }
                }


            }
        }


        public int countNode(Node root) {

            if (root == null) {
                return 0;
            }
            int count = 1;
            int leftNodes = countNode(root.left);
            int rightNodes = countNode(root.right);
            int total = count + leftNodes + rightNodes;
            return total;
        }

        public int sumOfNodes(Node root){

            if (root == null){
                return 0;
            }

            int left = sumOfNodes(root.left);
            int right = sumOfNodes(root.right);
            int total = root.data + left+right;
            return total;
        }


        // find height of tree
        public int heightOfTree(Node root){

            if (root == null) return 0;

            int leftHeight = heightOfTree(root.left);
            int rightHeight = heightOfTree(root.right);

            int totalSubTreeHeight = Math.max(leftHeight, rightHeight)+ 1;
            return totalSubTreeHeight;

        }

        // diameter of Tree = Approach1 0(n^2)
        public int diamOfTree1(Node root){

            if (root == null) return 0;

            int diam1 = diamOfTree1(root.left);
            int diam2 = diamOfTree1(root.right);
            int diam3 = heightOfTree(root.left) + heightOfTree(root.right) + 1;

            return  Math.max(diam3, Math.max(diam1,diam2));
        }

        // diameter of Tree = Approach2 0(n)
        public TreeInfo diamOfTree2(Node root){

            if (root == null){
                return new TreeInfo(0,0);
            }

            TreeInfo left = diamOfTree2(root.left);
            TreeInfo right = diamOfTree2(root.right);

            int height = Math.max(left.ht, right.ht) + 1;

            int diam1 = left.diam;
            int diam2 = right.diam;
            int diam3 = left.ht + right.ht + 1;


            int myDiam = Math.max(diam3, Math.max(diam1,diam2));
            TreeInfo treeInfo = new TreeInfo(height,myDiam);

            return treeInfo;
        }




        // find subtree of another tree
//        public boolean isIdentical(TreeNode root,TreeNode subRoot){
//            if(root == null && subRoot == null) return true;
//            if(root == null || subRoot == null) return false;
//
//            if(root.val == subRoot.val){
//                return isIdentical(root.left,subRoot.left) && isIdentical(root.right,subRoot.right);
//            }
//
//            return false;
//        }
//        public boolean isSubtree(TreeNode root, TreeNode subRoot) {
//            if(subRoot == null) return true;
//            if(root == null) return false;
//
//            if(root.val == subRoot.val){
//                if(isIdentical(root,subRoot)){
//                    return true;
//                }
//            }
//            return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
//
//        }


    }

    public static void main(String[] args) {

        int[] nodes = {1,2,4,-1,-1,5,-1,-1,3,-1,6,-1,-1};
        BinaryTreeTraversal btt = new BinaryTreeTraversal();

        Node root = btt.preOrderTreeBuild(nodes);

//        btt.preOrderTraversal(root);
//        btt.postOrderTraversal(root);
//        btt.levelOrderTraversal(root);

//        System.out.println(btt.countNode(root));
//        System.out.println(btt.sumOfNodes(root));

//        System.out.println(btt.heightOfTree(root));
//        System.out.println(btt.diamOfTree1(root));

        System.out.println(btt.diamOfTree2(root).diam);
    }
    
}
