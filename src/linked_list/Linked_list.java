



package linked_list;

import static java.lang.Integer.parseInt;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class Linked_list extends Application {
    
    public static void main(String [] args)
    {
        launch(args);
    } 

@Override
    public void start(Stage primarystage){
        
        LinkedList1 linkedList=new LinkedList1();
        
//                            SET UP BUTTONS
        Button empty_btn=new Button("Is Empty");
        Button size_btn=new Button("Size");
        Button add_btn=new Button("Add");
        Button remove_btn=new Button("Remove");
        Button sort_btn=new Button("Sort");
        Button reverse_btn=new Button("Reverse");
       
//                            SET UP LABELS
        Label list_output_label=new Label("--Empty Linked List--");
        Label error_label=new Label();
        error_label.setVisible(false);
        error_label.setId("error_label");
        
//                            SET UP TEXT FIELDS
        TextField add_index_text=new TextField();
        add_index_text.setPromptText("--Index--");
        add_index_text.setPrefColumnCount(5);
        add_index_text.setFocusTraversable(false);
        
        TextField add_string_text=new TextField();
        add_string_text.setPromptText("--String--");
        add_string_text.setPrefColumnCount(5);
        add_string_text.setFocusTraversable(false);
        
        TextField remove_index=new TextField();
        remove_index.setPromptText("--Index--");
        remove_index.setPrefColumnCount(5);
        remove_index.setFocusTraversable(false);
        
        TextField remove_str=new TextField();
        remove_str.setPromptText("--String--");
        remove_str.setPrefColumnCount(5);
        remove_str.setFocusTraversable(false);
        
        TextField size_text=new TextField();
        size_text.setPromptText("---");
        size_text.setPrefColumnCount(2);
        size_text.setEditable(false);
        
//                            EMPTY BUTTON LAMBDA EVENT
        empty_btn.setOnAction(event ->{
            String result_txt=String.valueOf(linkedList.isEmpty());
            error_label.setText(result_txt);
            error_label.setVisible(true);
        });
        
//                            SIZE BUTTON LAMBDA EVENT
        size_btn.setOnAction(event ->{
            String size=String.valueOf(linkedList.size());
            size_text.setText(size);
        });

//                            ADD BUTTON LAMBDA EVENT
        add_btn.setOnAction(event ->{
            
            String result_add_str=add_string_text.getText();
            
            if (add_string_text.getText().isEmpty()){                               //String text is empty, enter a string value
                error_label.setVisible(true);
                error_label.setText("Type in a String");
            }
            else if (add_index_text.getText().isEmpty()){                           //index is empty, add string value only
                error_label.setVisible(false);
                linkedList.add(result_add_str);
                list_output_label.setText(linkedList.toString());
                add_string_text.clear();
            }
            else{                                                                   //add string value at index value
                boolean binary, add_binary;
                error_label.setVisible(false);
                add_binary=TestInt(add_index_text.getText());
                
                if(add_binary==true){
                    int result_add_int=-1;
                    try{
                        result_add_int=parseInt(add_index_text.getText());
                    }
                    catch (NumberFormatException e){
                    }

                    binary=linkedList.add(result_add_int, result_add_str);
                    if (binary==true){
                        list_output_label.setText(linkedList.toString());
                    }
                    else{
                        error_label.setVisible(true);
                        error_label.setText(add_index_text.getText()+" is out of range");
                    }
                    add_index_text.clear();
                    add_string_text.clear();
                }
                else{
                     error_label.setText(add_index_text.getText()+" is not an Int");
                     error_label.setVisible(true);
                }
            }
            
        });

//                            REMOVE BUTTON LAMBDA EVENT
        remove_btn.setOnAction(event ->{
            error_label.setVisible(true);
            boolean remove_binary=TestInt(remove_index.getText());
            if(remove_binary==true || remove_index.getText().isEmpty()){
                if (remove_str.getText().isEmpty() && remove_index.getText().isEmpty()){            //if both index and string are empty, error
                    error_label.setText("Enter in a index or String value");
                }
                else if (remove_index.getText().isEmpty()){                                         //if index is empty, add string
                    boolean result=linkedList.remove(remove_str.getText());
                    if (result==true){
                        error_label.setText(remove_str.getText()+" is removed");
                        if (linkedList.isEmpty()==true){
                            list_output_label.setText("--Empty Linked List--");
                        }
                        else {
                            list_output_label.setText(linkedList.toString()); 
                        }
                    }
                    else{
                        error_label.setText(remove_str.getText()+" is not in linked list");
                    }
                }
                else if (remove_str.getText().isEmpty()){                                   //if string is empty, remove element at index
                    int remove_index_int=parseInt(remove_index.getText());
                    String removed=linkedList.remove(remove_index_int);

                    if (removed==null){
                        error_label.setText(remove_index.getText()+" is out of range");
                    }
                    else{
                        if (linkedList.isEmpty()==true){
                            list_output_label.setText("--Empty Linked List--");
                        }
                        else{
                            error_label.setText(removed+" is removed");
                            list_output_label.setText(linkedList.toString()); 
                        }
                    }
                }
                else{
                    error_label.setText("Enter index or String, not both");
                }
            }
            else{
                error_label.setText(remove_index.getText()+" is not an Int");
            }
            remove_index.clear();
            remove_str.clear();
            
        });

//                            SORT BUTTON LAMBDA EVENT
        sort_btn.setOnAction(event ->{
            if (linkedList.isEmpty()==true){
                error_label.setText("Linked List is empty");
                error_label.setVisible(true);
            }
            else{
                linkedList.sort();
                list_output_label.setText(linkedList.toString());
                error_label.setVisible(false);
            }
        });

//                            REVERSE BUTTON LAMBDA EVENT
        reverse_btn.setOnAction(event ->{
            if (linkedList.isEmpty()==true){
                error_label.setText("Linked List is empty");
                error_label.setVisible(true);
            }
            else{
                linkedList.reverse();
                list_output_label.setText(linkedList.toString());
                error_label.setVisible(false);
            }
        });        
        
        HBox hbox_empty=new HBox(empty_btn);
        hbox_empty.setAlignment(Pos.CENTER);
        hbox_empty.setSpacing(20);
        
        HBox hbox_add=new HBox( add_index_text, add_string_text, add_btn);
        hbox_add.setAlignment(Pos.CENTER);
        hbox_add.setSpacing(20);
        
        HBox hbox_remove=new HBox(remove_index, remove_str, remove_btn);
        hbox_remove.setAlignment(Pos.CENTER);
        hbox_remove.setSpacing(20);
        
        HBox hbox_size=new HBox(size_text, size_btn);
        hbox_size.setAlignment(Pos.CENTER);
        hbox_size.setSpacing(20);
        
        VBox vbox=new VBox(list_output_label, error_label, hbox_add, hbox_remove, hbox_size, sort_btn, reverse_btn, hbox_empty);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);
        
        Scene scene=new Scene(vbox, 600, 600);
        scene.getStylesheets().add("styles.css");
        primarystage.setScene(scene);
        primarystage.setTitle("Linked List Challenge");
        primarystage.show();           
    }
    
    public boolean TestInt(String test){
        try{
            parseInt(test);
            return true;
        }
        catch(NumberFormatException e){
            return false;
        }
    }
           
}
