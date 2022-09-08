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
 * Servlet implementation class MovieUpdate
 */
@WebServlet("/movieupdate.do")
public class MovieUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String url="movie/movieUpdate.jsp";
		int code = Integer.parseInt(request.getParameter("code"));
		MovieDAO movieDAO=MovieDAO.getInstance();
		MovieVO mVO=movieDAO.selectProductByCode(code);
		request.setAttribute("movie", mVO);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		//파일 열때 경로를 지정해주는 것
		String saveDirctory=request.getServletContext().getRealPath("images");
		int maxPostSize=5*1024*1024; //5MB
		String encType="UTF-8";
		//파일 이름이 동일할때 번호를 부여함 (policy)
		DefaultFileRenamePolicy policy=new DefaultFileRenamePolicy();
		MultipartRequest multi=new MultipartRequest(request,saveDirctory,maxPostSize,encType,policy);
		
		MovieVO mVO=new MovieVO();
		mVO.setCode(Integer.parseInt(multi.getParameter("code")));
		mVO.setTitle(multi.getParameter("title"));
		mVO.setPrice(Integer.parseInt(multi.getParameter("price")));
		mVO.setDirector(multi.getParameter("director"));
		mVO.setActor(multi.getParameter("actor"));
		mVO.setPoster(multi.getParameter("poster"));
		mVO.setSynopsis(multi.getParameter("synopsis"));
		
		if(multi.getFilesystemName("poster") == null ) {
			mVO.setPoster(multi.getParameter("nomakeImg"));
		}else {
			mVO.setPoster(multi.getFilesystemName("poster"));
		}
		MovieDAO productDAO=MovieDAO.getInstance();
		productDAO.updateProduct(mVO);
		
		response.sendRedirect("movielist.do");
	}

}
