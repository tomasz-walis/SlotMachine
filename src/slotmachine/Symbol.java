
package slotmachine;

/**
 * Created by Tom on 11/12/2016.
 */


import javax.swing.*;

public class Symbol implements ISymbol{

    private int value;
    private ImageIcon image;

    //constructor
    public Symbol(int value, ImageIcon image){
        this.value = value;
        this.image =image;
    }

    //setters
    @Override
    public void setValue(int value){
        this.value = value;
    }

    @Override
    public void setImage(ImageIcon image){
        this.image = image;

    }

    //getters
    @Override
    public int getValue(){
        return value;
    }


    @Override
    public ImageIcon getImage(){
        return image;
    }

    //compare symbol method
    @Override
    public boolean isSameSymbol (Symbol symbol){
        if (value == symbol.value){
            return true;
        }
        return false;
    }


}





