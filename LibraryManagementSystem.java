import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
// Created By Vanshit Aggarwal
class Book {
    String name, author, type;

    Book(String name, String author, String type) {
        this.name = name;
        this.author = author;
        this.type = type;
    }
}

class LibraryManagementSystem extends Frame {
    private ArrayList<Book> library = new ArrayList<>();
    private TextArea textArea;

    LibraryManagementSystem() {
        setTitle("Library Management System");
        setSize(600, 400);
        setLocation(300, 100);
        setLayout(new BorderLayout());

        MenuBar menuBar = new MenuBar();
        setMenuBar(menuBar);

        Menu fileMenu = new Menu("File");
        menuBar.add(fileMenu);
        MenuItem exitItem = new MenuItem("Exit");
        fileMenu.add(exitItem);

        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        Panel panel = new Panel();
        panel.setLayout(new FlowLayout());

        Label nameLabel = new Label("Name");
        TextField nameTextField = new TextField(20);

        Label authorLabel = new Label("Author");
        TextField authorTextField = new TextField(20);

        Label typeLabel = new Label("Type");
        TextField typeTextField = new TextField(20);

        Button addButton = new Button("Add Book");
        Button listButton = new Button("List Books");
        Button deleteButton = new Button("Delete Book");

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameTextField.getText();
                String author = authorTextField.getText();
                String type = typeTextField.getText();
                library.add(new Book(name, author, type));
                nameTextField.setText("");
                authorTextField.setText("");
                typeTextField.setText("");
            }
        });

        listButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                for (int i = 0; i < library.size(); i++) {
                    Book book = library.get(i);
                    textArea.append("Book #" + (i + 1) + " ---> Name: " + book.name + "\tAuthor: " + book.author + "\tType: " + book.type + "\n");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Enter the book number to delete:");
                try {
                    int bookNumber = Integer.parseInt(input) - 1;
                    if (bookNumber >= 0 && bookNumber < library.size()) {
                        library.remove(bookNumber);
                        JOptionPane.showMessageDialog(null, "Book deleted successfully!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid book number.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid book number.");
                }
            }
        });

        panel.add(nameLabel);
        panel.add(nameTextField);
        panel.add(authorLabel);
        panel.add(authorTextField);
        panel.add(typeLabel);
        panel.add(typeTextField);
        panel.add(addButton);
        panel.add(listButton);
        panel.add(deleteButton);

        textArea = new TextArea();
        textArea.setEditable(false);

        add(panel, BorderLayout.NORTH);
        add(textArea, BorderLayout.CENTER);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        LibraryManagementSystem libraryManagementSystem = new LibraryManagementSystem();
        libraryManagementSystem.setVisible(true);
    }
}
