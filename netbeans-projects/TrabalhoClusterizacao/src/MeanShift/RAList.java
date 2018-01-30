package MeanShift;

public class RAList {

// PUBLIC DATA MEMBERS
    /**
     * RAM Label
     */
    public int label;
    /**
     * RAM Weight
     */
    public float edgeStrength;
    /**
     * # edge pixels
     */
    public int edgePixelCount;
    /**
     * RAM Link
     */
    public RAList next;
// PRIVATE DATA MEMBERS
    /**
     * current and previous pointer
     */
    private RAList cur;
    /**
     * flag
     */
    private char exists;

// PUBLIC METHODS
    /**
     * Constructs a RAList object.
     *
     * Usage: RAList()
     *
     * Post:
     *   - a RAlist object has been properly constructed.
     */
    public void RAList() {

        try {

            // initialize label and link
            label = -1;
            next = null;

            // initialize edge strenght weight and count
            edgeStrength = 0;
            edgePixelCount = 0;


        } catch (Exception e) {
            //System.out.println("\n--- RAList Constructor Exception ---\n");
            e.printStackTrace();
        }

    } // end constructor

    /**
     * <pre>
     * Insert a region node into the region adjacency list.
     *
     * Usage: Insert(RAList entry)
     *
     * Pre:
     *   - entry is a node representing a connected region
     * Post:
     *   - entry has been inserted into the region adjacency list if it does not
     *     already exist there.
     *   - if the entry already exists in the region adjacency list 1 is returned
     *     otherwise 0 is returned.
     *
     * @param entry
     *
     * @return integer  indicating whether or not a new node was
     *   				actually inserted into the region adjacency list???????????
     */
    public int Insert(RAList entry) {

        // if the list contains only one element
        // then insert this element into next
        if (next == null) {
            // insert entry
            next = entry;
            entry.next = null;

            // done
            return 0;
        }

        // traverse the list until either:

        // (a) entry's label already exists - do nothing
        // (b) the list ends or the current label is
        //     greater than entry's label, thus insert the entry
        //     at this location

        // check first entry
        if (next.label > entry.label) {
            // insert entry into the list at this location
            entry.next = next;
            next = entry;

            // done
            return 0;
        }

        // check the rest of the list...
        exists = 0;
        cur = next;
        while (cur != null) {
            if (entry.label == cur.label) {
                // node already exists
                exists = 1;
                break;
            } else if ((cur.next == null) || (cur.next.label > entry.label)) {
                // insert entry into the list at this location
                entry.next = cur.next;
                cur.next = entry;
                break;
            }

            // traverse the region adjacency list
            cur = cur.next;
        }

        // done. Return exists indicating whether or not a new node was
        //       actually inserted into the region adjacency list.
        return (int) (exists);

    } // end Insert
} // end RAList class

