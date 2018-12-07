package com.john;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageLoader {

    private BufferedImage spriteSheet = null;
    public BufferedImage player;
    public BufferedImage bck;
    public Image playerScaled;
    public BufferedImage playerS2;
    public BufferedImage heart;
    public BufferedImage blackHeart;
    public BufferedImage box;

    public void init(){
        Handler h = new Handler();
        BufferedImageLoader loader = new BufferedImageLoader();
        try{
            spriteSheet = loader.loadImage("/sprite_sheet.png");
        }catch (IOException e){
            e.printStackTrace();
        }
        SpriteSheet ss =  new SpriteSheet(spriteSheet);
        player = ss.grabImage(2,1,32,32);

        playerScaled = player.getScaledInstance(h.spiderW,h.spiderH,BufferedImage.SCALE_DEFAULT);


        playerS2 = toBufferedImage(playerScaled);

        bck = ss.grabImage(1,1,32,32);
        heart = ss.grabImage(3,1,32,32);
        blackHeart = ss.grabImage(4,1,32,32);
        box = ss.grabImage(5, 1,32,32);


    }

    public static BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }
}
