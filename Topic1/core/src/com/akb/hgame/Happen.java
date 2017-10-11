package com.akb.hgame;



        import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Happen {

    double date;
    private String name;
    int randpos;
    int corpos;
    BitmapFont Hfont;


    public Happen(Double date, String name) {
        this.date = date;
        this.name = name;

    }

    public String getName() {
        return name;

    }
    public void setName(String name) {
        this.name = name;
    }

    public double getDate() {
        return date;
    }

    public void setDate(double date) {
        this.date = date;
    }

    public void setFontSize(float scale) {
        Hfont.getData().setScale( scale, -scale);

    }
    public float getFontSize() {
        return Hfont.getScaleY();
    }
    public void setFontColour(int r,int g, int b, float opacity) {
        Hfont.setColor(r, g, b, opacity);

    }
    public void SetFont(BitmapFont f) {
        this.Hfont = f;
    }
    public BitmapFont GetFont() {
        return Hfont;
    }





}



