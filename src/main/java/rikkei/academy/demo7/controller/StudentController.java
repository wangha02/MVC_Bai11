package rikkei.academy.demo7.controller;

import rikkei.academy.demo7.model.Student;
import rikkei.academy.demo7.service.IStudentService;
import rikkei.academy.demo7.service.StudentServiceIMPL;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(value = {"/", "/students"})
public class StudentController extends HttpServlet {
    private IStudentService studentService = new StudentServiceIMPL();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showFormCreate(request, response);
                break;
            case "edit":
                showFormEdit(request, response);
                break;
            case "delete":
                formDeleteStudent(request, response);
                break;
            default:
                showListStudent(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                actionCreate(request, response);
                break;
            case "edit":
                actionEdit(request, response);
                break;
                case "delete":
                    actionDelete(request, response);
                    break;
        }
    }



    private void showListStudent(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        // tao ra list de tra ve view
        List<Student> studentList = studentService.findAll();
        request.setAttribute("listStudent", studentList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("students/list.jsp");
        dispatcher.forward(request, response);
    }

    private void showFormCreate(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("students/create.jsp");
        dispatcher.forward(request, response);
    }

    private void actionCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = studentService.findAll().get(studentService.findAll().size() - 1).getId() + 1;
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        Student student = new Student(id, name, age);
        studentService.save(student);
        request.setAttribute("message", "Create student success");
        RequestDispatcher dispatcher = request.getRequestDispatcher("students/create.jsp");
        dispatcher.forward(request, response);
    }

    //EDITTTT
    private void showFormEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Student student = studentService.findById(id);
        request.setAttribute("student", student);
        RequestDispatcher dispatcher = request.getRequestDispatcher("students/edit.jsp");
        dispatcher.forward(request, response);
    }

    private void actionEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Student student = studentService.findById(id);
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        student.setName(name);
        student.setAge(age);
        request.setAttribute("message", "update success!!");
        RequestDispatcher dispatcher = request.getRequestDispatcher("students/edit.jsp");
        dispatcher.forward(request, response);
    }

    private void formDeleteStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Student student = studentService.findById(id);
        request.setAttribute("student", student);
        RequestDispatcher dispatcher = request.getRequestDispatcher("students/delete.jsp");
        dispatcher.forward(request,response);
    }

    private void actionDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        studentService.deleteById(id);
        request.setAttribute("message","update success");
        RequestDispatcher dispatcher = request.getRequestDispatcher("students/list.jsp");
        request.setAttribute("listStudent",studentService.findAll());
        dispatcher.forward(request,response);

    }
}

