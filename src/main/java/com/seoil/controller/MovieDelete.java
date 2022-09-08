package com.seoil.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.seoil.dao.MovieDAO;
import com.seoil.dto.MovieVO;

/**
 * Servlet implementation class MovieDelete
 */
@WebServlet("/moviedelete.do")
public class MovieDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String url="movie/movieDelete.jsp";
		int code=Integer.parseInt(request.getParameter("code"));
		
		MovieDAO movieDAO=MovieDAO.getInstance();
		//실질적으로 지우는 작업을 jsp에서 하려고 하는 행위
		MovieVO mVO=movieDAO.selectProductByCode(code);
		request.setAttribute("movie", mVO);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		
		
		MovieDAO movieDAO=MovieDAO.getInstance();
		int code=Integer.parseInt(request.getParameter("code"));
		movieDAO.deleteMovie(code);
		response.sendRedirect("movielist.do");
		
		/*
		 * String url="movie/movieList.jsp"; RequestDispatcher dispatcher =
		 * request.getRequestDispatcher(url); dispatcher.forward(request, response);
		 */
	}

}
