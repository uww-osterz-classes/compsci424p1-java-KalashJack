/* COMPSCI 424 Program 1
 * Name: Jack Cerni
 * 
 * This is a template. Program1.java *must* contain the main class
 * for this program. Otherwise, feel free to edit these files, even
 * these pre-written comments or my provided code. You can add any
 * classes, methods, and data structures that you need to solve the
 * problem and display your solution in the correct format.
 */
package compsci424.p1.java;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main class for this program. The required steps have been copied
 * into the main method as comments. Feel free to add more comments to
 * help you understand your code, or for any other reason. Also feel
 * free to edit this comment to be more helpful for you.
 */
public class Program1 {
    // Declare any class/instance variables that you need here.

    /**
     * @param args command-line arguments, which can be ignored
     */
    public static void main(String[] args) {
        ArrayList commandChain = new ArrayList<String>();
        Scanner in = new Scanner(System.in);

        // 1. Ask the user to enter commands of the form "create N",
        //    "destroy N", or "end", where N is an integer between 0 
        //    and 15.

        System.out.println("Please enter a command 'create N', 'destroy N' or 'end', where 'N' is an integer between 0 and 15 (inclusive). For the create function, N represents the new PCB's parent.\n");

        // 2. While the user has not typed "end", continue accepting
        //    commands. Add each command to a list of actions to take 
        //    while you run the simulation.

        // 3. When the user types "end" (or optionally any word that 
        //    is not "create" or "destroy"), stop accepting commands 
        //    and complete the following steps.
        //
        // Hint: Steps 2 and 3 refer to the same loop. ;-)

        String input = "";
        while(true) {
            System.out.print("Enter a command: ");
            input = in.nextLine();
            if(!input.contains("create") && !input.contains("destroy")) {
                break;
            }
            commandChain.add(input);
        }
        System.out.println();

        // 4. Create an object of the Version 1 class and an object of
        //    the Version 2 class.

        Version1 v1 = new Version1();
        Version2 v2 = new Version2();

        // 5. Run the command sequence once with the Version 1 object, 
        //    calling its showProcessTree method after each command to
        //    show the changes in the tree after each command.

        System.out.println("========== Version 1 Execution ==========");

        String command = "";
        String[] splitCommand = new String[2];
        for(int i = 0; i < commandChain.size(); i++) {
            command = (String) commandChain.get(i);
            if(command.contains("create")) {
                splitCommand = command.split(" ");
                Version1.create(Integer.parseInt(splitCommand[1]));
            } else if(command.contains("destroy")) {
                splitCommand = command.split(" ");
                Version1.destroy(Integer.parseInt(splitCommand[1]));
            }
            Version1.showProcessInfo();
        }

        // 6. Repeat step 5, but with the Version 2 object.

        System.out.println("========== Version 2 Execution ==========");

        command = "";
        splitCommand = new String[2];
        for(int i = 0; i < commandChain.size(); i++) {
            command = (String) commandChain.get(i);
            if(command.contains("create")) {
                splitCommand = command.split(" ");
                Version2.create(Integer.parseInt(splitCommand[1]));
            } else if(command.contains("destroy")) {
                splitCommand = command.split(" ");
                Version2.destroy(Integer.parseInt(splitCommand[1]));
            }
            Version2.showProcessInfo();
        }

        // 7. Store the current system time in a variable
        long startTime = System.currentTimeMillis();

        // ... then run the command sequence 200 times with Version 1.
        for(int x = 0; x < 200; x++) {
            command = "";
            splitCommand = new String[2];
            for(int i = 0; i < commandChain.size(); i++) {
                command = (String) commandChain.get(i);
                if(command.contains("create")) {
                    splitCommand = command.split(" ");
                    Version1.create(Integer.parseInt(splitCommand[1]));
                } else if(command.contains("destroy")) {
                    splitCommand = command.split(" ");
                    Version1.destroy(Integer.parseInt(splitCommand[1]));
                }
            }
        }

        // ... After this, store the new current system time in a
        //     second variable. Subtract the start time from the end 
        //     time to get the Version 1 running time, then display 
        //     the Version 1 running time.
        long finishTime = System.currentTimeMillis();
        long version1RunTime = finishTime - startTime;
        System.out.println("Version 1 200 Loop Execution Time: " + version1RunTime);

        // 8. Repeat step 7, but with the Version 2 object.
        startTime = System.currentTimeMillis();

        for(int x = 0; x < 200; x++) {
            command = "";
            splitCommand = new String[2];
            for(int i = 0; i < commandChain.size(); i++) {
                command = (String) commandChain.get(i);
                if(command.contains("create")) {
                    splitCommand = command.split(" ");
                    Version2.create(Integer.parseInt(splitCommand[1]));
                } else if(command.contains("destroy")) {
                    splitCommand = command.split(" ");
                    Version2.destroy(Integer.parseInt(splitCommand[1]));
                }
            }
        }

        finishTime = System.currentTimeMillis();
        long version2RunTime = finishTime - startTime;
        System.out.println("Version 2 200 Loop Execution Time: " + version2RunTime);

        // This line is here just to test the Gradle build procedure.
        // You can delete it.
        //System.out.println("Builds without errors and runs to completion.");
    }
}
