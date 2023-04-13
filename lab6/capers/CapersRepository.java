package capers;


import java.io.File;
import static capers.Utils.*;
import java.io.IOException;

import static capers.Utils.readContentsAsString;
import static capers.Utils.writeContents;

/** A repository for Capers 
 * @author TODO
 * The structure of a Capers Repository is as follows:
 *
 * .capers/ -- top level folder for all persistent data in your lab12 folder
 *    - dogs/ -- folder containing all of the persistent data for dogs
 *    - story -- file containing the current story
 *
 * TODO: change the above structure if you do something different.
 */
public class CapersRepository {
    /**
     * Current Working Directory.
     */
    static final File CWD = new File(System.getProperty("user.dir"));

    /**
     * Main metadata folder.
     */
    static final File CAPERS_FOLDER = Utils.join(".capers");
    static final File STORY = new File(".capers", "story.txt");
    public CapersRepository() {
        if (!CAPERS_FOLDER.exists()) {
            CAPERS_FOLDER.mkdir();
        }
    }

                                                                                     // TODO Hint: look at the `join` function in Utils

    /**
     * Does required filesystem operations to allow for persistence.
     * (creates any necessary folders or files)
     * Remember: recommended structure (you do not have to follow):
     *
     * .capers/ -- top level folder for all persistent data in your lab12 folder
     *    - dogs/ -- folder containing all of the persistent data for dogs
     *    - story -- file containing the current story
     */
    public static void setupPersistence() {

        if (!STORY.exists()) {
            try {
                STORY.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        //File DOG_FOLDER = Utils.join(".capers", "dogs");
        //DOG_FOLDER.mkdir();
        // TODO
    }

    /**
     * Appends the first non-command argument in args
     * to a file called `story` in the .capers directory.
     * @param text String of the text to be appended to the story
     */
    public static void writeStory(String text) {
        // TODO

        String new_text = text + "\n";
        String memory = readContentsAsString(STORY);
        writeContents(STORY, memory, new_text);
        System.out.println(readContentsAsString(STORY));
    }

    /**
     * Creates and persistently saves a dog using the first
     * three non-command arguments of args (name, breed, age).
     * Also prints out the dog's information using toString().
     */
    public static void makeDog(String name, String breed, int age) {
        // TODO
        Dog d = new Dog (name, breed, age);
        d.saveDog();
        System.out.println(d.toString());

    }

    /**
     * Advances a dog's age persistently and prints out a celebratory message.
     * Also prints out the dog's information using toString().
     * Chooses dog to advance based on the first non-command argument of args.
     * @param name String name of the Dog whose birthday we're celebrating.
     */
    public static void celebrateBirthday(String name) {
        // TODO

        Dog dd = Dog.fromFile(name);
        dd.haveBirthday();
    }
}
