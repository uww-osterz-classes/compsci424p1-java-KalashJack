/* COMPSCI 424 Program 1
 * Name:
 */
package compsci424.p1.java;

import java.util.ArrayList;
import java.util.LinkedList;

/** 
 * Implements the process creation hierarchy for Version 2, which does
 * not use linked lists.
 * 
 * This is a template. Program1.java *must* contain the main class
 * for this program. Otherwise, feel free to edit these files, even
 * these pre-written comments or my provided code. You can add any
 * classes, methods, and data structures that you need to solve the
 * problem and display your solution in the correct format.
 */
public class Version2 {
    // Declare any class/instance variables that you need here.
    static Version2PCB[] PCBArray;

    /**
     * Default constructor. Use this to allocate (if needed) and
     * initialize the PCB array, create the PCB for process 0, and do
     * any other initialization that is needed. 
     */
    public Version2() {
        PCBArray = new Version2PCB[16];
        Version2PCB newPcb = new Version2PCB();
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

        // Assuming you've found the PCB for parentPid in the PCB array:
        // 1. Allocate and initialize a free PCB object from the array
        //    of PCB objects
        if(PCBArray[parentPid] == null) {
            System.out.println("ERROR: parentPid not found in process hierarchy; create failed for parentPid " + parentPid + "!");
            return -1;
        }

        // 2. Connect the new PCB object to its parent, its older
        //    sibling (if any), and its younger sibling (if any)
        Version2PCB newProcess = new Version2PCB();
        newProcess.parentPid = parentPid;
        int secondYoungestSibling = -1;
        for(int i = 0; i < PCBArray.length; i++) {
            if(PCBArray[i] != null && PCBArray[i].parentPid == parentPid && PCBArray[i].youngerSibling == -1) {
                secondYoungestSibling = i;
            }
        }
        for(int i = 0; i < PCBArray.length; i++) {
            if(PCBArray[i] == null ) {
                newProcess.processIndex = i;
                PCBArray[i] = newProcess;
                break;
            }
        }
        if(secondYoungestSibling != -1 && secondYoungestSibling < 15) {
            PCBArray[secondYoungestSibling].youngerSibling = newProcess.processIndex;
            PCBArray[newProcess.processIndex].olderSibling = PCBArray[secondYoungestSibling].processIndex;
        } else {
            PCBArray[parentPid].firstChild = newProcess.processIndex;
        }

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
        destroyChildNodes(PCBArray[targetPid].firstChild);

        // 2. Adjust connections within the hierarchy graph as needed to
        //    re-connect the graph
        connectNodes(PCBArray[targetPid].parentPid);

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
                ArrayList children = new ArrayList<Integer>();
                for(int x = 0; x < PCBArray.length; x++) {
                    if(PCBArray[x] != null && PCBArray[x].parentPid == i) {
                        children.add(x);
                    }
                }
                if(children.isEmpty()) {
                    System.out.println(" and has no children");
                } else {
                    System.out.print(" and children are ");
                    for(int x = 0; x < children.size(); x++) {
                        System.out.print(children.get(x) + " ");
                    }
                    System.out.println();
                }
            }
        }
        System.out.println();
   }

   /* If you need or want more methods, feel free to add them. */

   static void destroyChildNodes(int youngestSibling) {
        if (youngestSibling == -1) {
            return;
        }
        if(PCBArray[youngestSibling].youngerSibling != -1) {
            destroyChildNodes(PCBArray[youngestSibling].youngerSibling);
        }
        PCBArray[youngestSibling] = null;
    }

    static void connectNodes(int newParent) {
        ArrayList vacantPCBs = new ArrayList<Integer>();
        for(int i = 0; i < PCBArray.length; i++) {
            if(PCBArray[i] == null) {
                vacantPCBs.add(i);
            }
        }
        for(int i = 0; i < PCBArray.length; i++) {
            if(PCBArray[i] != null && vacantPCBs.contains(PCBArray[i].parentPid)) {
                PCBArray[i].parentPid = newParent;
            }
        }
    }

}
