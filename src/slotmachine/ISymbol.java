package slotmachine;

/**
 * Created by Tom on 11/12/2016.
 */


import javax.swing.*;

public interface ISymbol {

    //interface implemented by Symbol class
    void setValue(int value);
    int getValue();
    void setImage(ImageIcon image);
    ImageIcon getImage();
    boolean isSameSymbol (Symbol s);
}
