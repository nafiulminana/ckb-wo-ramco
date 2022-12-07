package com.ckb.wo.server.dao.ibatis.impl;

import com.ckb.wo.server.dao.NotesDAO;
import com.ckb.wo.client.model.Notes;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class NotesDAOImpl extends SqlMapClientDaoSupport implements NotesDAO{

    public NotesDAOImpl() {
        super();
    }

    @Override
    public void insertWorkOrder(Notes record) {
        Object newKey = getSqlMapClientTemplate().insert(
                "tnotes.insertNotes", record);
        record.setTnotesPk((Long) newKey);
    }

    @Override
    public List<Notes> selectNotesForWorkOrderPk
            (Long tworkorderPk,String limitClause) {
        Map param = new HashMap();
        param.put("tworkorderPk", tworkorderPk);
        param.put("limitClause", limitClause);
        List<Notes> list = getSqlMapClientTemplate().queryForList(
                "tnotes.selectNotesByWorkorderId", param);
        return list;
    }

    @Override
    public Notes selectNotesByPrimaryKey(Long tnotesPk) {
        Notes record = (Notes) getSqlMapClientTemplate().queryForObject(
                "tnotes.selectNotesByNotesId", tnotesPk);
        return record;
    }

    @Override
    public Integer countNotes(Long tworkorderPk) {
        return (Integer) getSqlMapClientTemplate().queryForObject("tnotes.countNotes", tworkorderPk);
    }
}
