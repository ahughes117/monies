package util;

import entities.*;
import java.util.ArrayList;
import javax.swing.JList;

/**
 * SQL ResultSet to JList utility
 *
 * @author alexhughes
 */
public class ListUtil {

    /**
     * Receives a JList object and populates it with items from an Entity List.
     *
     * @param anEntityL
     * @param aList
     */
    public static void fillList(ArrayList<Entity> anEntityL, JList aList) {
        ArrayList<String> items = new ArrayList();
        String[] itemArray = new String[anEntityL.size()];

        for (Entity ent : anEntityL) {
            //casting and adding items
            if (ent instanceof Expense) {
                Expense e = (Expense) ent;
                items.add(e.getExpenseID() + ", " + e.getAmount() + " " + e.getDescription() + " " + e.getDate());
            }
        }

        //converting the ArrayList to a String Array
        for (int i = 0; i < items.size(); i++) {
            itemArray[i] = items.get(i);
        }

        //removing and then adding
        aList.removeAll();
        aList.setListData(itemArray);
    }
}
