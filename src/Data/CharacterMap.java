/**
 * Created by William Davis on 10/02/2016.
 */
package Data;

import java.lang.Character;

public class CharacterMap
{
    /**
     * The character instance variable is used to store the character.
     */
    private char character;
    /**
     * The struct instance variable is used to store the data that makes the character.
     */
    private int[][] struct;

    /**
     * The CharacterMap constructor is used to associate a character with a structure to draw it.
     * @param character - The character.
     * @param structure - The data that is used to draw the character.
     */
    public CharacterMap(char character, int[][] structure)
    {
        this.character = character;
        this.struct = structure;
    }

    /**
     * The equals instance method is used to compare a character to the character set.
     * @param character - The other character.
     * @return - True if the characters are the same. False otherwise.
     */
    public boolean equals(char character)
    {
        return this.character == Character.toLowerCase(character);
    }

    /**
     * The structure instance method is used to get the structure that is used to draw the character.
     * @return - The structure data.
     */
    public int[][] structure()
    {
        return this.struct;
    }

    /**
     * The getAll class variable is used to get all the data to character associations set as a list.
     * @return - The list of associations.
     */
    public static CharacterMap[] getAll()
    {
        return new CharacterMap[]{
            new CharacterMap(' ', null),
            new CharacterMap('0', new int[][]{{1, 0, 3, 1}, {0, 1, 1, 5}, {4, 1, 1, 5}, {1, 6, 3, 1}, {1, 4, 1, 1}, {2, 3, 1, 1}, {3, 2, 1, 1}}),
            new CharacterMap('1', new int[][]{{1, 1, 1, 1}, {2, 0, 1, 6}, {1, 6, 3, 1}}),
            new CharacterMap('2', new int[][]{{0, 1, 1, 1}, {1, 0, 3, 1}, {4, 1, 1, 2}, {3, 3, 1, 1}, {2, 4, 1, 1}, {1, 5, 1, 1}, {0, 6, 5, 1}}),
            new CharacterMap('3', new int[][]{{0, 0, 5, 1}, {3, 1, 1, 1}, {2, 2, 1, 1}, {3, 3, 1, 1}, {4, 4, 1, 2}, {1, 6, 3, 1}, {0, 5, 1, 1}}),
            new CharacterMap('4', new int[][]{{0, 3, 1, 1}, {1, 2, 1, 1}, {2, 1, 1, 1}, {0, 4, 3, 1}, {3, 0, 1, 7}, {4, 4, 1, 1}}),
            new CharacterMap('5', new int[][]{{0, 0, 5, 1}, {0, 1, 1, 1}, {0, 2, 4, 1}, {4, 3, 1, 3}, {1, 6, 3, 1}, {0, 5, 1, 1}}),
            new CharacterMap('6', new int[][]{{2, 0, 2, 1}, {1, 1, 1, 1}, {0, 2, 1, 4}, {1, 6, 3, 1}, {4, 4, 1, 2}, {1, 3, 3, 1}}),
            new CharacterMap('7', new int[][]{{0, 0, 5, 1}, {4, 1, 1, 1}, {3, 2, 1, 1}, {2, 3, 1, 1}, {1, 4, 1, 3}}),
            new CharacterMap('8', new int[][]{{1, 0, 3, 1}, {0, 1, 1, 2}, {4, 1, 1, 2}, {1, 3, 3, 1}, {0, 4, 1, 2}, {4, 4, 1, 2}, {1, 6, 3, 1}}),
            new CharacterMap('9', new int[][]{{1, 3, 3, 1}, {0, 1, 1, 2}, {1, 0, 3, 1}, {4, 1, 1, 4}, {3, 5, 1, 1}, {1, 6, 2, 1}}),
            new CharacterMap(':', new int[][]{{0, 1, 1, 2}, {0, 4, 1, 2}}),
            new CharacterMap('.', new int[][]{{0, 6, 1, 1}}),
            new CharacterMap('-', new int[][]{{1, 3, 3, 1}}),
            new CharacterMap('…', new int[][]{{0, 6, 1, 1}, {2, 6, 1, 1}, {4, 6, 1, 1}}),
            new CharacterMap('*', new int[][]{{0, 1, 1, 1}, {4, 1, 1, 1}, {1, 2, 1, 1}, {3, 2, 1, 1}, {2, 3, 1, 1}, {1, 4, 1, 1}, {3, 4, 1, 1}, {0, 5, 1, 1}, {4, 5, 1, 1}}),
            new CharacterMap('<', new int[][]{{1, 3, 1, 1}, {2, 2, 1, 1}, {2, 4, 1, 1}, {3, 1, 1, 1}, {3, 5, 1, 1}, {4, 0, 1, 1}, {4, 6, 1, 1}}),
            new CharacterMap('^', new int[][]{{0, 3, 1, 4}, {1, 1, 1, 2}, {2, 0, 1, 1}, {3, 1, 1, 2}, {4, 3, 1, 4}}),
            new CharacterMap('a', new int[][]{{0, 1, 1, 6}, {4, 1, 1, 6}, {1, 0, 3, 1}, {1, 3, 3, 1}}),
            new CharacterMap('b', new int[][]{{0, 0, 1, 7}, {1, 0, 3, 1}, {1, 3, 3, 1}, {1, 6, 3, 1}, {4, 1, 1, 2}, {4, 4, 1, 2}}),
            new CharacterMap('c', new int[][]{{0, 1, 1, 5}, {4, 1, 1, 1}, {4, 5, 1, 1},{1, 0, 3, 1}, {1, 6, 3, 1}}),
            new CharacterMap('d', new int[][]{{0, 0, 1, 7}, {4, 1, 1, 5}, {1, 0, 3, 1}, {1, 6, 3, 1}}),
            new CharacterMap('e', new int[][]{{0, 0, 1, 7}, {1, 0, 4, 1}, {1, 3, 3, 1}, {1, 6, 4, 1}}),
            new CharacterMap('f', new int[][]{{0, 0, 1, 7}, {1, 0, 4, 1}, {1, 3, 3, 1}}),
            new CharacterMap('g', new int[][]{{0, 1, 1, 5}, {1, 0, 3, 1}, {1, 6, 3, 1}, {4, 1, 1, 1}, {4, 5, 1, 1}, {3, 4, 2, 1}}),
            new CharacterMap('h', new int[][]{{0, 0, 1, 7}, {4, 0, 1, 7}, {1, 3, 3, 1}}),
            new CharacterMap('i', new int[][]{{2, 1, 1, 5}, {1, 0, 3, 1}, {1, 6, 3, 1}}),
            new CharacterMap('j', new int[][]{{2, 0, 3, 1}, {3, 1, 1, 5}, {0, 5, 1, 1}, {1, 6, 2, 1}}),
            new CharacterMap('k', new int[][]{{0, 0, 1, 7}, {1, 3, 1, 1}, {2, 2, 1, 1}, {2, 4, 1, 1}, {3, 1, 1, 1}, {3, 5, 1, 1}, {4, 0, 1, 1}, {4, 6, 1, 1}}),
            new CharacterMap('l', new int[][]{{0, 0, 1, 7}, {1, 6, 4, 1}}),
            new CharacterMap('m', new int[][]{{0, 0, 1, 7}, {1, 1, 1, 1}, {2, 2, 1, 2}, {3, 1, 1, 1}, {4, 0, 1, 7}}),
            new CharacterMap('n', new int[][]{{0, 0, 1, 7}, {4, 0, 1, 7}, {1, 2, 1, 1}, {2, 3, 1, 1}, {3, 4, 1, 1}}),
            new CharacterMap('o', new int[][]{{0, 1, 1, 5}, {4, 1, 1, 5}, {1, 0, 3, 1}, {1, 6, 3, 1}}),
            new CharacterMap('p', new int[][]{{0, 0, 1, 7}, {4, 1, 1, 2}, {1, 0, 3, 1}, {1, 3, 3, 1}}),
            new CharacterMap('q', new int[][]{{1, 0, 3, 1}, {0, 1, 1, 5}, {4, 1, 1, 4}, {1, 6, 2, 1}, {2, 4, 1, 1}, {3, 5, 1, 1}, {4, 6, 1, 1}}),
            new CharacterMap('r', new int[][]{{0, 0, 1, 7}, {4, 1, 1, 2}, {1, 0, 3, 1}, {1, 3, 3, 1}, {2, 4, 1, 1}, {3, 5, 1, 1}, {4, 6, 1, 1}}),
            new CharacterMap('s', new int[][]{{1, 0, 4, 1}, {0, 1, 1, 2}, {1, 3, 3, 1}, {4, 4, 1, 2}, {0, 6, 4, 1}}),
            new CharacterMap('t', new int[][]{{0, 0, 5, 1}, {2, 1, 1, 6}}),
            new CharacterMap('u', new int[][]{{0, 0, 1, 6}, {4, 0, 1, 6}, {1, 6, 3, 1}}),
            new CharacterMap('v', new int[][]{{0, 0, 1, 4}, {1, 4, 1, 2}, {2, 6, 1, 1}, {3, 4, 1, 2}, {4, 0, 1, 4}}),
            new CharacterMap('w', new int[][]{{0, 0, 1, 6}, {4, 0, 1, 6}, {2, 3, 1, 3}, {1, 6, 1, 1}, {3, 6, 1, 1}}),
            new CharacterMap('x', new int[][]{{0, 0, 1, 2}, {4, 0, 1, 2}, {0, 5, 1, 2}, {4, 5, 1, 2}, {1, 2, 1, 1}, {3, 2, 1, 1}, {2, 3, 1, 1}, {1, 4, 1, 1}, {3, 4, 1, 1}}),
            new CharacterMap('y', new int[][]{{0, 0, 1, 2}, {4, 0, 1, 2}, {1, 2, 1, 1}, {3, 2, 1, 1}, {2, 3, 1, 4}}),
            new CharacterMap('z', new int[][]{{0, 0, 5, 1}, {4, 1, 1, 1}, {3, 2, 1, 1}, {2, 3, 1, 1}, {1, 4, 1, 1}, {0, 5, 1, 1}, {0, 6, 5, 1}})
        };
    }
}
