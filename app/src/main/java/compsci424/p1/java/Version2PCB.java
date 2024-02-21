/* COMPSCI 424 Program 1
 * Name:
 */
package compsci424.p1.java;

/**
 * The process control block structure that is used to track a
 * process's parent, first child, younger sibling, and older sibling
 * (if they exist) in Version 2.
 */
public class Version2PCB {
    int parentPid = -1;
    int firstChild = -1;
    int youngerSibling = -1;
    int olderSibling = -1;
    int processIndex = -1;
}
