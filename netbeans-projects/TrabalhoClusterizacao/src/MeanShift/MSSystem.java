package MeanShift;

public class MSSystem {

// PUBLIC DATA MEMBERS
    /**
     * percent of mean-shift calculations completed
     */
    public int percentDone = 0;
// PRIVATE DATA MEMBERS
    /**
     * time that calculations were started
     */
    private java.util.Date currentTime;

// PUBLIC METHODS
    /**
     * Constructs a mean shift system object.
     */
    public MSSystem() {

        try {

            // initialize currentTime
            currentTime = new java.util.Date();

        } catch (Exception e) {
            e.printStackTrace();
        }


    } // end constructor

    /**
     * <pre>
     * Initializes the system timer. The timer object synthesized by this class is
     * initialized during construction of the msSystem class to be the current time
     * during construction.
     *
     * Usage: StartTimer()
     *
     * Post:
     *   - the mean shift system time has been set to the current system time.
     */
    public void StartTimer() {

        try {

            // set msSystem time to system time
            currentTime = new java.util.Date();


        } catch (Exception e) {
            System.out.println("\n--- MSSystem.StartTimer() Exception ---\n");
            e.printStackTrace();
        }

    } //end StartTimer

    /**
     * <pre>
     * Returns the amount of time elapsed in seconds from when StartTimer() was called.
     * If StartTimer() was not called, the time returned is the time elapsed from the
     * construction of the msSystem object.
     *
     * Usage: ElapsedTime()
     *
     * Post:
     *   - the amount of time in seconds since the mean shift system time was last set
     *     is returned.
     *
     * @return long integer of the amount of time elapsed
     */
    public long ElapsedTime() {

        // DECLARATIONS
        java.util.Date tmpTime = new java.util.Date();

        // return the amount of time elapsed in seconds
        // since the MSSystem time was last set...
        return (tmpTime.getTime() - currentTime.getTime()) / 1000;

    }

    /**
     * <pre>
     * Outputs to a device a character message containing delimeters. These delimeters
     * are replaced by the variable input parameters passed to prompt.(Like printf.)                                   |//
     * This method should be altered if a special device either than stderr is desired
     * to be used as an output prompt.
     *
     * Usage: Prompt(varArgs)
     *
     * Pre:
     *   - a variable set of arguments
     * Post:
     *   - string has been output to the user
     *
     * @param args		a variable set of arguments to be placed into the prompt string
     */
    public void Prompt(String args) {

        try {

            System.out.print(args);

        } catch (Exception e) {
            e.printStackTrace();
        }


    } // end Prompt
} // end MSSystem class

