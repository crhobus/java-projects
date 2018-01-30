package MeanShift;

public class RegionList {

// PRIVATE DATA MEMBERS
    /**
     * region list partitioned array
     */
    private REGION regionList[];
    /**
     * defines the number maximum number of regions allowed (determined by
     * user during class construction)
     */
    private int maxRegions;
    /**
     * the number of regions currently stored by the region list
     */
    private int numRegions;
    /**
     * dimension of data set being classified by region list class
     */
    private int N;
    /**
     * number of points contained by the data set being classified byregion list class
     */
    private int L;

// PUBLIC METHODS
    /**
     * <pre>
     * Constructs a region list object.
     *
     * Usage: RegionList(maxRegions, L, N)
     *
     * Pre:
     *   - modesPtr is a pointer to an array of modes
     *   - maxRegions_ is the maximum number of regions
     *     that can be defined
     *   - L_ is the number of data points being class
     *     ified by the region list class
     *   - N is the dimension of the data set being cl
     *     assified by the region list class
     * Post:
     *   - a region list object has been properly init
     *     ialized.
     *
     * @param maxRegions_	the maximum amount of regions that can be classified by
     *						the region list
     * @param L_			the length of the input data set being classified by the
     *						region list object
     * @param N_			the dimension of the input data set being classified by
     *						the region list object
     */
    public RegionList(int maxRegions_, int L_, int N_) {

        try {

            // Obtain maximum number of regions that can be
            // defined by user
            if ((maxRegions = maxRegions_) <= 0) {
                ErrorHandler("RegionList", "Maximum number of regions is zero or negative.", ErrorType.FATAL);
            }

            // Obtain dimension of data set being classified by
            // region list class
            if ((N = N_) <= 0) {
                ErrorHandler("RegionList", "Dimension is zero or negative.", ErrorType.FATAL);
            }

            // Obtain length of input data set...
            if ((L = L_) <= 0) {
                ErrorHandler("RegionList", "Length of data set is zero or negative.", ErrorType.FATAL);
            }

            // Allocate memory for region list array
            regionList = new REGION[maxRegions];

            //Initialize region list...
            numRegions = 0;


        } catch (Exception e) {
            //System.out.println("\n--- RegionList Constructor Exception ---\n");
            e.printStackTrace();
        }


    } // end constructor

    /**
     * <pre>
     * Adds a region to the region list.
     *
     * Usage: AddRegion(label, pointCount, indeces)
     *
     * Pre:
     *   - label is a positive integer used to uniquely identify a region
     *   - pointCount is the number of N-dimensional data points that exist in the
     *     region being classified.
     *   - indeces is a set of indeces specifying the data points contained within
     *     this region
     *   - pointCount must be > 0
     * Post:
     *   - a new region labeled using label and containing pointCount number of
     *     points has been added to the region list.
     *
     * @param label			a positive integer used to uniquely identify a region
     * @param pointCount	a positive integer that specifies the number of N-dimensional
     *						data points that exist in the region being classified
     * @param indeces		an integer array that specifies the set of indeces of the
     *						data points that are contianed with in this region
     * @param offset
     */
    public void AddRegion(int label, int pointCount, int indeces[], int offset) {

        try {

            // make sure that there is enough room for this new region
            // in the region list array...
            if (numRegions >= maxRegions) {
                ErrorHandler("AddRegion", "Not enough memory allocated.", ErrorType.FATAL);
            }

            // make sure that label is positive and point Count > 0...
            if ((label < 0) || (pointCount <= 0)) {
                ErrorHandler("AddRegion", "Label is negative or number of points in region is invalid.", ErrorType.FATAL);
            }

            // place new region into region list array using
            // numRegions index
            regionList[numRegions] = new REGION();
            regionList[numRegions].label = label;
            regionList[numRegions].pointCount = pointCount;
            regionList[numRegions].region = new int[pointCount];

            System.arraycopy(indeces, offset, regionList[numRegions].region, 0, pointCount);

            // increment numRegions to indicate that another
            // region has been added to the region list
            numRegions++;


        } catch (Exception e) {
            //	System.out.println("\n--- RegionList.AddRegion() Exception ---\n");
            e.printStackTrace();
        }

    } // end AddRegion

    /**
     * <pre>
     * Resets the region list for re-use (for new classification).
     *
     * Usage: Reset()
     *
     * Post:
     *   - the region list has been reset.
     */
    public void Reset() {

        try {

            // reset region list
            numRegions = 0;


        } catch (Exception e) {
            //System.out.println("\n--- RegionList.Reset Exception ---\n");
            e.printStackTrace();
        }

    } // end Reset

    /**
     * <pre>
     * Returns the number of regions stored by the region list.
     *
     * Usage: GetNumRegions()
     *
     * Post:
     *   - the number of regions stored by the region list is returned.
     *
     * @return integer indicating how many regions have been found
     */
    public int GetNumRegions() {

        // return region count
        return numRegions;

    } // end GetNumRegions

    /**
     * <pre>
     * Returns the label of a specified region.
     *
     * Usage: label = GetLabel(regionNumber)
     *
     * Pre:
     *   - regionNum is an index into the region list array.
     * Post:
     *   - the label of the region having region index specified by regionNum has
     *     been returned.
     * @param regionNum	the index of the region in the region list array
     *
     * @return integer that is the label of a specified region
     */
    public int GetLabel(int regionNum) {

        // return the label of a specified region
        return regionList[regionNum].label;

    } // end GetLabel

    /**
     * <pre>
     * Returns number of data points contained by a specified region.
     *
     * Usage: pointCount = GetRegionCount(regionNumber)
     *
     * Pre:
     *   - regionNum is an index into the region list array.
     * Post:
     *   - the number of points that classify the region whose index is specified
     *     by regionNum is returned.
     *
     * @param regionNum	the index of the region in the region list array
     *
     * @return integer of the return the region count of a specified region
     */
    public int GetRegionCount(int regionNum) {

        // return the region count of a specified region
        return regionList[regionNum].pointCount;

    } // end GetRegionCount

    /**
     * <pre>
     * Returns a pointer to a set of grid location indeces specifying the data points
     * belonging to a specified region.
     *
     * Usage: indeces = GetRegionIndeces(regionNumber)
     *
     * Pre:
     *   - regionNum is an index into the region list array.
     * Post:
     *   - the region indeces specifying the points contained by the region specified
     *     by regionNum are returned.
     *
     * @param regionNum	the index of the region in the region list array
     *
     * @return integer array of the return point indeces using regionNum
     */
    public int[] GetRegionIndeces(int regionNum) {

        // return point indeces using regionNum
        return regionList[regionNum].region;

    } // end GetRegionIndeces

// PRIVATE METHODS
    /**
     * Class Error Handler
     *
     * Usage; ErrorHandler(functname, errmsg, status)
     *
     * Pre:
     *   - functName is the name of the function that caused an error
     *   - errmsg is the error message given by the calling function
     *   - status is the error status: ErrorType.FATAL or NONErrorType.FATAL
     * Post:
     *   - the error message errmsg is flagged on behave of function functName.
     *   - if the error status is ErrorType.FATAL then the program is halted, otherwise
     *     execution is continued, error recovery is assumed to be handled by
     *     the calling function.
     *
     * @param functName		the name of the corresponding function
     * @param errmsg		the error message associated with the function name
     * @param status		the error type that has occured
     */
    private void ErrorHandler(String functName, String errmsg, ErrorType status) {

        try {

            // flag error message on behalf of calling function, error format
            // specified by the error status...
            if (status == ErrorType.NONFATAL) {
                System.out.println(functName + " Error: " + errmsg + "\n");
            } else {
                System.out.println(functName + " FATAL Error: " + errmsg + "\nAborting Program.\n");
                System.exit(1);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    } // end ErrorHandler
} // end RegionList class

