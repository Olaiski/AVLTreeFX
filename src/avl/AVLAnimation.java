package avl;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AVLAnimation extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        AVLTree<Integer> tree = new AVLTree<>();
        BorderPane pane = new BorderPane();
        AVLView view = new AVLView(tree);
        pane.setCenter(view);

        TextField tfKey = new TextField();
        tfKey.setPrefColumnCount(3);
        tfKey.setAlignment(Pos.BASELINE_RIGHT);
        Button btInsert = new Button("Insert");
        Button btDelete = new Button("Delete");
        Button btRandom = new Button("Random_10");
        Button btSearch = new Button("Search");
        HBox hBox = new HBox(5);
        hBox.getChildren().addAll(new Label("Enter a key: "), tfKey, btSearch, btInsert, btDelete,btRandom);
        hBox.setAlignment(Pos.CENTER);
        pane.setBottom(hBox);



        btInsert.setOnAction(e -> {
            int key = Integer.parseInt(tfKey.getText());
            if (tree.search(key)) { // Key already in the tree
                view.displayTree();
                view.setStatus(key + " is already in the tree");
            }
            else {
                tree.insert(key);
                view.displayTree();
                view.setStatus(key + " is inserted in the tree");
            }
        });

        btDelete.setOnAction(e -> {
            int key = Integer.parseInt(tfKey.getText());
            if (!tree.search(key)) { // key is not in the tree
                view.displayTree();
                view.setStatus(key + " is not in the tree");
            }
            else {
                tree.delete(key);
                view.displayTree();
                view.setStatus(key + " is deleted from the tree");
            }
        });

        btRandom.setOnAction(e -> {
            view.displayRandom();
            view.setStatus("10 random integers added");
        });

        btSearch.setOnAction(e -> {
            int key = Integer.parseInt(tfKey.getText());
            if (!tree.search(key)) {
                view.displayTree();
                view.setStatus(key + " is not in the tree");
            }
            else {
                view.displayTree();
                view.setStatus(key + " is in the tree");
            }
        });


        Scene scene = new Scene(pane, 600, 450);
        primaryStage.setTitle("AVLAnimation");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
