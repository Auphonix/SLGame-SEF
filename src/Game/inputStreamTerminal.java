package Game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JTextField;


public class inputStreamTerminal extends InputStream {
    byte[] contents;
    int pointer = 0;

    public inputStreamTerminal(final JTextField text) {

        text.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyChar() == '\n') {
                    contents = text.getText().getBytes();
                    pointer = 0;
                    text.setText("");
                }
                super.keyReleased(e);
            }
        });
    }

    @Override
    public int read() throws IOException {
        if (pointer >= contents.length) return -1;
        return this.contents[pointer++];
    }

}
