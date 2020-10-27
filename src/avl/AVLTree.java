package avl;

import java.util.ArrayList;
import java.util.Iterator;

public class AVLTree<E extends Comparable<E>> implements Tree<E> {

    private final int MAX_RANDOM_INTEGERS = 10;
    private final int RANDOM_RANGE = 150;

    protected AVLTreeNode<E> root;
    protected int size;

    public AVLTree() {
    }

    public AVLTree(E[] objects) {
        for (E o : objects) add(o);
    }

    private AVLTreeNode<E> createNewNode(E e) {
        return new AVLTreeNode<>(e);
    }

    private void updateHeight(AVLTreeNode<E> node) {
        if (node.left == null && node.right == null) // Node is a leaf
            node.height = 0;
        else if (node.left == null) // Node has no left subtree
            node.height = 1 + ((AVLTreeNode<E>)(node.right)).height;
        else if (node.right == null) // Node has no right subtree
            node.height = 1 + ((AVLTreeNode<E>)(node.left)).height;
        else
            node.height = 1 + Math.max(((AVLTreeNode<E>)(node.right)).height,
                    ((AVLTreeNode<E>)(node.left)).height);
    }

    public E find(int k) {
        return find(k, root);
    }

    private E find(int k, AVLTreeNode<E> root) {
        AVLTreeNode<E> A = root.left;
        AVLTreeNode<E> B = root.right;

        if (A == null && k == 1)
            return root.element;
        else if (A == null && k == 2)
            return B.element;
        else if (k <= A.size)
            return find(k, A);
        else if (k == A.size + 1)
            return root.element;
        else if (k > A.size + 1)
            return find(k - A.size - 1, B);

        return null;
    }

    @Override
    public boolean search(E e) {
        AVLTreeNode<E> current = root;

        while (current != null) {
            if (e.compareTo(current.element) < 0)
                current = current.left;
            else if (e.compareTo(current.element) > 0)
                current = current.right;
            else
                return true;
        }
        return false;
    }



    @Override
    public boolean insert(E e) {
        if (root == null) root = createNewNode(e);
        else {
            AVLTreeNode<E> parent = null;
            AVLTreeNode<E> current = root;
            while (current != null)
                if (e.compareTo(current.element) < 0) {
                    parent = current;
                    current = current.left;
                }
                else if (e.compareTo(current.element) > 0) {
                    parent = current;
                    current = current.right;
                }
                else
                    return false;

            if (e.compareTo(parent.element) < 0)
                parent.left = createNewNode(e);
            else
                parent.right = createNewNode(e);
            // FIXME: 27/10/2020 size++? Oppgave 26.5
            parent.size++;


        }

        balancePath(e);
        size++;
        return true;
    }

    private void balancePath(E e) {
        ArrayList<AVLTreeNode<E>> path = path(e);

        for (int i = path.size() - 1; i >= 0; i--) {
            AVLTreeNode<E> A = (AVLTreeNode<E>)(path.get(i));
            updateHeight(A);
            AVLTreeNode<E> parentOfA = (A == root) ? null : (AVLTreeNode<E>)(path.get(i - 1));

            switch (balanceFactor(A)) {
                case -2:
                    if (balanceFactor((AVLTreeNode<E>)A.left) <= 0) {
                        balanceLL(A, parentOfA);
                    }
                    else {
                        balanceLR(A, parentOfA);
                    }
                    break;
                case +2:
                    if (balanceFactor((AVLTreeNode<E>)A.right) >= 0) {
                        balanceRR(A, parentOfA);
                    }
                    else {
                        balanceRL(A, parentOfA);
                    }
            }
        }
    }

    private int balanceFactor(AVLTreeNode<E> node) {
        if (node.right == null)
            return -node.height;
        else if (node.left == null)
            return +node.height;
        else
            return ((AVLTreeNode<E>)node.right).height - ((AVLTreeNode<E>)node.left).height;
    }


    private void balanceLL(AVLTreeNode<E> A, AVLTreeNode<E> parentOfA) {
        AVLTreeNode<E> B = A.left;

        if (A == root) {
            root = B;
        }
        else {
            if (parentOfA.left == A) {
                parentOfA.left = B;
            }
            else {
                parentOfA.right = B;
            }
        }
        A.left = B.right; // Make T2 the left subtree of A
        B.right = A; // Make A the left child of B
        updateHeight((AVLTreeNode<E>)A);
        updateHeight((AVLTreeNode<E>)B);
    }

    private void balanceLR(AVLTreeNode<E> A, AVLTreeNode<E> parentOfA) {
        AVLTreeNode<E> B = A.left; // A is left-heavy
        AVLTreeNode<E> C = B.right; // B is right heavy

        if (A == root) {
            root = C;
        }
        else {
            if (parentOfA.left == A) {
                parentOfA.left = C;
            }
            else {
                parentOfA.right = C;
            }
        }

        A.left = C.right; // Make T3 the left subtree of A
        B.right = C.left; // Make T2 the right subtree of B
        C.left = B;
        C.right = A;

        updateHeight((AVLTreeNode<E>)A);
        updateHeight((AVLTreeNode<E>)B);
        updateHeight((AVLTreeNode<E>)C);
    }

    private void balanceRR(AVLTreeNode<E> A, AVLTreeNode<E> parentOfA) {
        AVLTreeNode<E> B = A.right; // A is right-heavy and B is right-heavy
        if (A == root) {
            root = B;
        }
        else {
            if (parentOfA.left == A) {
                parentOfA.left = B;
            }
            else {
                parentOfA.right = B;
            }
        }
        A.right = B.left; // Make T2 the right subtree
        B.left = A;
        updateHeight((AVLTreeNode<E>)A);
        updateHeight((AVLTreeNode<E>)B);
    }

    private void balanceRL(AVLTreeNode<E> A, AVLTreeNode<E> parentOfA) {
        AVLTreeNode<E> B = A.right; // A is right-heavy
        AVLTreeNode<E> C = B.left; // B is left-heavy

        if (A == root) {
            root = C;
        }
        else {
            if (parentOfA.left == A) {
                parentOfA.left = C;
            }
            else {
                parentOfA.right = C;
            }
        }
        A.right = C.left; // Make T2 the right subtree of A
        B.left = C.right; // Make T3 the left subtree of B
        C.left = A;
        C.right = B;

        updateHeight((AVLTreeNode<E>)A);
        updateHeight((AVLTreeNode<E>)B);
        updateHeight((AVLTreeNode<E>)C);
    }


    public ArrayList<AVLTreeNode<E>> path(E e) {
        ArrayList<AVLTreeNode<E>> list = new ArrayList<>();
        AVLTreeNode<E> current = root;

        while (root != null) {
            list.add(current);
            if (e.compareTo(current.element) < 0)
                current = current.left;
            else if (e.compareTo(current.element) > 0)
                current = current.right;
            else
                break;
        }
        return list;
    }


    @Override
    public boolean delete(E element) {
        if (root == null)
            return false;
        // Locate the node to be deleted and also locate its parent node
        AVLTreeNode<E> parent = null;
        AVLTreeNode<E> current = root;
        while (current != null) {
            if (element.compareTo(current.element) < 0) {
                parent = current;
                current = current.left;
            }
            else if (element.compareTo(current.element) > 0) {
                parent = current;
                current = current.right;
            }
            else
                break;
        }

        if (current == null)
            return false;


        // Case 1: current has no left children
        if (current.left == null) {
            // Connect the parent with the right child of the current node
            if (parent == null) {
                root = current.right;
            }
            else {
                if (element.compareTo(parent.element) < 0)
                    parent.left = current.right;
                else
                    parent.right = current.right;

                balancePath(parent.element);
            }
        }
        else {
            // Case 2: The current node has a left child
            // Locate the rightmost node in the left subtree of the current node and also its parent
            AVLTreeNode<E> parentOfRightMost = current;
            AVLTreeNode<E> rightMost = current.left;

            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right; // Keep going right
            }
            // Replace the element in current by the element in rightMost
            current.element = rightMost.element;

            // Eliminate rightmost node
            if (parentOfRightMost.right == rightMost)
                parentOfRightMost.right = rightMost.left;
            else
                // Special case: parentOfRightMost is current
                parentOfRightMost.left = rightMost.left;

            // Balance the tree if necessary
            balancePath(parentOfRightMost.element);

        }
        current.size--;
        size--;
        return true;
    }

    @Override
    public void inOrder() {
        inOrder(root);
    }

    protected void inOrder(AVLTreeNode<E> root) {
        if (root == null) return;
        inOrder(root.left);
        System.out.print(root.element + " ");
        inOrder(root.right);
    }

    public Integer[] randomIntegers() {
        Integer[] intArr = new Integer[MAX_RANDOM_INTEGERS];
        for (int i = 0; i < intArr.length; i++) {
            intArr[i] = (int) (Math.random() * RANDOM_RANGE);
        }
        return intArr;
    }


    @Override
    public int getSize() {
        return size;
    }

    public AVLTreeNode<E> getRoot() {
        return root;
    }

    @Override
    public Iterator<E> iterator() {
        return new InorderIterator();
    }

    private class InorderIterator implements Iterator<E> {
        private ArrayList<E> list = new ArrayList<>();
        private int current = 0;

        public InorderIterator() {
            inOrder();
        }

        private void inOrder() {
            inOrder(root);
        }

        private void inOrder(AVLTreeNode<E> root) {
            if (root == null) return;
            inOrder(root.left);
            list.add(root.element);
            inOrder(root.right);
        }

        @Override
        public boolean hasNext() {
            return current < list.size() ? true : false;
        }

        @Override
        public E next() {
            return list.get(current++);
        }

        @Override
        public void remove() {
            if (current == 0) // next() has not been called yet
                throw new IllegalStateException();

            delete(list.get(--current));
            list.clear(); // Clear the list
            inOrder(); // Rebuild the list
        }
    }


    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    public static class AVLTreeNode<E> {
        protected int height = 0;
        protected int size;
        protected E element;
        protected AVLTreeNode<E> left;
        protected AVLTreeNode<E> right;

        public AVLTreeNode(E e) {
            this.element = e;
        }

        public int getSize() {
            return size;
        }
    }
}
