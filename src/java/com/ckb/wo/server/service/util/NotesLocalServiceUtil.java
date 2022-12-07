/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ckb.wo.server.service.util;

import com.ckb.wo.client.model.Notes;
import com.ckb.wo.server.service.INotesService;
import java.util.List;

/**
 *
 * @author Amaran Sidhiq
 */
public class NotesLocalServiceUtil extends GenericServiceUtil {

    private static INotesService getNotesService() {
        return (INotesService) getBean("notesService");
    }

    public static void saveNotes(Notes record) {
        getNotesService().saveNotes(record);
    }

    public static List<Notes> getNotesForWorkOrder(Long tworkorderPk,String limitClause) {
        List<Notes> list = getNotesService().getNotesForWorkOrder(tworkorderPk,limitClause);
        return list;
    }

    public static Notes getNotes(Long tnotesPk) {
        Notes record = (Notes) getNotesService().getNotes(tnotesPk);
        return record;
    }

    public static Integer countNotes(Long tworkorderPk) {
        return (Integer) getNotesService().countNotes(tworkorderPk);
    }
}
