package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.lang.Math.*;
import static java.lang.Math.abs;

public class Rdrone extends enemy{
    /**ticks to fire*/
    private int t = 50;
    /**Target X to move to.*/
    private int tarx;
    /**Target Y to move to*/
    private int tary;
    /**Image of drone*/
    public static BufferedImage image;

    static {
        try {
            image = ImageIO.read(new File("/Users/noah/IdeaProjects/Droinds/Images/Rdrone.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    Rdrone(int x1, int y1, int dx1, int dy1, boolean exis)  {
        super(10, 20, x1, y1, dx1, dy1, exis);
        tarx = x1 + 10;
        tary = y1 + 10;
        float r = (float) atan2(tary, tarx);
        dx = (float) ((float) 0.5*cos(r));
        dy = (float) ((float) 0.5*sin(r));
    }
    /**Called to update: draws itself, fires if it can, and moves.*/
    @Override
    public void update(Graphics2D g, game game) {
        super.update(g, game);
        Main.drawImage(g, (int) x, (int) y, h, image);
        if(t==0){
            t = 250;
            eRocket e = (eRocket) game.getUnused(eRocket.class);
            if (e == null) {
                eRocket dp = new eRocket(0,0,0,0,true);
                dp.fire(this, game.p);
                game.entities.add(dp);
            }
            else{
                e.fire(this, game.p);
            }
        }
        else{
            t -= 1;
        }
        h = (float) Math.atan2(game.p.y - y, game.p.x - x);
        if (abs(tarx - (int) x) <= 1 && abs(tary - (int) y) <= 1){
            tarx = game.ran.nextInt(1000);
            tary = game.ran.nextInt(500);
            float r = (float) Math.atan2(tary - y, tarx - x);
            dx = (float) ((float) 0.5*Math.cos(r));
            dy = (float) ((float) 0.5*Math.sin(r));
        }
    }
}
