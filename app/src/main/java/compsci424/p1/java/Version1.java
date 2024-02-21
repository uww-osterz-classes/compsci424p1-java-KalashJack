/* COMPSCI 424 Program 1
 * Name: Jack Cerni
 */
package compsci424.p1.java;

import java.util.LinkedList;

/** 
 * Implements the process creation hierarchy for Version 1, which uses
 * linked lists.
 * 
 * This is a template. Program1.java *must* contain the main class
 * for this program. Otherwise, feel free to edit these files, even
 * these pre-written comments or my provided code. You can add any
 * classes, methods, and data structures that you need to solve the
 * problem and display your solution in the correct format.
 */
public class Version1 {
    // Declare any class/instance variables that you need here.
    static Version1PCB[] PCBArray;

    /**
     * Default constructor. Use this to allocate (if needed) and
     * initialize the PCB array, create the PCB for process 0, and do
     * any other initialization that is needed. 
     */
    public Version1() {
        PCBArray = new Version1PCB[16];
        Version1PCB newPcb = new Version1PCB();
        newPcb.parentPid = -1;
        newPcb.processIndex = 0;
        PCBArray[0] = newPcb;

        for(int i = 1; i < PCBArray.length; i++) {
            PCBArray[i] = null;
        }
    }

    /**
     * Creates a new child process of the process with ID parentPid. 
     * @param parentPid the PID of the new process's parent
     * @return 0 if successful, not 0 if unsuccessful
     */
    static int create(int parentPid) {
        // If parentPid is not in the process hierarchy, do nothing; 
        // your code may return an error code or message in this case,
        // but it should not halt
        if(parentPid < 0 || PCBArray[parentPid] == null) {
            System.out.println("ERROR: parentPid not found in process hierarchy; create failed for parentPid " + parentPid + "!");
            return -1;
        }
        // Assuming you've found the PCB for parentPid in the PCB array:
        // 1. Allocate and initialize a free PCB object from the array
        //    of PCB objects
        Version1PCB newProcess = new Version1PCB();
        newProcess.parentPid = parentPid;
        for(int i = 0; i < PCBArray.length; i++) {
            if(PCBArray[i] == null ) {
                newProcess.processIndex = i;
                PCBArray[i] = newProcess;
                break;
            }
        }

        // 2. Insert the newly allocated PCB object into parentPid's
        //    list of children
        PCBArray[parentPid].children.add(newProcess.processIndex);

        // You can decide what the return value(s), if any, should be.
        // If you change the return type/value(s), update the Javadoc.
        return 0; // often means "success" or "terminated normally"
    }

    /**
     * Recursively destroys the process with ID parentPid and all of
     * its descendant processes (child, grandchild, etc.).
     * @param targetPid the PID of the process to be destroyed
     * @return 0 if successful, not 0 if unsuccessful
     */
    static int destroy (int targetPid) {
         // If targetPid is not in the process hierarchy, do nothing; 
         // your code may return an error code or message in this case,
         // but it should not halt
        if(PCBArray[targetPid] == null) {
            System.out.println("ERROR: targetPid not found in process hierarchy; destroy failed for targetPid " + targetPid + "!");
            return -1;
        }

         // Assuming you've found the PCB for targetPid in the PCB array:
         // 1. Recursively destroy all descendants of targetPid, if it
         //    has any, and mark their PCBs as "free" in the PCB array 
         //    (i.e., deallocate them)
         destroyChildNodes(PCBArray[targetPid].children);

         // 2. Remove targetPid from its parent's list of children
         PCBArray[PCBArray[targetPid].parentPid].children.removeFirstOccurrence(targetPid);

         // 3. Deallocate targetPid's PCB and mark its PCB array entry
         //    as "free"
         PCBArray[targetPid] = null;

         // You can decide what the return value(s), if any, should be.
         // If you change the return type/value(s), update the Javadoc.
        return 0; // often means "success" or "terminated normally"
    }

    /**
     * Traverse the process creation hierarchy graph, printing
     * information about each process as you go. See Canvas for the
     * *required* output format. 
     *         
     * You can directly use "System.out.println" statements (or
     * similar) to send the required output to stdout, or you can
     * change the return type of this function to return the text to
     * the main program for printing. It's your choice. 
     */
    static void showProcessInfo() {
        for(int i = 0; i < PCBArray.length; i++) {
            if(PCBArray[i] != null) {
                System.out.print("Process " + i + ": parent is " + PCBArray[i].parentPid);
                if(PCBArray[i].children.isEmpty()) {
                    System.out.println(" and has no children");
                } else {
                    System.out.print(" and children are ");
                    for(int x = 0; x < PCBArray[i].children.size(); x++) {
                        System.out.print(PCBArray[i].children.get(x) + " ");
                    }
                    System.out.println();
                }
            }
        }
        System.out.println();
    }

    /* If you need or want more methods, feel free to add them. */

    static void destroyChildNodes(LinkedList children) {
        if (children.isEmpty()) {
            return;
        }
        for(int i = 0; i < children.size(); i++) {
            destroyChildNodes(PCBArray[(int) children.get(i)].children);
            PCBArray[(int) children.get(i)] = null;
        }
    }
}
