package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class eRocket extends projectile{
    public static BufferedImage image;

    static {
        try {
            image = ImageIO.read(new File("/Users/noah/IdeaProjects/Droinds/Images/Emissile.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public player tar = null;
    public float h = 0;
    eRocket(int x1, int y1, int dx1, int dy1, boolean exis) {
        super(10, 20, x1, y1, dx1, dy1, 20, exis);
    }

    @Override
    public void update(Graphics2D g, game game) {
        Main.drawImage(g, (int) x, (int) y, (float) (h+(Math.PI/2)), image);
        float[] i = Main.getHome(0.5F, 1, tar, this);
        dx += i[0];
        dy += i[1];
        h = i[2];
        if (this.vTotal() > 100){
            float h = this.angleH();
            dx = (float) (10*cos(h));
            dy = (float) (10*sin(h));
        }
        super.update(g, game);
    }

    public void fire(Rdrone r, player tar){
        this.x = r.x;
        this.y = r.y;
        this.dx = (float) ((float) 2*cos(r.h));
        this.dy = (float) ((float) 2*sin(r.h));
        this.h = r.h;
        this.x += 10*dx;
        this.y += 10*dy;
        this.exist = true;
        this.tar = tar;
    }
}
