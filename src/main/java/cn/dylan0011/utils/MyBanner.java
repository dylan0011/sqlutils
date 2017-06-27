package cn.dylan0011.utils;

import org.springframework.boot.Banner;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.boot.ansi.AnsiStyle;
import org.springframework.core.env.Environment;

import java.io.PrintStream;

/**
 * Created by yuhp@terminus.io on 2017/6/27.
 * Desc: ${desc}
 */
public class MyBanner implements Banner {

    private static final String BANNER[] = {"","                                                                                    \n" +
            "                                                                                    \n" +
            "                                                                                    \n" +
            "             ,--,                        ,-.----.    ,--,                           \n" +
            "           ,--.'|                 ,---.  \\    /  \\ ,--.'|         ,---,             \n" +
            " ,--,  ,--,|  |,                 '   ,'\\ |   :    ||  |,      ,-+-. /  |  ,----._,. \n" +
            " |'. \\/ .`|`--'_      ,--.--.   /   /   ||   | .\\ :`--'_     ,--.'|'   | /   /  ' / \n" +
            " '  \\/  / ;,' ,'|    /       \\ .   ; ,. :.   : |: |,' ,'|   |   |  ,\"' ||   :     | \n" +
            "  \\  \\.' / '  | |   .--.  .-. |'   | |: :|   |  \\ :'  | |   |   | /  | ||   | .\\  . \n" +
            "   \\  ;  ; |  | :    \\__\\/: . .'   | .; :|   : .  ||  | :   |   | |  | |.   ; ';  | \n" +
            "  / \\  \\  \\'  : |__  ,\" .--.; ||   :    |:     |`-''  : |__ |   | |  |/ '   .   . | \n" +
            "./__;   ;  \\  | '.'|/  /  ,.  | \\   \\  / :   : :   |  | '.'||   | |--'   `---`-'| | \n" +
            "|   :/\\  \\ ;  :    ;  :   .'   \\ `----'  |   | :   ;  :    ;|   |/       .'__/\\_: | \n" +
            "`---'  `--`|  ,   /|  ,     .-./         `---'.|   |  ,   / '---'        |   :    : \n" +
            "            ---`-'  `--`---'               `---`    ---`-'                \\   \\  /  \n" +
            "                                                                           `--`-'   "};
    private static final String SPRING_BOOT = " :: Powered by XIAOPING :: ";
    private static final int STRAP_LINE_SIZE = 42;

    @Override
    public void printBanner(Environment environment, Class<?> aClass, PrintStream printStream) {
        String[] var4 = BANNER;
        int var5 = var4.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            String line = var4[var6];
            printStream.println(line);
        }

        String version = "(v1.0)";

        String padding;
        for(padding = ""; padding.length() < STRAP_LINE_SIZE - (version.length() + SPRING_BOOT.length()); padding = padding + " ") {
        }

        printStream.println(AnsiOutput.toString(new Object[]{AnsiColor.GREEN, SPRING_BOOT, AnsiColor.DEFAULT, padding, AnsiStyle.FAINT, version}));
        printStream.println();
    }
}
