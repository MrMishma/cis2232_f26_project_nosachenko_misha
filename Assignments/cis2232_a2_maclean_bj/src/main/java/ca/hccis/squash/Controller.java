package ca.hccis.squash;

import ca.hccis.util.CisUtility;

/**
 * Controls the overall flow of the program.
 *
 * @author cis2232
 * @since 20260921
 */
public class Controller {

    public static final int EXIT = 0;

    public static final String MENU = "1) Option 1" + System.lineSeparator()
            + EXIT + ") Exit"
            + System.lineSeparator();

    public static final String MESSAGE_ERROR = "Error";
    public static final String MESSAGE_EXIT = "Goodbye";
    public static final String MESSAGE_SUCCESS = "Success";

    public static void main(String[] args) {

        int menuOption;

        do {
            menuOption = CisUtility.getInputInt(MENU);

            switch (menuOption) {
                case EXIT:
                    System.out.println(MESSAGE_EXIT);
                    break; //Break out of the loop as we're finished.
                case 1:
                    processOption1();
                    break;
                default:
                    System.out.println(MESSAGE_ERROR);
                    break;
            }
        } while (menuOption != EXIT);
    }

    /**
     * Processing for menu option 1
     *
     * @author
     * @since
     */
    public static void processOption1() {

    }
}
