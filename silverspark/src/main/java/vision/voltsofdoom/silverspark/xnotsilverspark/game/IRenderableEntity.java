package vision.voltsofdoom.silverspark.xnotsilverspark.game;


import vision.voltsofdoom.silverspark.graphic.Spark;

public interface IRenderableEntity {
  float getX();

  float getY();

  float getImageWidth();

  float getHeight();

  Spark getTexture();

  int getTextureX();

  int getTextureY();

}
