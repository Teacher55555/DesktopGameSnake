import java.awt.*;
import java.io.File;

/**
 * Created by Igor Gridin on 05.05.17.
 * <p>
 * Class, which defines font "Curlz.ttf" as a default font in the game
 **/

class MyFont {
    private Font font;

    Font getMyFont (float size) {

        try {
           File font_file = new File(getClass().getResource( "/Res/Font/Curlz.ttf").getFile());
           font = Font.createFont(Font.TRUETYPE_FONT, font_file);
        } catch (Exception ex) {ex.printStackTrace();}
        return font.deriveFont(size);
    }
}
