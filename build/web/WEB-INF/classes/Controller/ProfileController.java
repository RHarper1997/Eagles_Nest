/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Data.*;
import Model.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.errors.IntrusionException;
import org.owasp.esapi.errors.ValidationException;

/**
 *
 * @author regin
 */
public class ProfileController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProfileController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProfileController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
            throws ServletException, IOException {
        doPost(request, response);
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
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user;
        UserProfile profile;
        String action =null;
        try {
            action = ESAPI.validator().getValidInput("Action String Validation", request.getParameter("action"), "SafeString", 200, false);
        } catch (ValidationException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IntrusionException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (session.getAttribute("currentProfile") == null) {
            profile = new UserProfile();
        } else {
            profile = (UserProfile) session.getAttribute("currentProfile");
        }
        ArrayList<Item> itemData = ItemDB.getAllItems();
        if (session.getAttribute("theUser") == null || session.getAttribute("theUser") == "") {
            user = UserDB.getUser(1);
            session.setAttribute("theUser", user);
        }
        if (session.getAttribute("theUser") != null && session.getAttribute("theUser") != "") {
            if (action == null || (!action.equals("save") && !action.equals("updateProfile")
                    && !action.equals("updateRating") && !action.equals("updateFlag") && !action.equals("deleteItem"))) {
                getServletContext().getRequestDispatcher("/mybookmarks.jsp").forward(request, response);
            }
            if (action.equals("save")) {
                String item = request.getParameterValues("itemList")[0];
                Item newItem = new Item();
                for (int i = 0; i < itemData.size(); i++) {
                    if (itemData.get(i).getItemCode().equals(item)) {
                        newItem = itemData.get(i);
                    }
                }
                profile.addItem(newItem, newItem.getRating(), false);
                session.setAttribute("currentProfile", profile);
                getServletContext().getRequestDispatcher("/mybookmarks.jsp").forward(request, response);
            } else if (action.equals("updateProfile")) {
                String itemCode = "";
                if (request.getParameterValues("itemList") != null && request.getParameterValues("itemList").length != 0) {
                    for (int i = 0; i < request.getParameterValues("itemList").length; i++) {
                        for (int x = 0; x < itemData.size(); x++) {
                            if (request.getParameterValues("itemList")[i].equals(itemData.get(x).getItemCode())) {
                                itemCode = request.getParameterValues("itemList")[i];
                                for (int z = 0; z < profile.getItems().size(); z++) {
                                    if (profile.getItems().get(z).getItem().getItemCode().equals(itemCode)) {
                                        request.setAttribute("theItem", profile.getItems().get(z));
                                        getServletContext().getRequestDispatcher("/feedback.jsp").forward(request, response);
                                        return;
                                    }
                                }
                                getServletContext().getRequestDispatcher("/mybookmarks.jsp").forward(request, response);
                            }
                        }
                    }

                }
            } else if (action.equals("updateRating")) {
                String itemCode = "";
                String[] itemList = request.getParameterValues("itemList");
                for (int i = 0; i < itemList.length; i++) {
                    for (int x = 0; x < itemData.size(); x++) {
                        if (itemList[i].equals(itemData.get(x).getItemCode())) {
                            itemCode = itemList[i];
                            Item newItem = null;
                            for (int z = 0; z < itemData.size(); z++) {
                                if (itemData.get(z).getItemCode().equals(itemCode)) {
                                    newItem = itemData.get(z);
                                }
                            }
                            if (request.getParameter(itemCode).compareTo("0") < 0 || request.getParameter(itemCode).compareTo("5") > 0 || newItem.getRating() == null) {
                                getServletContext().getRequestDispatcher("/mybookmarks.jsp").forward(request, response);
                                return;
                            }
                            if (request.getParameter(itemCode).equals("0")) {
                                for (int n = 0; n < profile.getItems().size(); n++) {
                                    if (profile.getItems().get(n).getItem().getItemCode().equals(newItem.getItemCode())) {
                                        profile.getItems().get(n).setRating(null);
                                        break;
                                    }
                                }
                            } else {
                                for (int n = 0; n < profile.getItems().size(); n++) {
                                    if (profile.getItems().get(n).getItem().getItemCode().equals(newItem.getItemCode())) {
                                        if (!request.getParameter(itemCode).equals(profile.getItems().get(n).getRating())) {
                                            String newRating = request.getParameter(itemCode);
                                            profile.getItems().get(n).setRating(newRating);
                                        }
                                    }
                                }

                            }
                            session.setAttribute("currentProfile", profile);
                            getServletContext().getRequestDispatcher("/mybookmarks.jsp").forward(request, response);
                        }
                    }
                }

            } else if (action.equals("updateFlag")) {
                String itemCode = "";
                String[] itemList = request.getParameterValues("itemList");
                for (int i = 0; i < itemList.length; i++) {
                    for (int x = 0; x < itemData.size(); x++) {
                        if (request.getParameterValues("itemList")[i].equals(itemData.get(x).getItemCode())) {
                            itemCode = request.getParameterValues("itemList")[i];
                            ItemRating newIR = new ItemRating();
                            for (int z = 0; z < itemData.size(); z++) {
                                if (itemData.get(z).getItemCode().equals(itemCode)) {
                                    newIR.setItem(itemData.get(z));
                                    newIR.setOwnIt(!newIR.isOwnIt());
                                }
                            }
                            if (newIR.isOwnIt() != true && newIR.isOwnIt() != false) {
                                getServletContext().getRequestDispatcher("/mybookmarks.jsp").forward(request, response);
                            }
                            for (int w = 0; w < profile.getItems().size(); w++) {
                                if (profile.getItems().get(w).getItem().getItemCode().equals(newIR.getItem().getItemCode())) {
                                    if (request.getParameter(itemCode).equals(String.valueOf(profile.getItems().get(w).isOwnIt()))) {
                                        profile.getItems().get(w).setOwnIt(!profile.getItems().get(w).isOwnIt());
                                    }
                                }
                            }
                            session.setAttribute("currentProfile", profile);
                            getServletContext().getRequestDispatcher("/mybookmarks.jsp").forward(request, response);
                        }
                    }
                }

            } else if (action.equals("deleteItem")) {
                String itemCode;
                String[] itemList = request.getParameterValues("itemList");
                for (int i = 0; i < itemList.length; i++) {
                    for (int x = 0; x < itemData.size(); x++) {
                        if (itemList[i].equals(itemData.get(x).getItemCode())) {
                            itemCode = itemList[i];
                            for (int z = 0; z < profile.getItems().size(); z++) {
                                if (profile.getItems().get(z).getItem().getItemCode().equals(itemCode)) {
                                    profile.removeItem(itemData.get(x));
                                    session.setAttribute("currentProfile", profile);
                                    getServletContext().getRequestDispatcher("/mybookmarks.jsp").forward(request, response);
                                }
                            }
                        }
                    }
                }
                getServletContext().getRequestDispatcher("/mybookmarks.jsp").forward(request, response);
            }
        }

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

    public static String validate(String s) {
        String action = null;
        try {

            action = ESAPI.validator().getValidInput("Action String Validation", s, "SafeString", 200, false);

        } catch (ValidationException e) {

        } catch (IntrusionException e) {

        }
        return action;
    }

}
