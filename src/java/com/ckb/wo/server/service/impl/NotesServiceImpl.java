package com.ckb.wo.server.service.impl;

import com.ckb.wo.server.service.INotesService;
import com.ckb.wo.client.model.Notes;
import com.ckb.wo.server.dao.NotesDAO;
import java.util.List;

/**
 *
 * @author Amaran Sidhiq
 */
public class NotesServiceImpl extends GenericServiceImpl implements INotesService {

    NotesDAO notesDAO = null;

    public NotesServiceImpl(NotesDAO notesDao) {
        super();
        this.notesDAO = notesDao;
    }

    @Override
    public void saveNotes(Notes record) {
        notesDAO.insertWorkOrder(record);
    }

    @Override
    public List<Notes> getNotesForWorkOrder(Long tworkorderPk, String limitClause) {
        List<Notes> list = notesDAO.selectNotesForWorkOrderPk(tworkorderPk, limitClause);
        return list;
    }

    @Override
    public Notes getNotes(Long tnotesPk) {
        Notes record = (Notes) notesDAO.selectNotesByPrimaryKey(tnotesPk);
        return record;
    }

    @Override
    public Integer countNotes(Long tworkorderPk) {
        return notesDAO.countNotes(tworkorderPk);
    }
}
