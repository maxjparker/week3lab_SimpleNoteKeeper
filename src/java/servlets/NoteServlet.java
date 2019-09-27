/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Note;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 657306
 */
@WebServlet( name = "NoteServlet", urlPatterns = {"/note"} )
public class NoteServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        // real path
        String path = getServletContext().getRealPath("/WEB-INF/note.txt");
        
        // to read files
        File file = new File(path);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        
        // read note object
        String noteTitle = br.readLine();
        String noteContent = br.readLine();
        Note note = new Note(noteTitle, noteContent);
        
        // page is first loaded
        if(request.getParameter("edit") == null)
        {
            request.setAttribute("title", note.getTitle());
            request.setAttribute("content", note.getContent());
        
            getServletContext().getRequestDispatcher("/WEB-INF/viewnote.jsp")
                .forward(request, response);
        }
        // edit is clicked, jsp changes
        else if(request.getParameter("edit").equalsIgnoreCase(""))
        {
            request.setAttribute("title", note.getTitle());
            request.setAttribute("content", note.getContent());
            
            getServletContext().getRequestDispatcher("/WEB-INF/editnote.jsp")
                .forward(request, response);
        }
        // close file streams
        br.close();
        fr.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        // real path
        String path = getServletContext().getRealPath("/WEB-INF/note.txt");
        
        // read from note.txt
        File file = new File(path);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        
        // create note
        String oldTitle = br.readLine();
        String oldContent = br.readLine();
        Note note = new Note();
        
        request.setAttribute("title", oldTitle);
        request.setAttribute("content", oldContent);
        br.close();
        fr.close();
        
        // edit note
        String newTitle = request.getParameter("titleInput");
        String newContent = request.getParameter("contentInput");
        note.setTitle(newTitle);
        note.setContent(newContent);
        
        // write note to file
        FileWriter fw = new FileWriter(path, false);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);
        pw.write(note.getTitle()+"\n");
        pw.write(note.getContent()+"\n");
        pw.close();
        bw.close();
        fw.close();
        
        
        getServletContext().getRequestDispatcher("/WEB-INF/editnote.jsp")
                .forward(request, response);
    }
}
