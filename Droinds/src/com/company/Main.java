package com.company;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import static java.lang.Math.*;
import static java.lang.Math.cos;

public class Main {
    public static Font ButtonFont = new Font("x", Font.PLAIN,18);
    public static Font DesFont = new Font("d", Font.PLAIN,12);
    /**Draws an image at location drawLocationX, drawLocationY*/
    public static void drawImage(Graphics2D g2d, int drawLocationX, int drawLocationY, float RotationRequired, BufferedImage image){
        double locationX = image.getWidth() / 2F;
        double locationY = image.getHeight() / 2F;
        AffineTransform tx = AffineTransform.getRotateInstance(RotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        g2d.drawImage(op.filter(image, null), (int)(drawLocationX-locationX), (int)(drawLocationY-locationY), null);
    }
    /**returns a int[] in the format of [acceleration x, accleration y, new heading]*/
    public static float[] getHome(float tspd,float spd, entity tar, projectile pro){
        // delta acceleration x
        float dax;
        // delta acceleration y
        float day;
        //desired heading
        float hw = (float) atan2((tar.y-pro.y), (tar.x-pro.x));
        // current heading
        float h = (float) atan2(pro.dy, pro.dx);
        if (abs(h-hw) >= 0.001){
            if (abs(h-hw) <= tspd){
                h = hw;
            }
            else if (h-hw < 0){
                h = h + tspd;
            }
            else{
                h = h - tspd;
            }
        }
        day = (float) (spd*sin(h));
        dax = (float) (spd*cos(h));
        float[] i = new float[]{dax, day, h};
        return(i);
    }
    /**Gets the game rolling, called on start.*/
    public static void main(String[] args) {
	    game g = new game();
    }
}
