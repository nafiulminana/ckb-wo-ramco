/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ckb.wo.server.service;

import com.ckb.wo.client.model.Notes;
import java.util.List;

/**
 *
 * @author Amaran Sidhiq
 */
public interface INotesService {

    Integer countNotes(Long tworkorderPk);

    Notes getNotes(Long tnotesPk);

    List<Notes> getNotesForWorkOrder(Long tworkorderPk, String limitClause);

    void saveNotes(Notes record);

}
