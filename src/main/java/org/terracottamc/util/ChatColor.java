package org.terracottamc.util;

/**
 * Copyright (c) 2021, TerracottaMC
 * All rights reserved.
 *
 * <p>
 * This project is licensed under the BSD 3-Clause License which
 * can be found in the root directory of this source tree
 *
 * @author Kaooot
 * @version 1.0
 */
public enum ChatColor {

    BLACK('0'),
    DARK_BLUE('1'),
    DARK_GREEN('2'),
    DARK_AQUA('3'),
    DARK_RED('4'),
    PURPLE('5'),
    ORANGE('6'),
    GRAY('7'),
    DARK_GRAY('8'),
    BLUE('9'),
    GREEN('a'),
    AQUA('b'),
    RED('c'),
    MAGENTA('d'),
    YELLOW('e'),
    WHITE('f'),
    GOLD('g'),
    OBFUSCATED('k', true),
    BOLD('l', true),
    STRIKETHROUGH('m', true),
    UNDERLINE('n', true),
    ITALIC('o', true),
    RESET('r');

    private static final char paragraphUnicode = '\u00A7';

    private final char colorCode;
    private final boolean formattingCode;
    private final String toString;

    ChatColor(char colorCode) {
        this(colorCode, false);
    }

    ChatColor(char colorCode, boolean formattingCode) {
        this.colorCode = colorCode;
        this.formattingCode = formattingCode;
        this.toString = new String(new char[]{ChatColor.paragraphUnicode, this.colorCode});
    }

    /**
     * Replaces all formatting symbols in the given text through the paragraph unicode
     *
     * @param formattingSymbol which should be replaced to the unicode
     * @param textToTranslate  which is used to translate the symbols
     *
     * @return a fresh translated and colorized {@link java.lang.String}
     */
    public static String translateAlternateColorCodes(final char formattingSymbol, final String textToTranslate) {
        return textToTranslate.replaceAll(Character.toString(formattingSymbol),
                Character.toString(ChatColor.paragraphUnicode));
    }

    /**
     * Clears a text out of all color codes
     *
     * @param textToClear that should be cleared
     *
     * @return a fresh cleaned {@link java.lang.String}
     */
    public static String clearColorCodes(final String textToClear) {
        return textToClear.replaceAll(Character.toString(ChatColor.paragraphUnicode), "");
    }

    @Override
    public String toString() {
        return this.toString;
    }

    /**
     * Retrieves the color code of this {@link org.terracottamc.util.ChatColor}
     *
     * @return a fresh color code
     */
    public char getColorCode() {
        return this.colorCode;
    }

    /**
     * Proofs whether this {@link org.terracottamc.util.ChatColor} is a formatting code
     *
     * @return whether this {@link org.terracottamc.util.ChatColor} can be used to format
     */
    public boolean isFormattingCode() {
        return this.formattingCode;
    }

    /**
     * Proofs whether this {@link org.terracottamc.util.ChatColor} is a color
     * instead of {@link org.terracottamc.util.ChatColor#RESET} or a formatting code
     *
     * @return true, if this {@link org.terracottamc.util.ChatColor} is a color, otherwise false
     */
    public boolean isColor() {
        return !this.formattingCode && this != ChatColor.RESET;
    }
}