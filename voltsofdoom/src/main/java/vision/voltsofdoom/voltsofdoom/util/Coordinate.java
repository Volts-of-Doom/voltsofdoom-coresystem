package vision.voltsofdoom.voltsofdoom.util;

/**
 * A coordinate on a grid.
 * 
 * @author GenElectrovise
 *
 */
public class Coordinate {
  private double x;
  private double y;

  public Coordinate(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();

    builder.append("Coordinate [");
    builder.append("{x=" + x + ",y=" + y + "}");
    builder.append("]");

    return builder.toString();
  }

}
