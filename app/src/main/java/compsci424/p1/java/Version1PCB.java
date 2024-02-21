/* COMPSCI 424 Program 1
 * Name: Jack Cerni
 */
package compsci424.p1.java;

import java.util.LinkedList;

/**
 * The process control block structure that is used to track a
 * process's parent and children (if any) in Version 1.
 */
public class Version1PCB {
    int parentPid = -1;
    int processIndex = -1;
    LinkedList children = new LinkedList<Integer>();
}
