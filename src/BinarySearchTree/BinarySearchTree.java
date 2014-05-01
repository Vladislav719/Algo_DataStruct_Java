package BinarySearchTree;

/**
 * Created by vladislav on 01.05.14.
 */
public class BinarySearchTree {

    private Node root;

    static class Node{
        int key;
        int value;
        Node left,
             right,
             parent;

        public Node(int key, int value, Node parent){
            this.key = key;
            this.value = value;
            this.parent = parent;
        }
    }

    private Node search(Node node, int key){
        if (node == null || node.key == key){
            return node;
        }
        if (key < node.key){
            return search(node.left, key);
        } else {
            return search(node.right, key);
        }
    }

    public Node search(int key){
        return search(root, key);
    }

    private Node insert(Node node, Node parent, int key, int value){
        if (node == null){
            node = new Node(key,value,parent);
        } else {
            if (key < node.key){
                node.left = insert(node.left, node, key, value);
            } else {
                node.right = insert(node.right, node, key, value);
            }
        }
        return node;
    }

    public void insert(int key, int value){
        root = insert(root, null, key, value);
    }

    void replace(Node a, Node b){
        if (a.parent == null)
            root = b;
        else if (a == a.parent.left)
            a.parent.left = b;
        else
            a.parent.right = b;
        if (b != null)
            b.parent = a.parent;
    }

    public void remove(int key){
        remove(root, key);
    }

    private void remove(Node node, int key){
        if (node == null){
            return;
        }
        if (key < node.key){
            remove(node.left, key);
        } else if (key > node.key){
            remove(node.right, key);
        } else  if (node.left != null && node.right != null){
            Node t = node.right;
            while (t.left != null){
                t = t.left;
            }
            node.key = t.key;
            node.value = t.value;
            replace(t,t.right);
        } else if (node.left != null){
            replace(node, node.left);
        } else if (node.right != null){
            replace(node, node.right);
        } else {
            replace(node, null);
        }
    }

    private void print(Node node){
        if (node != null){
            print(node.left);
            System.out.println(node.key + " : " + node.value + " ");
            print(node.right);
        }
    }

    public void print(){
        print(root);
        System.out.println();
    }
}
