/*
 * Name: Diane Li
 * PID:  A15773774
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Search Engine implementation.
 * 
 * @author Diane Li
 * @since  02/17/2021
 */
public class SearchEngine {

    private static final int QUERY_START = 2;

    /**
     * Populate BSTrees from a file
     * @param movieTree  - BST to be populated with actors
     * @param studioTree - BST to be populated with studios
     * @param ratingTree - BST to be populated with ratings
     * @param fileName   - name of the input file
     * @returns false if file not found, true otherwise
     */
    public static boolean populateSearchTrees(
            BSTree<String> movieTree, BSTree<String> studioTree,
            BSTree<String> ratingTree, String fileName
    ) {
        // open and read file
        File file = new File(fileName);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                // read 5 lines per batch: movie, cast, studios, rating, trailing hyphen
                String movie = scanner.nextLine().trim();
                String cast[] = scanner.nextLine().split(" ");
                String studios[] = scanner.nextLine().split(" ");
                String rating = scanner.nextLine().trim();
                scanner.nextLine();

                // populate three trees with the information you just read
                for (String actor : cast) {
                    populateHelper(movieTree, actor, movie);
                    populateHelper(ratingTree, actor, rating);
                }
                for (String studio: studios) { populateHelper(studioTree, studio, movie); }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }

    private static void populateHelper(BSTree<String> tree, String rawKey, String value) {
        // converts key to lower case for case-insensitive search
        String key = rawKey.toLowerCase();

        // if key not already in tree, insert key and value. else, add value to key's linked list
        tree.insert(key);
        // check that key's linked list doesn't already contain this document
        LinkedList<String> dataList= tree.findDataList(key);
        if (!dataList.contains(value)) { tree.insertData(key, value); }
    }

    /**
     * Search a query in a BST
     * @param searchTree - BST to be searched
     * @param query      - query string
     */
    public static void searchMyQuery(BSTree<String> searchTree, String query) {

        // process query
        String[] keys = query.toLowerCase().split(" ");
        // inserts key if not already in tree to avoid IAE in finding datalist
        searchTree.insert(keys[0]);
        LinkedList<String> firstVals = searchTree.findDataList(keys[0]);

        // search and output intersection results
        LinkedList<String> commonVals = new LinkedList<>();
        commonVals.addAll(firstVals);
        LinkedList<String> currVals;
        // if more than 1 key in query, iteratively retains only values common to all keys
        for (int i = 1; i < keys.length; i++) {
            searchTree.insert(keys[i]);
            currVals = searchTree.findDataList(keys[i]);
            commonVals.retainAll(currVals);
        }
        print(query, commonVals);

        // search and output individual results
        if (keys.length > 1) {
            for (String key : keys) {
                // extracts current key's data
                currVals = searchTree.findDataList(key);
                // if there are values, removes the ones already printed
                // and adds them to the list of values that has been printed
                if (currVals.isEmpty()) {
                    print(key, currVals);
                } else {
                    currVals.removeAll(commonVals);
                    commonVals.addAll(currVals);
                    if (!currVals.isEmpty()) {
                        print(key, currVals);
                    }
                }
            }
        }

    }

    /**
     * Print output of query
     * @param query     Query used to search tree
     * @param documents Output of documents from query
     */
    public static void print(String query, LinkedList<String> documents) {
        if (documents == null || documents.isEmpty())
            System.out.println("The search yielded no results for " + query);
        else {
            Object[] converted = documents.toArray();
            Arrays.sort(converted);
            System.out.println("Documents related to " + query
                    + " are: " + Arrays.toString(converted));
        }
    }

    /**
     * Main method that processes and query the given arguments
     * @param args command line arguments (at least 3: file containing all documents and keywords
     *             in search engine, kind of search to be doing, keyword(s) to search for)
     */
    public static void main(String[] args) {

        // initialize search trees
        BSTree movies = new BSTree();
        BSTree studios = new BSTree();
        BSTree ratings = new BSTree();

        // process command line arguments
        String fileName = args[0];
        int searchKind = Integer.parseInt(args[1]);

        String query = "";
        for (int i = QUERY_START; i < args.length; i++) {
            query += args[i] + " ";
        }

        // populate search trees
        populateSearchTrees(movies, studios, ratings, fileName);

        // choose the right tree to query
        BSTree myTree;
        if (searchKind == 0) { myTree = movies; }
        else if (searchKind == 1) { myTree = studios; }
        else { myTree = ratings; }
        searchMyQuery(myTree, query);

    }
}
