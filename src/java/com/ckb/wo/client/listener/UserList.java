package com.ckb.wo.client.listener;

import com.ckb.wo.client.model.FastUser;
import com.ckb.wo.client.model.FastUserExample;
import com.ckb.wo.client.model.User;
import com.ckb.wo.client.model.UserExample;
import com.ckb.wo.server.service.util.FastServiceLocalServiceUtil;
import com.ckb.wo.server.service.util.UserLocalServiceUtil;
import com.shido.jdbc2orm.JDBCConnector;
import com.shido.jdbc2orm.ResultMap;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

public class UserList extends HttpServlet {

    final static Logger LOG = Logger.getLogger(UserList.class);

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param pk
     * @param rolePk
     * @return
     */
    public String removeRole(String pk, Long rolePk) {
        String result = "";
        try {
            List<Long> lrolePk = new ArrayList<>();
            lrolePk.add(rolePk);
            UserLocalServiceUtil.deleteUserFromRoles(pk, lrolePk);
            result = "Role Removed!";
        } catch (Exception e) {
            result = e.getMessage();
        }
        return result;
    }

    public String assign(String pk, Long[] rolePks) {
        String result = "";
        try {
            User user = UserLocalServiceUtil.getUserByPrimaryKeyWithJoin(pk);
            List<Long> roles = new ArrayList<>();
            for (Long rolepk : rolePks) {
                roles.add(rolepk);
            }
            UserLocalServiceUtil.insertUserToRoles(pk, roles);
            result = "Role has been updated!";
        } catch (Exception e) {
            result = e.getMessage();
        }
        return result;
    }

    public String setBoss(String under, String upper) {
        String result = "";
        try {
            User u = UserLocalServiceUtil.getUserByPrimaryKey(under);
            u.setBossemployeeid(upper);
            UserLocalServiceUtil.updateUser(u);
            result = "Superior for " + u.getFirstName() + (u.getMiddleName().isEmpty() ? " " : " " + u.getMiddleName() + " ") + u.getLastName();
            if (upper != null) {
                result += " has been set!";
            } else {
                result += " has been cleared!";
            }
            u = null;
        } catch (Exception e) {
            result = e.getMessage();
        }
        return result;
    }

    public String setActive(String pk, boolean active) {
        String result = "";
        try {
            User u = UserLocalServiceUtil.getUserByPrimaryKey(pk);
            u.setIsactiveuser(active);
            UserLocalServiceUtil.updateUser(u);
            result = u.getFirstName() + (u.getMiddleName().isEmpty() ? " " : " " + u.getMiddleName() + " ") + u.getLastName();
            result += " access has been " + (active ? "A" : "Dea") + "ctivated";
        } catch (Exception e) {
            result = e.getMessage();
        }
        return result;
    }

    public String add(String pk) {
        String result = "User:\n";
        List<String> user = new ArrayList<>();
        try {
            FastUserExample example = new FastUserExample();
            example.createCriteria().andEmployeeIdEqualTo(pk);
            Iterator<FastUser> it = FastServiceLocalServiceUtil.getFastUserByExample(example).iterator();
            int i = 0;
            while (it.hasNext()) {
                FastUser u = it.next();
                String midName = u.getMiddleName() == null ? "" : u.getMiddleName();
                result += ++i + " " + (u.getFirstName() == null ? "" : u.getFirstName()) + (midName.isEmpty() ? " " : " " + midName + " ") + (u.getLastName() == null ? "" : u.getLastName()) + "\n";
                user.add(u.getEmployeeId());
            }
            FastServiceLocalServiceUtil.importFastUserByIds(user);
            result += "\nHas been added!";
        } catch (Exception e) {
            result = e.getMessage();
        }
        return result;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            int totalUser = 0;
            int start = 0;
            int limit = 10;
            try {
                start = new Integer(request.getParameter("start"));
                limit = new Integer(request.getParameter("limit"));
            } catch (Exception e) {
            }

            UserExample example = null;
            FastUserExample fexample = null;
            if (request.getParameter("t").equals("user")) {
//                example = new UserExample();
//                example.setLimitClause(start + "," + limit);
//                UserExample.Criteria crit = example.createCriteria();
//                for (String name : request.getParameter("n").split(" ")) {
//                    crit.andFirstNameLike("%" + name + "%");
//                    example.or(example.createCriteria().andMiddleNameLike("%" + name + "%"));
//                    example.or(example.createCriteria().andLastNameLike("%" + name + "%"));
//                }
//                totalUser=UserLocalServiceUtil.countUserByExample(example);
                JDBCConnector conUser = new JDBCConnector();
                example = new UserExample();
                example.setLimitClause(start + "," + limit);
                UserExample.Criteria crit = example.createCriteria();

                for (String name : request.getParameter("n").split(" ")) {
                    crit.andFirstNameLike("%" + name + "%");
                    example.or(example.createCriteria().andMiddleNameLike("%" + name + "%"));
                    example.or(example.createCriteria().andLastNameLike("%" + name + "%"));
                    try {
                        ResultMap rsUser = conUser.getSingleResult("SELECT employee_id FROM db_workorder.tuser WHERE CONCAT_WS(' ', first_name, middle_name, last_name) LIKE '%" + name + "%' ");
                        if (rsUser != null) {
                            example.or(example.createCriteria().andBossemployeeidLike("%" + rsUser.getString("employee_id") + "%"));
                        }
                    } catch (SQLException ex) {
                        LOG.error(ex.getMessage(), ex);
                    }
                }
                totalUser = UserLocalServiceUtil.countUserByExample(example);
//                totalUser = UserLocalServiceUtil.countUser();
            } else if (request.getParameter("t").equals("fuser")) {
                fexample = new FastUserExample();
                fexample.setLimitClause(start + "," + limit);
                FastUserExample.Criteria crit = fexample.createCriteria();
                for (String name : request.getParameter("n").split(" ")) {
                    crit.andFirstNameLike("%" + name + "%");
                    fexample.or(fexample.createCriteria().andMiddleNameLike("%" + name + "%"));
                    fexample.or(fexample.createCriteria().andLastNameLike("%" + name + "%"));
                }
                totalUser = FastServiceLocalServiceUtil.countFastUserByExample(fexample);
            } else if (request.getParameter("t").equals("sboss")) {
//                example = new UserExample();
//                example.setLimitClause(start + "," + limit);
//                UserExample.Criteria crit = example.createCriteria();
//                LevelExample level = new LevelExample();
//
//                level.createCriteria().andLevelValueGreaterThan(LevelLocalServiceUtil.getLevelByPrimaryKey(UserLocalServiceUtil.getUserByPrimaryKey(request.getParameter("sb")).getLevelId()).getLevelValue());
//                Iterator<Level> it = LevelLocalServiceUtil.getLevelByExample(level).iterator();
//                List<String> lst = new ArrayList<String>();
//                while (it.hasNext()) {
//                    Level l = it.next();
//                    lst.add(l.getLevelId());
//                }
//                crit.andLevelIdIn(lst);
//                for (String name : request.getParameter("n").split(" ")) {
//                    example.or(example.createCriteria().andFirstNameLike("%" + name + "%"));
//                    example.or(example.createCriteria().andMiddleNameLike("%" + name + "%"));
//                    example.or(example.createCriteria().andLastNameLike("%" + name + "%"));
//                }
//                totalUser = UserLocalServiceUtil.countUsersOneLevelAbove(request.getParameter("sb"));
//                totalUser = UserLocalServiceUtil.countUser();
                fexample = new FastUserExample();
                fexample.setLimitClause(start + "," + limit);
                FastUserExample.Criteria crit = fexample.createCriteria();
                for (String name : request.getParameter("n").split(" ")) {
                    crit.andFirstNameLike("%" + name + "%");
                    fexample.or(fexample.createCriteria().andMiddleNameLike("%" + name + "%"));
                    fexample.or(fexample.createCriteria().andLastNameLike("%" + name + "%"));
                }
                totalUser = FastServiceLocalServiceUtil.countFastUserByExample(fexample);
            }

            List<User> user = null;
            List<FastUser> fuser = null;

            Map map = new HashMap();
            map.put("total", totalUser);
            JSONArray data = new JSONArray();
            if (request.getParameter("t").equals("user")) {
//                user = UserLocalServiceUtil.getUserByExampleWithJoin(example);
                user = UserLocalServiceUtil.getUserByExample(example);
                Iterator<User> itu = user.iterator();
                while (itu.hasNext()) {
                    User o = itu.next();
                    JSONObject obj = JSONObject.fromObject(o);
                    obj.put("action", "<a href='#' onclick='setActive(\"" + o.getEmployeeId() + "\"," + !o.getIsactiveuser() + ")'>" + (o.getIsactiveuser() ? "Dea" : "A") + "ctivate</a>");
                    try {
                        if (!o.getBossemployeeid().isEmpty()) {
                            User b = UserLocalServiceUtil.getUserByPrimaryKey(o.getBossemployeeid());
                            obj.put("boss", "<a href='#' onclick='setBoss(\"" + o.getEmployeeId() + "\")'>" + (b.getFirstName() + (b.getMiddleName().isEmpty() ? " " : " " + b.getMiddleName() + " ") + b.getLastName()) + "</a>");
                        } else {
                            obj.put("boss", "<a href='#' onclick='setBoss(\"" + o.getEmployeeId() + "\")'>Set</a>");
                        }
                    } catch (NullPointerException e) {
                        obj.put("boss", "<a href='#' onclick='setBoss(\"" + o.getEmployeeId() + "\")'>Set</a>");
                    }
                    data.add(obj);
                }
            } else if (request.getParameter("t").equals("fuser")) {
                fuser = FastServiceLocalServiceUtil.getFastUserByExample(fexample);
                Iterator<FastUser> itf = fuser.iterator();
                while (itf.hasNext()) {
                    FastUser o = itf.next();
                    JSONObject obj = JSONObject.fromObject(o);
                    obj.put("action", "<a href='#' onclick=\"addUser('" + o.getEmployeeId() + "')\">Add</a>");
                    data.add(obj);
                }
            } else if (request.getParameter("t").equals("sboss")) {
                if (fexample != null) {
                    fuser = FastServiceLocalServiceUtil.getFastUserByExample(fexample);
                } else {
                    user = UserLocalServiceUtil.getUsersOneLevelAbove(request.getParameter("sb"), start + "," + limit, null);
                }
                Iterator<FastUser> itu = fuser.iterator();
                while (itu.hasNext()) {
                    try {
                        FastUser o = itu.next();
                        JSONObject obj = JSONObject.fromObject(o);
                        obj.put("action", "<a href='#' onclick='setBoss(\"" + o.getEmployeeId() + "\")'>Select</a>");
                        data.add(obj);
                    } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                    }
                }
            }
            map.put("data", data);
            map.put("success", data.size() > 0);

            JSONObject obj = JSONObject.fromObject(map);
            out.print(obj);
        } finally {
            out.close();
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,
            IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,
            IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
