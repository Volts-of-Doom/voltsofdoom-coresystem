package vision.voltsofdoom.silverspark.render;

import vision.voltsofdoom.silverspark.api.IRenderableText;
import vision.voltsofdoom.silverspark.text.FontState;


public class TextRenderer extends AbstractRenderer {


  /** Initializes the renderer. */
  public void init() {

    super.init();

  }


  /**
   * Calculates total width of a text.
   *
   * @param text The text
   *
   * @return Total width of the text
   */
  public int getTextWidth(FontState fs, CharSequence text) {
    return fs.getWidth(text);
  }

  /**
   * Calculates total height of a text.
   *
   * @param text The text
   *
   * @return Total width of the text
   */
  public int getTextHeight(FontState fs, CharSequence text) {
    return fs.getHeight(text);
  }

  /**
   * Calculates total width of a debug text.
   *
   * @param text The text
   *
   * @return Total width of the text
   */
  public int getDebugTextWidth(FontState fs, CharSequence text) {
    return fs.getWidth(text);
  }

  /**
   * Calculates total height of a debug text.
   *
   * @param text The text
   *
   * @return Total width of the text
   */
  public int getDebugTextHeight(FontState fs, CharSequence text) {
    return fs.getHeight(text);
  }

  /**
   * Draw debug text at the specified position.
   *
   * @param text Text to draw
   * @param x X coordinate of the text position
   * @param y Y coordinate of the text position
   */
  public void drawDebugText(FontState fs, CharSequence text, float x, float y) {
    fs.drawText(this, text, x, y);
  }

  /**
   * Draw text at the specified position and color.
   *
   * @param dt DisplayText to draw
   */
  public void drawText(IRenderableText dt) { /// No, no, no! FontState shouldn't render itself!
    dt.getDisplayFont().drawText(this, dt.getText(), dt.getPosn().x, dt.getPosn().y, dt.getColor());
  }

}
