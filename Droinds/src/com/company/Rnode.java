package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Rnode {
    public int relX;
    public int relY;
    public String file;
    public ArrayList<Rnode> reqs;
    public int price;
    public BufferedImage img;
    public String[] des;
    public boolean r = false;
    public boolean equipped = false;
    Rnode(int relX1, int relY1, String file1, ArrayList<Rnode> reqs1, int price1, String[] description){
        relX = relX1;
        relY = relY1;
        file = file1;
        reqs = reqs1;
        price = price1;
        des = description;
        try {
            img = ImageIO.read(new File("/Users/noah/IdeaProjects/Droinds/Images/"+ file +".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g, game game){
        if (reqs != null){
            g.setColor(Color.WHITE);
            for (Rnode node : reqs){
                if(node.r){
                    g.setColor(Color.GREEN);
                }
                g.drawLine(relX,relY,node.relX, node.relY);
            }
        }
        if (r){
            g.setColor(Color.YELLOW);
        }
        else {
            g.setColor(Color.RED);
        }
        g.fillRect(relX - img.getHeight()/2 - 5, relY - img.getHeight()/2 - 5, img.getWidth()+10, img.getHeight()+10);
        Main.drawImage(g,relX,relY,0,img);
    }
    public void update(Graphics2D g, game game){
        if(game.mx <= relX + img.getWidth()/2 + 5 && game.my <= relY + img.getHeight()/2 + 5
                && game.mx >= relX - img.getWidth()/2 - 5 && game.my >= relY - img.getHeight()/2 - 5){
            g.setColor(new Color(255,0,0, 158));
            g.fillRect(relX - img.getWidth()/2, relY - img.getHeight()/2, img.getWidth(), img.getHeight());
            g.setColor(Color.GRAY);
            g.fillRect(relX+img.getWidth()+5, relY, des[0].length()*6, des.length*13 + 13);
            g.setColor(Color.WHITE);
            g.setFont(Main.DesFont);
            if (!Objects.equals(game.p.model, file) && equipped){
                this.equipped = false;
            }
            for (int i = 0; i < des.length; i++) {
                g.drawString(des[i], relX + img.getWidth() + 5, relY + i * 13 + 13);
            }
            if (equipped) {
                g.drawString("Equipped", relX + img.getWidth() + 5, relY + des.length*13 + 13);
            }
            else{
                g.drawString("Not equipped", relX + img.getWidth() + 5, relY + des.length*13 + 13);
            }
            if (game.mp){
                game.mp = false;
                if(r){
                    game.p.model = file;
                    game.p.img = img;
                    equipped = true;
                }
            }
        }
    }
}
