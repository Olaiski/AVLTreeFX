package avl;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.util.Arrays;

public class AVLView extends Pane {

    private AVLTree<Integer> tree = new AVLTree<>();
    private final double radius = 15;
    private final double vGap = 50;

    private Circle circle;

    public AVLView(AVLTree<Integer> tree) {
        this.tree = tree;
        setStatus("Tree is empty");
    }

    public void setStatus(String s) {
        getChildren().add(new Text(20, 20, s));
    }

    public void displayTree() {
        this.getChildren().clear();
        if (tree.getRoot() != null)
            displayTree(tree.getRoot(), getWidth() / 2, vGap, getWidth() / 4);
    }

    private void displayTree(AVLTree.AVLTreeNode<Integer> root, double x, double y, double hGap) {
        if (root.left != null) {
            // Draw a line to the left node
            getChildren().add(new Line(x - hGap, y + vGap, x, y));
            // Draw the left subtree recursively
            displayTree(root.left, x - hGap, y + vGap, hGap / 2);
        }

        if (root.right != null) {
            // Draw a line to the right node
            getChildren().add(new Line(x + hGap, y + vGap, x, y));
            // Draw the right subtree recursively
            displayTree(root.right, x + hGap, y + vGap, hGap / 2);
        }

        // Display a node
        circle = new Circle(x, y, radius);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        getChildren().addAll(circle, new Text(x - 4, y + 4, root.element + ""));
    }

    public void displayRandom() {
        this.getChildren().clear();
        tree.clear();

        tree.addAll(Arrays.asList(tree.randomIntegers()));

        if (tree.getRoot() != null)
            displayTree(tree.getRoot(), getWidth() / 2, vGap, getWidth() / 4);

    }

}
