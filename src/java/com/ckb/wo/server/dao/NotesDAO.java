/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ckb.wo.server.dao;

import com.ckb.wo.client.model.Notes;
import java.util.List;

/**
 *
 * @author Amaran Sidhiq
 */
public interface NotesDAO {

    Integer countNotes(Long tworkorderPk);

    void insertWorkOrder(Notes record);

    Notes selectNotesByPrimaryKey(Long tnotesPk);

    List<Notes> selectNotesForWorkOrderPk(Long tworkorderPk,String limitClause);

}
